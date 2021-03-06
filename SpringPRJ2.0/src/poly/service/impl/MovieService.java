package poly.service.impl;

import java.util.Iterator;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.MovieDTO;
import poly.persistance.mapper.IMovieMapper;
import poly.service.IMovieService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
@Service("MovieService")
public class MovieService implements IMovieService{
	@Resource(name="MovieMapper")
	private IMovieMapper movieMapper;
	//로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * Jsoup 라이브러리를 통한 CGV 영화 순위 정보가져오기
	 */
	@Override
	public int getMovieInfoFromWEB() throws Exception {
		// TODO Auto-generated method stub
		// 로그 찍기(추후 찍은 로그를 통해 이함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB start");
		int res = 0; //크롤링 결과(0보다 크면 크롤링 성공)
		//CGV 
		String url = "http://www.cgv.co.kr/movies/";
		// jsoup 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML 소스 작성할 변수
		Document doc = null; //
		//사이트 접속(http 프로토콜만 가능, https 프로토콜은 보안상 안됨)
		doc = Jsoup.connect(url).get();
		Elements element = doc.select("div.sect-movie-chart");
		Iterator<Element> movie_rank= element.select("strong.rank").iterator();
		Iterator<Element> movie_name = element.select("strong.title").iterator();
		Iterator<Element> movie_reserve= element.select("strong.percent span").iterator();
		Iterator<Element> score= element.select("span.percent").iterator();
		Iterator<Element> open_day= element.select("span.txt-info").iterator();
		MovieDTO pDTO = null;
		while(movie_rank.hasNext()) {
			pDTO = new MovieDTO();
			pDTO.setRank_chkeck_time(DateUtil.getDateTime("yyyyMMdd24hmmss"));
			String rank = CmmUtil.nvl(movie_rank.next().text()).trim();
			pDTO.setMovie_rank(rank.substring(3,rank.length()));
			pDTO.setMovie_nm(CmmUtil.nvl(movie_name.next().text().trim()));
			pDTO.setMovie_reserve(CmmUtil.nvl(movie_reserve.next().text().trim()));
			pDTO.setScore(CmmUtil.nvl(score.next().text().trim()));
			pDTO.setOpen_day(CmmUtil.nvl(open_day.next().text().trim().substring(0, 10)));
			pDTO.setReg_id("admin");
			res += movieMapper.InsertMovieInfo(pDTO);
		}
		log.info(this.getClass().getName()+".getMovieInfoFromWEB end!");
		return res;
	}

}
