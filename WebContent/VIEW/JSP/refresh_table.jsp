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
   <form name="refreshTableForm" action="${pageContext.request.contextPath}/BufRefreshTableController" method="post">
	<c:set var="columnInfo" value="${columnInfoListKey}" />
	<c:set var="count" value="0" />
    <c:forEach items="${columnInfo}" var="columnInfo" step="1"> 
      	<a class="middle"> ${columnInfo}  </a>
   		<input type="text" class="screenWidth" name="${count}"> 
   		<c:set var="count" value="${count + 1}" scope="page"/> 
    </c:forEach> 
    
    <input type="hidden" name="cellsNumber" id="cellsNumberID" value="${count}">
    <input type="hidden" name="refreshCellKey" id="refreshCellID" value="0">
	
	<c:set var="primaryKey" value="${primaryKeysList}" />
    <c:forEach items="${primaryKey}" var="primaryKey" step="1">    
        <input type="submit" name="refreshTable" value="Refresh with id: ${primaryKey}" class="screenWidth" onclick="document.getElementById('refreshCellID').value = '${primaryKey}'">
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