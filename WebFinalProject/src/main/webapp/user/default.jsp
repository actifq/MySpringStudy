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
  <div id="prew_img">
				
	       <ul class="round">
	        <c:forEach var="vo" items="${list }">
			 <li><img src="${vo.image }" alt="" /></li>
			</c:forEach>
           </ul>
<script type="text/javascript" src="lib/jquery.js"></script>
<script type="text/javascript" src="lib/jquery.roundabout.js"></script>
<script type="text/javascript">
			
			$(document).ready(function() {
				$('.round').roundabout();
			});
		
		</script>
				
				</div>
				<div id="content_top"></div>				
				<div id="content_bg_repeat">
					
					<div class="inside">
            	<div class="row-1 outdent">
              	<div class="wrapper">
              	  <div class="metam1">
                  	<!-- .box1 -->
                  	<jsp:include page="movie_rank.jsp"></jsp:include>
                  	<!-- /.box1 -->
                  </div>
              	  <div class="metam2">
                  	<!-- .box1 -->
                  	<jsp:include page="movie_reserve.jsp"></jsp:include>
                  	<!-- /.box1 -->
                  </div>
              	  <div class="metam3">
                  	<!-- .box1 -->
                  	<jsp:include page="movie_boxoffice.jsp"></jsp:include>
                  	<!-- /.box1 -->
                  </div>
              	</div>
              </div>
</body>
</html>