<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="user/movie/table.css">
</head>
<body>
   <center>
     <table id="table_content" width=450>
       <tr>
         <th colspan="5">시간 정보</th>
       </tr>
       <tr>
         <c:forEach var="time" items="${list }">
           <td>${time }</td>
         </c:forEach>
       </tr>
     </table>
   </center>
</body>
</html>




