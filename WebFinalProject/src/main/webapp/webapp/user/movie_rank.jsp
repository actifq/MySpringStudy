<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <div class="box1">
           <h2>실시간 영화 순위</h2>
           <c:forEach var="data" items="${rankList }">
            <h4>${data }</h4>
           </c:forEach>
           <a href="#" class="read">Read more</a>
   </div>
</body>
</html>