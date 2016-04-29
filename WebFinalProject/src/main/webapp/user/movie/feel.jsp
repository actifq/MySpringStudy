<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="movie.css">
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">

$(function () {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '영화 감성 현황'
        },
        subtitle: {
            text: 'Source: <a href="http://www.sist.co.kr">SIST쌍용교육센터</a>'
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: '맑은 고딕'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Feel'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: 'Feel: <b>{point.y:.1f} count</b>'
        },
        series: [{
            name: 'Movie Feeling',
            data: [
                <c:forEach var="vo" items="${fList}">
                 ['<c:out value="${vo.word}"/>', <c:out value="${vo.count}"/>],
                </c:forEach>
            ],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                format: '{point.y:.1f}', // one decimal
                y: 10, // 10 pixels down from the top
                style: {
                    fontSize: '13px',
                    fontFamily: '맑은 고딕'
                }
            }
        }]
    });
});

</script>
</head>
<body>
  <center>
    <table id="table_content">
      <tr>
       <c:forEach var="vo" items="${list }">
        <td>
         <a href="#">
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
    </table>
    <table id="table_content">
     <tr>
      <td class="tdcenter">
       <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
      </td>
     </tr>
    </table>
  </center>
</body>
</html>