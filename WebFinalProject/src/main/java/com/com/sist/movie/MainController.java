package com.sist.movie;
import javax.servlet.http.HttpServletRequest;

import com.sist.controller.*;
import java.util.*;
import com.movie.dao.*;
@Controller("mc")
public class MainController {
   @RequestMapping("main.do")
   public String main_page(HttpServletRequest req)
   {
	   MovieManager m=new MovieManager();
	   List<MovieDTO> list=m.movieAllData();
	   List<String> rankList=m.movieRank();
	   List<String> boxList=m.movieBoxoffice();
	   List<String> rList=m.movieReserve();
	   req.setAttribute("list", list);
	   req.setAttribute("rankList",rankList );
	   req.setAttribute("rList",rList );
	   req.setAttribute("boxList",boxList );
	   req.setAttribute("jsp", "default.jsp");
	   return "user/main.jsp";
   }
}
