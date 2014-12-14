<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>WPattern Spring MVC</title>
</head>
<body>      
	<h2>Contacts</h2>
	
	<table>
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last name</th>
			<th>Email</th>
			<th>Phone</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${contactForm.contacts}" var="contact">
			<tr id="tableContact_id${contact.id}">
				<td>${contact.id}</td>
				<td>${contact.firstname}</td>
				<td>${contact.lastname}</td>
				<td>${contact.email}</td>
				<td>${contact.phone}</td>
				<td> <a href="/contact/edit.html?id=${contact.id}">Edit</a> </td>
				<td> <a href="/contact/delete.html?id=${contact.id}">Delete</a> </td>
			</tr>
		</c:forEach>
	</table>
	
	<br/>
	
	<form:form action="/contact/add.html" method="get">
		<input type="submit" value="Add" />
	</form:form>
	
	<form:form action="/contact/listEdit.html" method="get">
		<input type="submit" value="List Edit" />
	</form:form>
</body>
</html>
