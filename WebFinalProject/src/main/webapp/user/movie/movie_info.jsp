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
	$('.movie_name').click(function(){
		var img=$(this).attr("img");
		var title=$(this).text();
		$('#poster_div').html("");
		$('#poster_div').html("<img src='"+img+"' width=150 height=170>");
		$('#span_title').text("");
		$('#span_title').text(title);
		var param="mno="+$(this).attr("id");
		sendMessage("POST", "theater_info.do", param, theaterInfo)
	});
});
function theaterInfo()
{
	if(httpRequest.readyState==4)
	{
		if(httpRequest.status==200)
		{
			
			$('#ti').html(httpRequest.responseText);
		}
	}
}
</script>
</head>
<body>
  <center>
    <table id="table_content" style="width:135px">
      <tr>
        <th>영화정보</th>
      </tr>
      <c:forEach var="vo" items="${list }">
        <tr class="dataTr">
          <td class="movie_name" id="${vo.mno }" img="${vo.poster }">${vo.title }</td>
        </tr>
      </c:forEach>
    </table>
  </center>
</body>
</html>



