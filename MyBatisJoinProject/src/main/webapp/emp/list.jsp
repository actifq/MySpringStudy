<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,com.sist.join.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    List<EmpVO> list=EmpDAO.empdeptJoinAllData();
    request.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="ajax.js"></script>
<script type="text/javascript">
function find(empno)
{
	// find.jsp?empno=empno
	sendMessage("GET","find.jsp", "empno="+empno, findCallback);
}
function findCallback()
{
	if(httpRequest.readyState==4)
	{
		if(httpRequest.status==200)
		{
			$('#detail').html(httpRequest.responseText);
		}
	}
}
</script>
</head>
<body>
  <center>
     <h3>사원 목록</h3>
     <table border=0 width=900 height=350>
     
      <tr>
      
       <td width=600>
       <div style="overflow: auto; width: 630px;height: 350px">
       <table id="table_content" width=600>
       <tr>
        <th>사번</th>
        <th>이름</th>
        <th>직위</th>
        <th>입사일</th>
        <th>근무지</th>
        <th>부서명</th>
       </tr>
       <c:forEach var="vo" items="${list }">
         <tr class="dataTr">
          <td>${vo.empno }</td>
          <td><a href="javascript:find(${vo.empno })">${vo.ename }</a></td>
          <td>${vo.job }</td>
          <td>
           <fmt:formatDate value="${vo.hiredate }" 
            pattern="yyyy-MM-dd"/></td>
          <td>${vo.dvo.dname }</td>
          <td>${vo.dvo.loc }</td>
         </tr>
       </c:forEach>
       </table>
       </div>
       </td>
       
       <td width=300 id="detail"></td>
      </tr>
     </table>
   
  </center>
</body>
</html>




