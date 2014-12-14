<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>WPattern Spring MVC</title>
</head>
<body>
	<c:choose>
		<c:when test="${contact.id == null}"><h2>Add Contact</h2></c:when>
		<c:when test="${contact.id != null}"><h2>Edit Contact</h2></c:when>
	</c:choose>
	
	<form:form method="post" action="/contact/save.html" modelAttribute="contact">
		<input name="id" value="${contact.id}" hidden="true"  />
		<label>First Name: </label> <input name="firstname" value="${contact.firstname}" /> <br/>
		<label>Last Name: </label> <input name="lastname" value="${contact.lastname}" /> <br/>
		<label>Email: </label> <input name="email" value="${contact.email}" /> <br/>
		<label>Phone: </label> <input name="phone" value="${contact.phone}" /> <br/>

		<br/>
		
		<input type="submit" name="action" value="Save" />
		<input type="submit" name="action" value="Cancel" />
	</form:form>
</body>
</html>
