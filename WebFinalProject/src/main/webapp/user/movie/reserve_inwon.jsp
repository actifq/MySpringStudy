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
</head>
<body>
  <center>
    <table id="table_content" width=180>
            <tr>
              <th colspan="2">�ο�</th>
            </tr>
            <tr>
              <td>
                                   �ο�:
                 <select id="grade">
                   <c:forEach var="i" begin="1" end="5">
                    <option>${i }</option>
                   </c:forEach>
                 </select>
              </td>
            </tr>
         </table>
  </center>
</body>
</html>