<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel</title>
<link type="text/css" rel="stylesheet" href="VIEW/CSS/new_style.css"/>
</head>
<body>

 <div id="container">
 
 <div id="header">
 	Hotel agency	
 </div>

 <div id="menu">
	<ul class="menu-2">
             <li><a href="${pageContext.request.contextPath}/ShowTableController">View table</a></li>
             <li><a href="${pageContext.request.contextPath}/ShowAllTablesController">View all tables</a></li>
	</ul>
  </div>
 
 <div id="content">
  
  <form name="callTableForm" action="${pageContext.request.contextPath}/ShowTableController" method="post">
  	<input type="hidden" id="tableNameEditTextID" name="tableNameEditText" value="">
	<c:set var="tableName" value="${tableNamesListKey}" />
    <c:forEach items="${tableName}" var="tableName" step="1">    
     	<input type="submit" value="${tableName}" class="lookLikeLink" onclick="document.getElementById('tableNameEditTextID').value = '${tableName}'">
     	<br>
    </c:forEach>
  </form> 
 
  </div>
  </div>
  
  <div id="footer">
  	Kenda Yana, 2016
  </div>
  
  
</body>
</html>