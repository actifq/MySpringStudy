<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="movie.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['영화명', '예매율'],
          <c:forEach var="vo" items="${list}">
          ['<c:out value="${vo.title}"/>',  <c:out value="${vo.reserve}"/>],
          </c:forEach>
        ]);

        var options = {
          title: '예매현황'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
        
        var chart1 = new google.visualization.ColumnChart(document.getElementById("piechart1"));
        chart1.draw(data, options);

      }
    </script>

</head>
<body>
  <center>
    <table id="table_content">
      <tr>
       <c:forEach var="vo" items="${list }">
        <td>
         <a href="movie_detail.do?no=${vo.no }">
          <img src="${vo.image }" width="100" height="120" border=0></a>
        </td>
       </c:forEach>
      </tr>
      <tr>
       <c:forEach var="vo" items="${list }">
        <td>
         <font color=black><b>${vo.title }</b></font>
        </td>
       </c:forEach>
      </tr>
      <tr>
       <c:forEach var="vo" items="${list }">
        <td>
         <font color=black>${vo.grade }</font>
        </td>
       </c:forEach>
      </tr>
    </table>
    <table id="table_content">
     <tr>
      <td class="tdcenter">
      <div id="piechart" style="width: 450px; height: 550px;"></div>
      </td>
      <td class="tdcenter">
      <div id="piechart1" style="width: 450px; height: 550px;"></div>
      </td>
     </tr>
    </table>
  </center>
</body>
</html>




