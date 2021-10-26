package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.service.IMovieService;

@Controller
public class MoiveController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource(name = "MovieService")
	private IMovieService movieService;
	@RequestMapping(value= "movie/getMovieInfoFromWEB", method=RequestMethod.GET)
	public String getMovieInfoFromWEB(HttpServletRequest req, HttpServletResponse resp,
			ModelMap model) throws Exception {
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB start!");
		int res = movieService.getMovieInfoFromWEB();
		model.addAttribute("res", String.valueOf(res));
		log.info(this.getClass().getName() + ".getMovieInfoFromWEB end");
		return "/movie/RankForWEB";
	}
}
