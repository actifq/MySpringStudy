package com.sist.movie;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.movie.dao.*;
import com.reserve.dao.MovieDAO;
import com.reserve.dao.MovieInfoVO;
import com.reserve.dao.TheaterInfoVO;

import java.io.File;
import java.util.*;

@Controller("movie")
public class MovieController {
	@RequestMapping("movie.do")
	public String movie_main(HttpServletRequest req){
		
		MovieManager m=new MovieManager();
		List<MovieDTO> list=m.movieAllData();
		
		req.setAttribute("list", list);
		req.setAttribute("jsp", "movie/movie_main.jsp");
		
		return "user/main.jsp";
	}
	
	@RequestMapping("movie_detail.do")
	public String movie_detail(HttpServletRequest req){
		String no=req.getParameter("no");
		
		MovieManager m=new MovieManager();
		MovieDTO vo=m.movieDetail(Integer.parseInt(no));
		List<String> list=new ArrayList<String>();
		File file=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebFinalProject\\images\\desc.txt");
		   if(file.exists())
			   file.delete();
		   for(int i=1;i<=3;i++)
		   {
		    String json=m.review_data(vo.getTitle(), i);
		    //System.out.println(json);
		    List<String> mList=m.jsonParse(json);
		    for(String re:mList){
		    	list.add(re);
		    	
		    }
		   }
		
		m.wordcloud();
		req.setAttribute("vo", vo);
		req.setAttribute("list", list);
		req.setAttribute("jsp", "movie/movie_detail.jsp");
		
		
		return "user/main.jsp";
	}
	
	@RequestMapping("movie_reserve.do")
	public String movie_reserve(HttpServletRequest req){
		req.setAttribute("jsp", "movie/movie_reserve.jsp");
		return "user/main.jsp";
	}
	
	@RequestMapping("movie_info.do")
	public String movie_info(HttpServletRequest req){
		
		List<MovieInfoVO> list=MovieDAO.movieInfoData();
		req.setAttribute("list", list);
		
		return "user/movie/movie_info.jsp";
	}
	
	@RequestMapping("theater_info.do")
	public String theater_info(HttpServletRequest req){
		List<TheaterInfoVO> list=MovieDAO.theaterInfoData();
		req.setAttribute("list", list);
		return "user/movie/theater_info.jsp";
	}
}


















