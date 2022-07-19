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
				<li> <a href="<%=request.getContextPath()%>/list">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div>
		<div>
			<div>
				<c:if test="${user != null}">
					<form action="update" method="post"></form>
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post"></form>
				</c:if>
				
				<caption>
					<h2>
						<c:if test="${user != null}">Edit User
						</c:if>
						<c:if test="${user == null}">Add New User
						</c:if>
					</h2>
				</caption>
				
				<c:if test="${user != null}">
					<input type="hidden" name="id" value="${user.id}"/>
				</c:if>
				<fieldset>
					<label>User Name</label><input type="text" value="${user.name}" 
					name="name" required="required"/>
				
				</fieldset>
				
				<fieldset>
					<label>User Email</label><input type="text" value="${user.email}" 
					name="email" />
				
				</fieldset>
				
				<fieldset>
					<label>User Country</label><input type="text" value="${user.country}" 
					name="country" />
				
				</fieldset>
				<button type="submit">Save</button>
				</form>
			</div>
		</div>
	
	</div>

</body>
</html>