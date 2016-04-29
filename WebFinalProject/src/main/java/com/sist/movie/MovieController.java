package com.sist.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.movie.dao.*;
import com.reserve.dao.MovieDAO;
import com.reserve.dao.MovieInfoVO;
import com.reserve.dao.ReserveVO;
import com.reserve.dao.TheaterInfoVO;

import java.io.File;
import java.util.*;
import java.text.*;
@Controller("movie")
public class MovieController {
    @RequestMapping("movie.do")
    public String movie_main(HttpServletRequest req)
    {
    	MovieManager m=new MovieManager();
    	List<MovieDTO> list=m.movieAllData();
    	req.setAttribute("list", list);
    	req.setAttribute("jsp", "movie/movie_main.jsp");
    	return "user/main.jsp";
    }
    @RequestMapping("movie_detail.do")
    public String movie_detail(HttpServletRequest req)
    {
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
 	      for(String re:mList)
 	      {
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
    public String movie_reserve(HttpServletRequest req)
    {
    	req.setAttribute("jsp", "movie/movie_reserve.jsp");
    	return "user/main.jsp";
    }
    @RequestMapping("movie_info.do")
    public String movie_info(HttpServletRequest req)
    {
    	List<MovieInfoVO> list=MovieDAO.movieInfoData();
    	System.out.println("list:"+list.size());
    	req.setAttribute("list", list);
    	return "user/movie/movie_info.jsp";
    }
    @RequestMapping("theater_info.do")
    public String theater_info(HttpServletRequest req)
    {
    	String mno=req.getParameter("mno");
    	String theaterno=MovieDAO.theaterNumber(Integer.parseInt(mno));
    	StringTokenizer st=new StringTokenizer(theaterno, ",");
    	// 1,2,3,4,5
    	List<TheaterInfoVO> list=new ArrayList<TheaterInfoVO>();
    	while(st.hasMoreTokens())
    	{
    		TheaterInfoVO vo=MovieDAO.theaterInfoData(
    				Integer.parseInt(st.nextToken()));
    		list.add(vo);
    	}
    	req.setAttribute("list", list);
    	return "user/movie/theater_info.jsp";
    }
    @RequestMapping("reserve_date.do")
    public String reserve_date(HttpServletRequest req)
    {
    	String strYear=req.getParameter("year");
    	String strMonth=req.getParameter("month");
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-d");//09 
    	StringTokenizer st=new StringTokenizer(sdf.format(date), "-");
    	String sy=st.nextToken();
    	String sm=st.nextToken();
    	String sd=st.nextToken();
    	
    	if(strYear==null)
    		strYear=sy;
    	if(strMonth==null)
    		strMonth=sm;
    	
    	int year=Integer.parseInt(strYear);
    	int month=Integer.parseInt(strMonth);
    	int day=Integer.parseInt(sd);
    	
    	String[] strWeek={"일","월","화","수","목","금","토"};
    	int[] lastDay={
    		31,28,31,30,31,30,
    		31,31,30,31,30,31
    	};
    	
    	int total=((year-1)*365)+((year-1)/4)-((year-1)/100)
    			 +((year-1)/400);
    	if(((year%4==0)&&(year%100!=0))||(year%400==0))
    	{
    		lastDay[1]=29;
    	}
    	else
    	{
    		lastDay[1]=28;
    	}
    	
    	for(int i=0;i<month-1;i++)
    	{
    		total+=lastDay[i];
    	}
    	
    	total++;
    	int week=total%7;
    	System.out.println("week:"+strWeek[week]);
    	req.setAttribute("year", year);
    	req.setAttribute("month", month);
    	req.setAttribute("day", day);
    	req.setAttribute("week", week);
    	req.setAttribute("lastDay", lastDay[month-1]);
    	List<String> weekList=new ArrayList<String>();
    	for(int i=0;i<7;i++)
    	{
    		weekList.add(strWeek[i]);
    	}
    	req.setAttribute("weekList", weekList);
    	return "user/movie/reserve_date.jsp";
    }
    @RequestMapping("reserve_time.do")
    public String reserve_time(HttpServletRequest req)
    {
    	int[] temp=new int[5];
    	boolean bCheck=false;
    	int su=0;
    	for(int i=0;i<5;i++)
    	{
    		bCheck=true;
    		while(bCheck)
    		{
    			su=(int)(Math.random()*15)+1;
    			bCheck=false;
    			for(int j=0;j<i;j++)
    			{
    				if(temp[j]==su)
    				{
    					bCheck=true;
    					break;
    				}
    			}
    		}
    		temp[i]=su;
    	}
    	for(int i=0;i<temp.length-1;i++)
    	{
    		for(int j=i+1;j<temp.length;j++)
    		{
    			if(temp[i]>temp[j])
    			{
    				int a=temp[i];
    				temp[i]=temp[j];
    				temp[j]=a;
    			}
    		}
    	}
    	List<String> list=new ArrayList<String>();
    	for(int no:temp)
    	{
    		String time=MovieDAO.timeInfoData(no);
    		list.add(time);
    	}
    	req.setAttribute("list", list);
    	return "user/movie/reserve_time.jsp";
    }
    @RequestMapping("inwon_info.do")
    public String inwon_info(HttpServletRequest req)
    {
    	return "user/movie/reserve_inwon.jsp";
    }
    @RequestMapping("reserve_ok.do")
    public String reserve_ok(HttpServletRequest req)
    throws Exception
    {
    	req.setCharacterEncoding("EUC-KR");
    	String title=req.getParameter("title");
    	String theater=req.getParameter("theater");
    	String day=req.getParameter("day");
    	String time=req.getParameter("time");
    	String inwon=req.getParameter("inwon");
    	String price=req.getParameter("price");
    	/*System.out.println(title+"-"+theater+"-"+day+"-"
    			+time+"-"+inwon+"-"+price);*/
    	HttpSession session=req.getSession();
    	String id=(String)session.getAttribute("id");
    	ReserveVO vo=new ReserveVO();
    	vo.setId(id);
    	vo.setTitle(title);
    	vo.setTheater(theater);
    	vo.setTime(time);
    	vo.setDay(day);
    	vo.setInwon(Integer.parseInt(inwon));
    	vo.setPrice(Integer.parseInt(price));
    	MovieDAO.reserveInsert(vo);
    	//List<ReserveVO> list=MovieDAO.reserveUserAllData(id);
    	//req.setAttribute("list", list);
    	//req.setAttribute("jsp", "movie/mypage.jsp");
    	return "user/movie/reserve_ok.jsp";
    }
    
    @RequestMapping("mypage.do")
    public String mypage(HttpServletRequest req){
    	
    	HttpSession session=req.getSession();
    	String id=(String)session.getAttribute("id");
    	List<ReserveVO> list=MovieDAO.reserveUserAllData(id);
    	req.setAttribute("list", list);
    	req.setAttribute("jsp", "movie/mypage.jsp");
    	return "user/main.jsp";
    }
    
    @RequestMapping("admin.do")
    public String admin(HttpServletRequest req){
    	
    	List<ReserveVO> list=MovieDAO.reserveAdminAllData();
    	req.setAttribute("list", list);
    	req.setAttribute("jsp", "movie/admin.jsp");
    	return "user/main.jsp";
    }
    
    @RequestMapping("admin_ok.do")
    public String admin_ok(HttpServletRequest req){
    	
    	String no=req.getParameter("no");
    	MovieDAO.reserveOkUpdate(Integer.parseInt(no));

    	
    	
    	return "user/movie/admin_ok.jsp";
    }
    
    @RequestMapping("feel.do")
    public String feel(HttpServletRequest req){
    	try{
    		
    	
    	String no=req.getParameter("no");
    	MovieManager m=new MovieManager();
    	MovieDTO d=m.movieDetail(Integer.parseInt(no));
    	MovieMainClass.movieExecute(d.getTitle());
    	
    	List<FeelVO> fList=MovieMainClass.createFeelData();
    	List<MovieDTO> list=m.movieAllData();
    	req.setAttribute("fList", fList);
    	req.setAttribute("list", list);
    	req.setAttribute("jsp", "movie/feel.jsp");
    	
    	}catch(Exception ex){
    		System.out.println("feel:"+ex.getMessage());
    	}
    	return "user/main.jsp";
    }
    
    
}






