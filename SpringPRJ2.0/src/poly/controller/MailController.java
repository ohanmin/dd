package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

@Controller
public class MailController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource(name = "MailService")
	private IMailService iMailService;
	/**
	 * 메일 발송하기
	 */
	@RequestMapping(value = "mail/sendMail", method = RequestMethod.GET)
	public String sendMail() {
		return "mail/sendMail";
	}
	@RequestMapping(value = "mail/sendMail", method = RequestMethod.POST)
	public String sendMail(HttpServletRequest req, HttpServletResponse resp, ModelMap model)
			throws Exception{
		log.info(this.getClass().getName() + "mail.sendMail start!");
		// 웹 URL로부터 전달받는 값들
		String toMail = CmmUtil.nvl(req.getParameter("toMail"));
		String title = CmmUtil.nvl(req.getParameter("title"));
		String contents = CmmUtil.nvl(req.getParameter("contents"));
		System.out.println("투메일1"+toMail);
		MailDTO pDTO = new MailDTO();
		pDTO.setToMail(toMail);
		pDTO.setTitle(title);
		pDTO.setContents(contents);
		int res = iMailService.doSendMail(pDTO);
		if(res==1) {
			log.info(this.getClass().getName() + "mail.sendMail success!!");
		}else {
			log.info(this.getClass().getName() + "mail.sendMail fail");
		}
		//메일 발송 결과를 JSP에 전달하기(데이터 전달시, 숫자보단 문자열이 컨트롤하기 
		model.addAttribute("res", String.valueOf(res));
		//로그 찍기
		log.info(this.getClass().getName()+"mail.sendMail end!");
		//함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/mailsendMailResult.jsp)
		return "/mail/sendMailResult";
		
	}
}
