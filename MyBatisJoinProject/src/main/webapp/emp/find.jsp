<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="com.sist.join.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String empno=request.getParameter("empno");
    EmpVO vo=EmpDAO.empdeptJoinFindData(Integer.parseInt(empno));
    request.setAttribute("vo", vo); // ${}
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="table.css">
</head>
<body>
	<center>
		<h3>${vo.ename }님의 상세보기</h3>
		<table id="table_content" width="300">
			<tr>
				<td width=30% align=right>사번</td>
				<td width=70% align=left>${vo.empno }</td>
			</tr>
			
			<tr>
				<td width=30% align=right>이름</td>
				<td width=70% align=left>${vo.ename }</td>
			</tr>
			
			<tr>
				<td width=30% align=right>직위</td>
				<td width=70% align=left>${vo.job }</td>
			</tr>
			
			<tr>
				<td width=30% align=right>사수번호</td>
				<td width=70% align=left>${vo.mgr }</td>
			</tr>
			
			<tr>
       <td width=30% align=right>입사일</td>
       <td width=70% align=left>
         <fmt:formatDate value="${vo.hiredate }" pattern="yyyy-MM-dd"/>
       </td>
      </tr>
			
			<tr>
				<td width=30% align=right>급여</td>
				<td width=70% align=left>$${vo.sal }</td>
			</tr>
			
			<tr>
				<td width=30% align=right>성과급</td>
				<td width=70% align=left>$${vo.comm }</td>
			</tr>
			
			<tr>
				<td width=30% align=right>부서명</td>
				<td width=70% align=left>${vo.dvo.dname }</td>
			</tr>
			
			<tr>
				<td width=30% align=right>근무지</td>
				<td width=70% align=left>${vo.dvo.loc }</td>
			</tr>
		</table>
	</center>
</body>
</html>















