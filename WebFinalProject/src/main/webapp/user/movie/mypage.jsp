<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="user/table.css">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table id="table_content">
			<tr>
				<th>��ȣ</th>
				<th>��ȭ��</th>
				<th>����</th>
				<th>������</th>
				<th>�󿵽ð�</th>
				<th>�ο�</th>
				<th>������Ȳ</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<td>${vo.no }</td>
				<td>${vo.title }</td>
				<td>${vo.theater }</td>
				<td>${vo.day }</td>
				<td>${vo.time }</td>
				<td>${vo.inwon }</td>
				<td>${vo.res_check==0?"������":"����Ϸ�" }</td>
			</c:forEach>
		</table>
	</center>
</body>
</html>