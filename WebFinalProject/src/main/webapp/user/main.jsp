<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
       EL : �����͸� ��� 
        = ${requestScope.no1} = request.getAttribute("no1")
                         == key
          ${no1}
        = ${sessionScope.id} = session.getAttribute("id")
        = ${param.id} = request.getParameter("id")
       JSTL : �±� 
        = core (���,URL)
          <c:forEach>
          <c:if>
          <c:redirect>
        = fmt (��¥ ��ȯ)
          <fmt:formatDate>
 --%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="EUC-KR" />
        <title>��ȭ��� ����Ʈ</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="styles.css" rel="stylesheet" type="text/css" media="screen" />
        <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
        <script type="text/javascript">
         $(function(){
        	 $('#logBtn').click(function(){
        		 var id=$('#id').val();
        		 if(id.trim()=="")
        		 {
        			 $('#id').focus();
        			 return;
        		 }
        		 var pwd=$('#pwd').val();
        		 if(pwd.trim()=="")
        		 {
        		     $('#pwd').focus();
        		     return;
        		 }
        		 $('#logForm').submit();
        	 });
        	 $('#logoutBtn').click(function(){
        		$('#logoutForm').submit(); 
        	 });
         });
        </script>
    </head>
    <body>
    	<div id="wrap">
				<div id="menu">
				   
					<ul>
						<li><a href="main.do" class="active">Home</a></li>
						<c:if test="${sessionScope.id==null }">
						 <li><a href="join.do">ȸ������</a></li>
						</c:if>
						<c:if test="${sessionScope.id!=null }">
						 <li><a href="join_update.do">ȸ������</a></li>
						</c:if>
						<li><a href="movie.do">��ȭ</a></li>
						<li><a href="review.do">����</a></li>
						<c:if test="${sessionScope.id!=null}">
						  <li><a href="board_list.do">Ŀ�´�Ƽ</a></li>
						  <c:if test="${sessionScope.admin==0 }">
						   <li><a href="mypage.do">MyPage</a></li>
						  </c:if>
						  <c:if test="${sessionScope.admin==1 }">
						   <li><a href="admin.do">������Ȳ</a></li>
						  </c:if>
						</c:if>
					</ul>
					
					
				</div>
				<div style="text-align:right;color:black">
				 <c:if test="${sessionScope.id==null }">
				  <form method="post" action="login.do" id="logForm">
				   ID:<input type=text name=id size=12 id="id">
                   &nbsp;
                   Password:<input type=password name=pwd size=10 id="pwd">
                   <input type=button value="�α���" id="logBtn">
                  </form>
                 </c:if>	
                 <c:if test="${sessionScope.id!=null }">
                  <form method="post" action="logout.do" id="logoutForm">
				   ${sessionScope.id }(${sessionScope.admin==0?"�Ϲ�":"������" })�� ȯ���մϴ�!!&nbsp;
                   <input type=button value="�α׾ƿ�" id="logoutBtn">
                  </form>
                 </c:if>			 
				</div>
				<div id="top_padding"></div>
				<!-- content -->
				<jsp:include page="${jsp }"></jsp:include>
              
		</div>
    </body>
</html>



