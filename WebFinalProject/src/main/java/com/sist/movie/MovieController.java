package com.sist.movie;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.movie.dao.*;

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
		
		File file=new File("C:\\springDev\\springStudy\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\WebFinalProject\\images\\desc.txt");
		   if(file.exists())
			   file.delete();
		   for(int i=1;i<=3;i++)
		   {
		    String json=m.review_data(vo.getTitle(), i);
		    //System.out.println(json);
		    m.jsonParse(json);
		   }
		
		m.wordcloud();
		req.setAttribute("vo", vo);
		req.setAttribute("jsp", "movie/movie_detail.jsp");
		
		
		return "user/main.jsp";
	}
}
