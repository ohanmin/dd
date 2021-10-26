package poly.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.UserInfoDTO;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 */
@Controller
public class UserInfoController {
	private Logger log = Logger.getLogger(this.getClass());
	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱긆톤패턴 적용됨)
	 */
	@Resource(name= "UserInfoService")
	private IUserInfoService userInfoService;
	/**
	 * 회원가입 화면으로 이동
	 */
	@RequestMapping(value="user/userRegForm")
	public String userRegForm() {
		log.info(this.getClass().getName() + ".user/userRegForm ok!");
		return "/user/UserRegForm";
	}
	/**
	 * 회원가입 로직 처리
	 */
	@RequestMapping(value="user/insertUserInfo")
	public String insertUserInfo(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + ".insertUserInfo start");
		//회원가입 결과에 대한 메시지를 전달할 변수
		String msg = "";
		//웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;
		try {
			/*
			 * 웹(회원정보 입력화면)에서 받는 정보를 String 변수에 저장 시작
			 * 무조건 웹으로 받는 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
			 * 
			 */
			String user_id= CmmUtil.nvl(req.getParameter("user_id"));//아이디
			String user_name= CmmUtil.nvl(req.getParameter("user_name"));//이름
			String password= CmmUtil.nvl(req.getParameter("password"));//비밀번호
			String email= CmmUtil.nvl(req.getParameter("email"));//이메일
			String addr1= CmmUtil.nvl(req.getParameter("addr1"));//주소
			String addr2= CmmUtil.nvl(req.getParameter("addr2"));//상세주소
			/*
			 * 웹 에서 받는 정보를 String 변수에 저장 끝!
			 * 무조건 웹으로 받은 정보는 DTO에 저장하기 위해 임시로 String 변수에 저장함
			 * 값을 받았으면, 꼭 로그르 찍어서 값이 제대로 들어오는지 파악해야함
			 */
			log.info("user_id :"+user_id);
			log.info("user_name :"+user_name);
			log.info("password :"+password);
			log.info("email :"+email);
			log.info("addr1 :"+addr1);
			log.info("addr2 :"+addr2);
			/*
			 * 웹에서 받은 정보를 DTO에 저장하기 시작
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
			 */
			//웹에서 받은 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();
			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);
			//비밀번호는 절대로 복호화되지 않도록 해서 알고리즘으로 암호화함
			pDTO.setPassword(EncryptUtil.enHashSHA256(password));
			//민감 정보인 이메일은 AES128-CBC로 암호화함
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));
			pDTO.setAddr1(addr1);
			pDTO.setAddr2(addr2);
			/*
			 * 웹에서 받은 정보를 DTO에 저장하기 끝
			 * 무조건 웹으로 받은 정보는 DTO에 저장해야 한다고 이해하길 권함
			 */
			/*
			 * 회원가입
			 */
			int res = userInfoService.insertUserInfo(pDTO);
			if(res==1) {
				msg= "회원가입되었습니다.";
			//추후 회원가입 입력화면에서 ajax을 활요하여 아이디 중복, 이메일 중복을ㄹ 체크하길 바람
			}else if(res==2) {
				msg= "이미 가입된 이메일 주소입니다.";
			}else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";
			}
		}catch(Exception e) {
			//저장이 실패되면 사용자에게 보여줄 메시지
			msg = "실패하였습니다. :" + e.toString();
			log.info(e.toString());
			e.printStackTrace();
		}finally {
			log.info(this.getClass().getName() + ".insertUserInfo end!");
			//회원가입 여부 결과 메시지 전달힉
			model.addAttribute("msg",msg);
			//회원가입 여부 결과 메시지 전달하기
			model.addAttribute("pDTO", pDTO);
			//변수 초기화(메몰리 효율화 시키기 위해 사용함)
			pDTO = null;
		}
		return "/user/Msg";
	}
	@RequestMapping(value="user/Loginform")
	public String loginForm() {
		log.info(this.getClass().getName() + ".user/loginForm ok");
		return "/user/Loginform";
	}
	@RequestMapping(value="user/getUserLoginCheck")
	public String getUserLoginCheck(HttpSession session, HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws Exception{
		log.info(this.getClass().getName() + ".getUserLoginCheck start");
		int res = 0;
		UserInfoDTO pDTO = null;
		try {
			String user_id = CmmUtil.nvl(req.getParameter("user_id"));
			String password = CmmUtil.nvl(req.getParameter("password"));
			log.info("user_id : " + user_id);
			log.info("password : " + password);
			pDTO = new UserInfoDTO();
			pDTO.setUser_id(user_id);
			pDTO.setPassword(EncryptUtil.enHashSHA256(password));
			res = userInfoService.getUserLoginCheck(pDTO);
			if(res==1) {
				session.setAttribute("SS_USER_ID", user_id);
			}
		}catch(Exception e) {
			res=2;
			log.info(e.toString());
			e.printStackTrace();
		}finally {
			log.info(this.getClass().getName() + ".insertUserInfo end!");
			model.addAttribute("res", String.valueOf(res));
			pDTO = null;
		}
		return "/user/LoginResult";
	}
}
