<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="table.css">
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
				<tr class="dataTr">
					<td calss="tdcenter">${vo.no }</td>
					<td calss="tdcenter">${vo.title }</td>
					<td calss="tdcenter">${vo.theater }</td>
					<td calss="tdcenter">${vo.day }</td>
					<td calss="tdcenter">${vo.time }</td>
					<td calss="tdcenter">${vo.inwon }</td>
					<td calss="tdcenter">
					 <a href="admin_ok.do?no=${vo.no}">${vo.res_check==0?"���δ��":"���οϷ�" }</a></td>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>