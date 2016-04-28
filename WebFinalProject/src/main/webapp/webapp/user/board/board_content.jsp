<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var i=0;
var w=0;
var m=0;
$(function(){
	$('.reply_table').show();
	$('#reply_show').click(function(){
		if(i==0)
		{
		  $('#reply_show').text("��ۺ���");
		  $('.reply_table').hide();
		  i=1;
		}
		else
		{
		  $('#reply_show').text("��۴ݱ�");
		  $('.reply_table').show();
		  i=0;
		}
	});
	$('.reply_write').click(function(){
		var id=$(this).attr('id');
		var no=id.substring(1);// w1 w2 w3
		if(w==0)
		{
		   $('#ww'+no).show();
		   w=1;	
		}
		else
		{
		   $('#ww'+no).hide();
		   w=0;
		}
	});
	$('.reply_modify').click(function(){
		var id=$(this).attr('id');
		var no=id.substring(1);// w1 w2 w3
		if(m==0)
		{
		   $('#mm'+no).show();
		   m=1;	
		}
		else
		{
		   $('#mm'+no).hide();
		   m=0;
		}
	});
	$('#replyBtn').click(function(){
		var rd=$('#reply_data').val();
		if(rd.trim()=="")
		{
			$('#reply_data').focus();
			return;
		}
		$('#rifrm').submit();
	});
	$('.ruBtn').click(function(){
		var id=$(this).attr('id');
		var no=id.substring(3);
		var data=$('#rd'+no).val();
		if(data.trim()=="")
		{
			$('#rd'+no).focus();
			return;
		}
		$('#frm'+no).submit();
	});
	$('.riBtn').click(function(){
		var id=$(this).attr('id');
		var no=id.substring(4);
		var data=$('#d'+no).val();
		if(data.trim()=="")
		{
			$('#d'+no).focus();
			return;
		}
		$('#rfrm'+no).submit();
	});
});
</script>
</head>
<body>
  <center>
    <h3>���뺸��</h3>
    <table id="table_content">
      <tr>
        <td width=20% class="tdcenter">��ȣ</td>
        <td width=30% class="tdcenter">${vo.no }</td>
        <td width=20% class="tdcenter">�ۼ���</td>
        <td width=30% class="tdcenter">
          <fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
        </td>
      </tr>
      <tr>
        <td width=20% class="tdcenter">�̸�</td>
        <td width=30% class="tdcenter">${vo.name }</td>
        <td width=20% class="tdcenter">��ȸ��</td>
        <td width=30% class="tdcenter">${vo.hit }</td>
      </tr>
      <tr>
        <td width=20% class="tdcenter">����</td>
        <td colspan=3 class="tdleft">${vo.subject }</td>
      </tr>
      <tr>
        <td colspan=4 class="tdleft" valign="top" height=100>
          <pre>${vo.content }</pre>
        </td>
      </tr>
    </table>
    <table id="table_content">
      <tr>
        <td align="right">
          <a href="board_update.do?no=${vo.no }&page=${page}">
          <img src="user/board/image/btn_modify.gif" border=0></a>
          <img src="user/board/image/btn_delete.gif" border=0>
          <a href="board_list.do?page=${page }">
          <img src="user/board/image/btn_list.gif" border=0></a>
        </td>
      </tr>
      <tr>
       <td align=right>
        <form method="post" action="board_delete.do">
         <input type=hidden name="no" value="${vo.no }">
         <input type=hidden name="page" value="${page }">
         ��й�ȣ:<input type="password" name=pwd size=10>
         <button>����</button>
        </form>
       </td>
      </tr>
    </table>
    <div><a href="#" id="reply_show">��ۺ���</a></div>
    <table id="table_content" class="reply_table" style="display:none">
     <tr>
      <th colspan=2>���</th>
     </tr>
     <c:forEach var="rvo" items="${list }">
       <tr>
         <td width="75%" height="30">
          <c:if test="${rvo.group_tab>0 }">
           <c:forEach var="i" begin="1" end="${rvo.group_tab }">
            &nbsp;&nbsp;
           </c:forEach>
           <img src="user/board/image/icon_reply.gif">
          </c:if>
          ${rvo.msg }<br>
          <c:if test="${rvo.group_tab>0 }">
           <c:forEach var="i" begin="1" end="${rvo.group_tab }">
            &nbsp;&nbsp;
           </c:forEach>
          </c:if>
          <font color=blue>${rvo.name }</font>
          (${rvo.dbday })
         </td>
         <td width="25%" class="tdcenter">
           <img src="user/board/image/btn_reply.gif" id="w${rvo.no }" class="reply_write">
           <c:if test="${sessionScope.id==rvo.id }">
             <img src="user/board/image/btn_modify.gif" id="m${rvo.no }" class="reply_modify">
             <a href="reply_delete.do?no=${rvo.no }&bno=${vo.no}&page=${page}">
             <img src="user/board/image/btn_delete.gif"></a>
           </c:if>
         </td>
       </tr>
     <tr id="ww${rvo.no }" style="display:none">
      <td colspan="2">
        <form method="post" action="reply_re_insert.do" id="rfrm${rvo.no }">
         <input type="hidden" name="bno" value="${vo.no }">
         <input type="hidden" name="page" value="${page}">
         <input type="hidden" name="no" value="${rvo.no }">
         <textarea rows="2" cols="70" name="reply_data" style="float: left" id="d${rvo.no }"></textarea>
         <input type=button value="��۴ޱ�" style="height:60px" id="rBtn${rvo.no }" class="riBtn">
        </form>
      </td>
     </tr>
     <tr id="mm${rvo.no }" style="display:none">
      <td colspan="2">
       <form method="post" action="reply_update.do" id="frm${rvo.no }">
         <input type="hidden" name="bno" value="${vo.no }">
         <input type="hidden" name="page" value="${page}">
         <input type="hidden" name="no" value="${rvo.no }">
        <textarea rows="2" cols="70" name="reply_data" style="float: left" id="rd${rvo.no }">${rvo.msg }</textarea>
        <input type=button value="��ۼ���" style="height:60px" id="btn${rvo.no }" class="ruBtn">
       </form>
      </td>
     </tr>
     </c:forEach>
     <tr>
      <td colspan="2">
        <form method="post" action="reply_insert.do" id="rifrm">
         <input type="hidden" name="bno" value="${vo.no }">
         <input type="hidden" name="page" value="${page}">
         <textarea rows="2" cols="70" name="reply_data" style="float: left" id="reply_data"></textarea>
         <input type=button value="���" style="height:60px" id="replyBtn">
        </form>
      </td>
     </tr>
    </table>
  </center>
</body>
</html>
