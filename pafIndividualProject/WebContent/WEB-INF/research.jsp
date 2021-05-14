

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Research Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>


</head>
<body>

<div class="container"><div class="row"><div class="col-6"> 

<h1>Research Information Management</h1>

<form id="formResearch" name="formResearch">
	User ID:
	<input id="userId" name="userId" type="text" class="form-control form-control-sm">
	<br>
	
	Name:
	<input id="rName" name="rName" type="text" class="form-control form-control-sm">
	<br>
	
	Description:
	<input id="rDesc" name="rDesc" type="text" class="form-control form-control-sm">
	<br>
	
	Status:
	<input id="rStatus" name="rStatus" type="text" class="form-control form-control-sm">
	<br>
	
	Budget:
	<input id="rBudget" name="rBudget" type="text" class="form-control form-control-sm">
	<br>

</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divResearchGrid">
 <%
 
 
 //FIND THE ERROR
 ResearchController newResearch = new ResearchController();
 
 out.print(newResearch.readItems()); 
 %>
</div>
</div> </div> </div> 

</body>
</html>