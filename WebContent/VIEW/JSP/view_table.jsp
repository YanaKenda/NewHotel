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

  
  <br>
   
 <form name="callTableForm" action="${pageContext.request.contextPath}/ShowTableController" method="post">
   <input type="text" id="tableNameEditTextID" name="tableNameEditText" value="${tableNameKey}">
   <input type="submit" name="showTableButton" value="Show table">
 </form>

 <table class="tableFromDatabase" border="3px">
	<caption>
		${tableNameKey}
	</caption>
	<c:set var="columnName" value="${columnNamesListKey}" />
    <c:forEach items="${columnName}" var="columnName" step="1">    
     	<th class="cell">
        	<c:out value="${columnName}"/>
      	</th>
    </c:forEach> 
	<c:choose>
		<c:when test="${stepKey > 0}">
     		<c:forEach var="i" begin="0" end="${stepKey - 1}">
     			<tr>
     			<c:set var="step" value="${stepKey}" />
     			<c:set var="value" value="${valuesListKey}" />
     			<c:set var="begin" value="${i}"/>
     				<c:forEach items="${value}" var="value" begin="${begin}" step="${stepKey}">   
      					<td class="cell">
          				<c:out value="${value}"/>
      					</td>
     				</c:forEach> 
     			</tr>
    		</c:forEach>
    	</c:when>
    	<c:otherwise>
    		<div>
    			Table is empty.
    		</div>
    	</c:otherwise>
    </c:choose> 
 </table> 

 
 <form name="addToTableForm" action="${pageContext.request.contextPath}/AddToTableController" method="post">
   <input type="hidden" name="updateTableNameEditText" value="${tableNameKey}">
   <input type="submit" name="addToTablePageLoadButton" value="Add" class="lookLikeLink">
 </form>
 
 <form name="refreshTableForm" action="${pageContext.request.contextPath}/RefreshTableController" method="post">
   <input type="hidden" name="updateTableNameEditText" value="${tableNameKey}">
   <input type="submit" name="refreshTableLoadButton" value="Refresh" class="lookLikeLink">
 </form>
 
  <form name="deleteFromTableForm" action="${pageContext.request.contextPath}/DeleteFromTableController" method="post">
   <input type="hidden" name="updateTableNameEditText" value="${tableNameKey}">
   <input type="submit" name="deleteFromTableLoadButton" value="Delete" class="lookLikeLink">
 </form>
 
 <form name="writeToPdfForm" action="${pageContext.request.contextPath}/PdfController" method="post">
 	<input type="submit" name="writeToPdfButton" value="Save to PDF docs" class="lookLikeLink">
 </form>
 
  <form name="writeToXslxForm" action="${pageContext.request.contextPath}/XlsxController" method="post">
 	<input type="submit" name="writeToXlsxButton" value="Save to XLSX doc" class="lookLikeLink">
  </form>
  
  <form name="writeToCsvForm" action="${pageContext.request.contextPath}/CsvController" method="post">
 	<input type="submit" name="writeToCsvButton" value="Save to CSV doc" class="lookLikeLink">
  </form>
 

 
 </div>
 </div>
 
   <div id="footer">
  	Yana Kenda, 2016
  </div>

</body>
</html>