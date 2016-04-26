package com.sist.movie;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.member.dao.MemberDAO;
import com.member.dao.MemberDTO;
import com.member.dao.ZipcodeDTO;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller("mc")
public class MemberController {
   @RequestMapping("login.do")
   public String memberLogin(HttpServletRequest req)
   {
	   String id=req.getParameter("id");
	   String pwd=req.getParameter("pwd");
	   // DB연동 
	   String res=MemberDAO.memberLogin(id, pwd);
	   String name="";
	   String admin="";
	   if(!(res.equals("NOID")||res.equals("NOPWD")))
	   {
		   StringTokenizer st=new StringTokenizer(res, "|");
		   name=st.nextToken();
		   admin=st.nextToken();
		   HttpSession session=req.getSession();
		   session.setAttribute("id", id);
		   session.setAttribute("name", name);
		   session.setAttribute("admin", admin);
	   }
	   req.setAttribute("res", res);
	   return "user/member/login.jsp";
   }
   @RequestMapping("logout.do")
   public String memberLogout(HttpServletRequest req)
   {
	   HttpSession session=req.getSession();
	   session.invalidate();
	   return "user/member/logout.jsp";
   }
   
   @RequestMapping("join.do")
   public String memberJoin(HttpServletRequest req){
	   req.setAttribute("jsp", "member/join.jsp");
	   return "user/main.jsp";
   }
   @RequestMapping("idcheck_ok.do")
   public String memberIdCheck(HttpServletRequest req){
	   
	   String id=req.getParameter("id");
	   int count=MemberDAO.memberIdCheck(id);
	   req.setAttribute("count", count);
	   
	   return "user/member/idcheck_ok.jsp";
   }
   
   @RequestMapping("postfind_ok.do")
   public String postfine_ok(HttpServletRequest req)
   throws Exception{
	   // android,ajax(js) ==> EUC-KR 인식을 못함.. UTF-8로만 받아야됨
	   req.setCharacterEncoding("UTF-8");
	   String dong=req.getParameter("dong");
	   List<ZipcodeDTO> list=MemberDAO.postfindAllData(dong);
	   int count=MemberDAO.postfindCount(dong);
	   req.setAttribute("list", list);
	   req.setAttribute("count", count);
	   return "user/member/postfind_ok.jsp";
   }
}





