<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="user/movie/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
$(function(){
	$('.res_time').click(function(){
		var time=$(this).text();
		$('#span_time').text("상영시간:"+time);
		sendMessage("POST", "inwon_info.do", null, inwonInfo)
	});
});
function inwonInfo()
{
	if(httpRequest.readyState==4)
	{
		if(httpRequest.status==200)
		{
			
			$('#res_inwon').html(httpRequest.responseText);
		}
	}
}
</script>
</head>
<body>
   <center>
     <table id="table_content" width=450>
       <tr>
         <th colspan="5">시간 정보</th>
       </tr>
       <tr>
         <c:forEach var="time" items="${list }">
           <td class="res_time dataTd">${time }</td>
         </c:forEach>
       </tr>
     </table>
   </center>
</body>
</html>




