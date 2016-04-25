<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#writeBtn').click(function(){
		var name=$('#name').val();
		if(name.trim()=="")
		{
			$('#name').focus();
			return;
		}
		// val() => <input type=text> => value
		// val("ȫ�浿") <input type=text value="">
		// <a href="aaa" id="aa">bbbbb</a>  => $('#aa').attr("href");
		// $('#aa').text()
		// <a><b>bbbb</b></a> => html()
		var subject=$('#subject').val();
		if(subject.trim()=="")
		{
			$('#subject').focus();
			return;
		}
		var content=$('#content').val();
		if(content.trim()=="")
		{
			$('#content').focus();
			return;
		}
		var pwd=$('#pwd').val();
		if(pwd.trim()=="")
		{
			$('#pwd').focus();
			return;
		}
		
		$('#boardFrm').submit();
	});
});
</script>
</head>
<body>
   <center>
      <h3>�۾���</h3>
      <form method="post" action="board_insert_ok.do" id="boardFrm">
      <table id="table_content">
        <tr>
          <td align=right width=15%>�̸�</td>
          <td align=left width=85%>
           <input type=text name=name size=15 id="name">
          </td>
        </tr>
        <tr>
          <td align=right width=15%>����</td>
          <td align=left width=85%>
           <input type=text name=subject size=50 id="subject">
          </td>
        </tr>
        <tr>
          <td align=right width=15%>����</td>
          <td align=left width=85%>
           <textarea rows="10" cols="55" name=content id="content"></textarea>
          </td>
        </tr>
        <tr>
          <td align=right width=15%>��й�ȣ</td>
          <td align=left width=85%>
           <input type="password" name=pwd size=10 id="pwd">
          </td>
        </tr>
        <tr>
          <td align=center colspan="2">
           <input type=button value=�۾��� id="writeBtn">
           <input type=button value=��� 
           onclick="javascript:history.back()">
          </td>
        </tr>
      </table>
      </form>
   </center>
</body>
</html>
