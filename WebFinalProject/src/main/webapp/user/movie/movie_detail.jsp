<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="movie.css">
</head>
<body>
  <center>
     <h3>${vo.title } �󼼺���</h3>
     <table id="table_content">
     	<tr>
     		<td align=right>
     			<a href="">
     				<img src="images/btn_buy.gif"></a>
     		</td>
     	</tr>
     </table>
     <table id="table_content">
       <tr>
        <td width=30% class="tdcenter" rowspan="6">
          <img src="${vo.image }">
        </td>
        <th colspan=2>${vo.title }</th>
       </tr>
       <tr>
        <td width=20% class="tdcenter">����</td>
        <td width=50% class="tdleft">${vo.regdate }����</td>
       </tr>
       <tr>
        <td width=20% class="tdcenter">���</td>
        <td width=50% class="tdleft">${vo.grade }</td>
       </tr>
       <tr>
        <td width=20% class="tdcenter">������</td>
        <td width=50% class="tdleft">${vo.reserve }%</td>
       </tr>
       <tr>
        <td width=20% class="tdcenter">��ũ</td>
        <td width=50% class="tdleft">${vo.rank }��</td>
       </tr>
       <tr>
        <td width=20% class="tdcenter">��</td>
        <td width=50% class="tdleft">${vo.like }</td>
       </tr>
     </table>
     <table id="table_content">
     	<tr>
     		<td class="tdcenter">
				<img src="images/desc.png">     		
     		</td>
     	</tr>
     </table>
  </center>
</body>
</html>





