<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var i=0;
$(function(){
	$('.dataTr').click(function(){
		var data=$(this).attr("id");
		var no=data.substring(1);
		if(i==0)
		{
			$('#c'+no).show();
			i=1;
		}
		else
		{
			$('#c'+no).hide();
			i=0;
		}
	});
});
</script>
</head>
<body>
   <center>
     <table id="table_content">
      <tr>
       <th>번호</th>
       <th>영화명</th>
       <th>극장</th>
       <th>예약일</th>
       <th>상영시간</th>
       <th>인원</th>
       <th>예약현황</th>
      </tr>
      <c:forEach var="vo" items="${list }">
        <tr class="dataTr" id="m${vo.no }">
	       <td class="tdcenter">${vo.no }</td>
	       <td class="tdcenter">${vo.title }</td>
	       <td class="tdcenter">${vo.theater }</td>
	       <td class="tdcenter">${vo.day }</td>
	       <td class="tdcenter">${vo.time }</td>
	       <td class="tdcenter" >${vo.inwon }</td>
	       <td class="tdcenter">${vo.res_check==0?"예약대기":"예약완료" }</td>
	     </tr>
	     <tr id="c${vo.no }" style="display:none">
	       <td colspan="7">
	         <div style="margin-left:200px;width:300px;height:170px;background: lightblue;border-radius:5px 5px 5px 5px">
	           <center>예약내역</center>
	                           예약번호:${vo.no }<br>
	                           영화명:${vo.title }<br>
	                           극장명:${vo.theater }<br>
	                           예약시간:${vo.day }(${vo.time })<br>
	                           예약인원:${vo.inwon }<br>
	                           예약금액:${vo.price }<br>
	                           예약현황:<font color=red>${vo.res_check==0?"예약대기":"예약완료"}</font>
	         </div>
	       </td>
	     </tr>
      </c:forEach>
     </table>
   </center>
</body>
</html>




