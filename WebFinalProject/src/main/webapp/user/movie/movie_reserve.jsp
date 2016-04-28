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
	sendMessage("POST", "movie_info.do", null, movieCallback);
	
	//sendMessage("POST", "theater_info.do", null, theaterCallback);
});
function movieCallback()
{
	if(httpRequest.readyState==4)
	{
		if(httpRequest.status==200)
		{
			//alert(httpRequest.responseText);
			$('#mi').html(httpRequest.responseText);
		}
	}
}

</script>
</head>
<body>
  <center>
     <h3>영화예매</h3>
     <table id="table_content" height=450 width=900>
       <tr>
         <td width=15% class="tdcenter" height=300 id="mi" valign="top"></td>
         <td width=20% class="tdcenter" height=300 id="ti" valign="top"></td>
         <td width=35% class="tdcenter" height=300 valign="top" id="di"></td>
         <td width=30% rowspan="2" class="tdleft" valign="top" height=500>
          <table id="table_content" width="270" height=500>
            <tr>
              <th>예매정보</th>
            </tr>
            <tr>
              <td class="tdcenter" id="poster_div"></td>
            </tr>
            <tr>
              <td class="tdcenter" id="span_title"></td>
            </tr>
            <tr>
              <td id="span_tname">극장명:</td>
            </tr>
            <tr>
              <td id="span_date">예약일:</td>
            </tr>
            <tr>  
              <td id="span_time">상영시간:</td>
            </tr>
            <tr>
              <td id="span_inwon">인원:</td>
            </tr>
            <tr>
              <td id="span_price">금액:</td>
            </tr>
            <tr>
              <td class="tdcenter">
                 <img src="images/res_d.png" border=0>
              </td>
            </tr>
          </table>
         </td>
       </tr>
       <tr>
         <td colspan="2" class="tdcenter" height=150 id="timei"></td>
         <td width=20% class="tdcenter" height=150 id="res_inwon">
           
         </td>
       </tr>
     </table>
  </center>
</body>
</html>





