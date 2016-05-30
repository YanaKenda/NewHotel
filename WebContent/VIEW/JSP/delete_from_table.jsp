<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel</title>
<link type="text/css" rel="stylesheet" href="VIEW/CSS/table_style.css"/>
</head>
<body>
   
   <table class="navigationTable" border="3px">
	<tr>
     <th><a href="${pageContext.request.contextPath}/ShowTableController">View table</a></th>
     <th><a href="${pageContext.request.contextPath}/ShowAllTablesController">View all tables</a></th>
    </tr>
   </table>
   
   <c:choose>
   <c:when test="${numberOfPrimaryKeys > 0}">
    <form name="deleteFromTableForm" action="${pageContext.request.contextPath}/BufDeleteFromTableController" method="post">
		<input type="hidden" name="deleteCellKey" id="deleteCellID" value="0">
		<c:set var="primaryKey" value="${primaryKeysList}" />
    	<c:forEach items="${primaryKey}" var="primaryKey" step="1">    
        		<input type="submit" name="deleteFromTable" value="Delete with id: ${primaryKey}" class="screenWidth" onclick="document.getElementById('deleteCellID').value = '${primaryKey}'">
        		<br>
    	</c:forEach> 
    </form>
   </c:when>
   <c:otherwise>
   		<div class="middle">
   		${tableNameKey} is empty.
   		</div>
   </c:otherwise>
   </c:choose>
  
 
   
   
   
</body>
</html>