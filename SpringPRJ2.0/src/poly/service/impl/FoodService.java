package poly.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.FoodDTO;
import poly.dto.MailDTO;
import poly.persistance.mapper.IFoodMapper;
import poly.service.IFoodService;
import poly.service.IMailService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
@Service("FoodService")
public class FoodService implements IFoodService {
	@Resource(name = "FoodMapper")
	private IFoodMapper foodMapper;
	@Resource(name = "MailService")
	private IMailService mailService;
	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int getFoodInfoFromWEB() throws Exception {
		// TODO Auto-generated method stub
		// 로그 찍기(추후 찍은 로그를 통해 이함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB start");
		int res = 0; // 크롤링 결과(0보다 크면 크롤링 성공)
		// CGV
		String url = "https://www.kopo.ac.kr/kangseo/content.do?menu=262";
		// jsoup 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스 작성할 변수
		Document doc = null; //
		// 사이트 접속(http 프로토콜만 가능, https 프로토콜은 보안상 안됨)
		doc = Jsoup.connect(url).get();
		Elements element = doc.select("table.tbl_table");
		Iterator<Element> day = element.select("tbody>tr>td:first-child").iterator();
		Iterator<Element> food_nm = element.select("tbody>tr>td:nth-child(3)>span").iterator();
		FoodDTO pDTO = null;
		MailDTO mDTO = null;
		String contents = "";
		while (day.hasNext()) {
		pDTO = new FoodDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			String day_re = CmmUtil.nvl(day.next().text()).trim();
			pDTO.setDay(day_re);
			String foodName = CmmUtil.nvl(food_nm.next().text().trim());
			pDTO.setFood_nm(foodName);
			pDTO.setReg_id("admin");
			res += foodMapper.InsertFoodInfo(pDTO);	
			contents += day_re + " : " +foodName + "\n" ;
		}
		mDTO = new MailDTO();
		mDTO.setToMail("ohhanmin@naver.com");
		mDTO.setTitle("식단 정보");
		mDTO.setContents(contents);
		mailService.doSendMail(mDTO);
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB end!");
		return res;
	}
}
