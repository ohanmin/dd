package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import poly.dto.FoodDTO;
import poly.dto.MovieDTO;
import poly.persistance.mapper.IFoodMapper;
import poly.service.IFoodService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

public class FoodService implements IFoodService {
	@Resource(name = "FoodMapper")
	private IFoodMapper foodMapper;
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
		Iterator<Element> day = element.select("tbody tr td").iterator();
		Iterator<Element> food_nm = element.select("tbody tr td span").iterator();
		FoodDTO pDTO = null;
		while (day.hasNext()) {
			pDTO = new FoodDTO();
			pDTO.setCollect_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			String day = CmmUtil.nvl(day.next().text()).trim();
			pDTO.setDay(day.substring(3, rank.length()));
			pDTO.setFood_nm(CmmUtil.nvl(movie_name.next().text().trim()));
			pDTO.setMovie_reserve(CmmUtil.nvl(movie_reserve.next().text().trim()));
			pDTO.setScore(CmmUtil.nvl(score.next().text().trim()));
			pDTO.setOpen_day(CmmUtil.nvl(open_day.next().text().trim().substring(0, 10)));
			pDTO.setReg_id("admin");
			res += movieMapper.InsertMovieInfo(pDTO);
		}
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB end!");
		return res;
	}
}
