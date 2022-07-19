<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management Application</title>
</head>
<body>
<header>
	<nav>
		<div>
			<a href="http://www.xadmin.net">User Management Application</a>
		</div>
		
		<ul>
			<li> <a href="<%=request.getContextPath()%>/list">User List</a></li>
		</ul>
	</nav>
</header>

<br>

<div>
	<div>
		<h3>List Of Users</h3>
		<hr>
		<div>
			<a href="<%=request.getContextPath()%>/new">Add New User</a>
		</div>
		<br>
		<table border="1" >
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>EMAIL</th>
			<th>COUNTRY</th>
			<th>ACTION</th>
		</tr>
		<c:forEach var="user" items="${listUser}">
		<tr>
			<td>${user.id}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
			<td>${user.country}</td>
			<td> 
			<a href="edit?id = ${user.id}">Edit</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="delete?id = ${user.id}">Delete</a>
			</td>			
		</tr>
		</c:forEach>
	</table>
	
	</div>
	

</div>
	
	

</body>
</html>