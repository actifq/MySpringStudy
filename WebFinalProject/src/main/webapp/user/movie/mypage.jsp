<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
var i=0;
$(function(){
	$('.dataTr').click(function(){
		var data=$(this).attr("id");
		var no=data.substring(1);
		if(i==0)
		{
			$('#c'+no).show();
			i=1;
		}
		else
		{
			$('#c'+no).hide();
			i=0;
		}
	});
});
</script>
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
        <tr class="dataTr" id="m${vo.no }">
	       <td class="tdcenter">${vo.no }</td>
	       <td class="tdcenter">${vo.title }</td>
	       <td class="tdcenter">${vo.theater }</td>
	       <td class="tdcenter">${vo.day }</td>
	       <td class="tdcenter">${vo.time }</td>
	       <td class="tdcenter" >${vo.inwon }</td>
	       <td class="tdcenter">${vo.res_check==0?"������":"����Ϸ�" }</td>
	     </tr>
	     <tr id="c${vo.no }" style="display:none">
	       <td colspan="7">
	         <div style="margin-left:200px;width:300px;height:170px;background: lightblue;border-radius:5px 5px 5px 5px">
	           <center>���೻��</center>
	                           �����ȣ:${vo.no }<br>
	                           ��ȭ��:${vo.title }<br>
	                           �����:${vo.theater }<br>
	                           ����ð�:${vo.day }(${vo.time })<br>
	                           �����ο�:${vo.inwon }<br>
	                           ����ݾ�:${vo.price }<br>
	                           ������Ȳ:<font color=red>${vo.res_check==0?"������":"����Ϸ�"}</font>
	         </div>
	       </td>
	     </tr>
      </c:forEach>
     </table>
   </center>
</body>
</html>




