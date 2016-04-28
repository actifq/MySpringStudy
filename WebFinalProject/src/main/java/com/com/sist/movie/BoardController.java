package com.sist.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.board.dao.*;
import java.text.*;
@Controller("bc")
public class BoardController {
   @RequestMapping("board_list.do")
   public String board_list(HttpServletRequest req)
   {
	   String page=req.getParameter("page");
	   if(page==null)
		   page="1";
	   int curpage=Integer.parseInt(page);
	   Map map=new HashMap();
	   int rowSize=10;
	   int start=(curpage*rowSize)-rowSize;
	   int end=curpage*rowSize;
	   map.put("start", start);
	   map.put("end", end);
	   List<BoardVO> list=BoardDAO.boardAllData(map);
	   for(BoardVO vo:list)
	   {
		   vo.setReplyCount(BoardDAO.replyCount(vo.getNo()));
		   vo.setDbday(new SimpleDateFormat("yyyy-MM-dd").format(vo.getRegdate()));
	   }
	   int totalpage=BoardDAO.boardTotalPage();
	   
	   req.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	   req.setAttribute("curpage", curpage);
	   req.setAttribute("totalpage", totalpage);
	   req.setAttribute("list", list);
	   req.setAttribute("jsp", "board/board_list.jsp");
	   return "user/main.jsp";
   }
   @RequestMapping("board_content.do")
   public String board_content(HttpServletRequest req)
   {
	   String no=req.getParameter("no");
	   String page=req.getParameter("page");
	   BoardVO vo=BoardDAO.boardContentData(Integer.parseInt(no));
	   ////////////////////////////////  �Խ��� 
	   ////////////���/////////////////
	   String rpage=req.getParameter("rpage");
	   if(rpage==null)
		   rpage="1";
	   int cpage=Integer.parseInt(rpage);
	   int rowSize=10;
	   int start=(cpage*rowSize)-(rowSize-1);
	   int end=(cpage*rowSize);
	   Map map=new HashMap();
	   map.put("bno", no);
	   map.put("start", start);
	   map.put("end", end);
	   List<ReplyVO> list=BoardDAO.replyAllData(map);
	   req.setAttribute("list", list);
	   req.setAttribute("page", page);
	   req.setAttribute("vo", vo);
	   req.setAttribute("jsp", "board/board_content.jsp");
	   return "user/main.jsp";
   }
   @RequestMapping("board_insert.do")
   public String board_insert(HttpServletRequest req)
   {
	   req.setAttribute("jsp", "board/board_insert.jsp");
	   return "user/main.jsp";
   }
   @RequestMapping("board_insert_ok.do")
   public String board_insert_ok(HttpServletRequest req)
   throws Exception
   {
	   req.setCharacterEncoding("EUC-KR");
	   String name=req.getParameter("name");
	   String subject=req.getParameter("subject");
	   String content=req.getParameter("content");
	   String pwd=req.getParameter("pwd");
	   BoardVO vo=new BoardVO();
	   vo.setName(name);
	   vo.setSubject(subject);
	   vo.setContent(content);
	   vo.setPwd(pwd);
	   BoardDAO.boardInsert(vo);
	   
	   return "user/board/board_insert_ok.jsp";
   }
   @RequestMapping("reply_insert.do")
   public String reply_insert(HttpServletRequest req)
   throws Exception
   {
	   req.setCharacterEncoding("EUC-KR");
	   String page=req.getParameter("page");
	   String bno=req.getParameter("bno");
	   String msg=req.getParameter("reply_data");
	   // DB����
	   HttpSession session=req.getSession();
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   
	   ReplyVO vo=new ReplyVO();
	   vo.setBno(Integer.parseInt(bno));
	   vo.setId(id);
	   vo.setName(name);
	   vo.setMsg(msg);
	   
	   BoardDAO.replyInsert(vo);
	   
	   req.setAttribute("no", bno);
	   req.setAttribute("page", page);
	   return "user/board/reply_ok.jsp";
   }
   @RequestMapping("reply_update.do")
   public String reply_update(HttpServletRequest req)
   throws Exception
   {
	   req.setCharacterEncoding("EUC-KR");
	   String no=req.getParameter("no");
	   String page=req.getParameter("page");
	   String bno=req.getParameter("bno");
	   String msg=req.getParameter("reply_data");
	   
	   ReplyVO vo=new ReplyVO();
	   vo.setNo(Integer.parseInt(no));
	   vo.setMsg(msg);
	   
	   BoardDAO.replyUpdate(vo);
	   
	   req.setAttribute("no", bno);
	   req.setAttribute("page", page);
	   return "user/board/reply_ok.jsp";
   }
   @RequestMapping("reply_re_insert.do")
   public String reply_re_insert(HttpServletRequest req)
   throws Exception
   {
	   req.setCharacterEncoding("EUC-KR");
	   String no=req.getParameter("no");
	   String page=req.getParameter("page");
	   String bno=req.getParameter("bno");
	   String msg=req.getParameter("reply_data");
	   // DB ���� 
	   ReplyVO pvo=BoardDAO.replyParentData(Integer.parseInt(no));
	   BoardDAO.replyStepIncrement(pvo);
	   
	   HttpSession session=req.getSession();
	   String id=(String)session.getAttribute("id");
	   String name=(String)session.getAttribute("name");
	   ReplyVO vo=new ReplyVO();
	   vo.setMsg(msg);
	   vo.setName(name);
	   vo.setId(id);
	   vo.setBno(Integer.parseInt(bno));
	   vo.setRoot(Integer.parseInt(no));
	   vo.setGroup_id(pvo.getGroup_id());
	   vo.setGroup_step(pvo.getGroup_step()+1);
	   vo.setGroup_tab(pvo.getGroup_tab()+1);
	   BoardDAO.replyReInsert(vo);
	   BoardDAO.replyDepthIncrement(Integer.parseInt(no));
	   req.setAttribute("no", bno);
	   req.setAttribute("page", page);
	   return "user/board/reply_ok.jsp";
   }
   @RequestMapping("reply_delete.do")
   public String reply_delete(HttpServletRequest req)
   {
	   String no=req.getParameter("no");
	   String page=req.getParameter("page");
	   String bno=req.getParameter("bno");
	   // DB���� 
	   ReplyVO vo=BoardDAO.replyDeleteData(Integer.parseInt(no));
	   if(vo.getDepth()==0)
	   {
		   BoardDAO.replyDelete(Integer.parseInt(no));
		   BoardDAO.replyDepthDecrement(vo.getRoot());
	   }
	   else
	   {
		   BoardDAO.replyMsgUpdate(Integer.parseInt(no));
	   }
	   req.setAttribute("no", bno);
	   req.setAttribute("page", page);
	   return "user/board/reply_ok.jsp";
   }
   @RequestMapping("board_delete.do")
   public String board_delete(HttpServletRequest req)
   {
	   String no=req.getParameter("no");
	   String page=req.getParameter("page");
	   String pwd=req.getParameter("pwd");
	   
	   String db_pwd=BoardDAO.boardGetPwd(Integer.parseInt(no));
	   boolean bCheck=false;
	   if(db_pwd.equals(pwd))
	   {
		   bCheck=true;
		   BoardDAO.boardDelete(Integer.parseInt(no));
	   }
	   else
	   {
		   bCheck=false;
	   }
	   req.setAttribute("page", page);
	   req.setAttribute("bCheck", bCheck);
	   return "user/board/delete.jsp";
   }
}




