<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<!-- About pure http://purecss.io/tables/ -->
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
	<title>WPattern Spring MVC</title>
</head>
<body>
	<script type="text/javascript">
	var wsUri = "ws://" + document.location.host + "/ws/contact";
	var webSocket = new WebSocket(wsUri);

	webSocket.onmessage = function(evt) { onMessage(evt) };

	function onMessage(evt) {
	    var jsonObj = JSON.parse(evt.data);
    	var id = jsonObj.contact.id;
    	var firstname = jsonObj.contact.firstname;
		var lastname = jsonObj.contact.lastname;
		var email = jsonObj.contact.email;
		var phone = jsonObj.contact.phone;

	    if (jsonObj.action == "ADD") {
			addContact(id, firstname, lastname, email, phone);
		} else if (jsonObj.action == "EDIT") {
			editContact(id, firstname, lastname, email, phone);			
		} else if (jsonObj.action == "DELETE") {
			deleteContact(id);
		} else {
			alert("Option \"" + jsonObj.action + "\" not available!");
		}
	}

	function addContact(id, firstname, lastname, email, phone) {
		var contactTable = document.getElementById("contactTable");
		var rowCount = contactTable.rows.length;
		var row = contactTable.insertRow(rowCount);
		
		row.id = "contactTable_id" + id;

		var cellId = row.insertCell(0);
		cellId.innerHTML = id;

		var cellId = row.insertCell(1);
		cellId.innerHTML = firstname;

		var cellId = row.insertCell(2);
		cellId.innerHTML = lastname;

		var cellId = row.insertCell(3);
		cellId.innerHTML = email;

		var cellId = row.insertCell(4);
		cellId.innerHTML = phone;
	}

	function editContact(id, firstname, lastname, email, phone) {
		var tableTd = document.getElementById("contactTable_id" + id)
		
		tableTd.cells[0].innerHTML = id;
		tableTd.cells[1].innerHTML = firstname;
		tableTd.cells[2].innerHTML = lastname;
		tableTd.cells[3].innerHTML = email;
		tableTd.cells[4].innerHTML = phone;
	}

	function deleteContact(id) {
		var tableTd = document.getElementById("contactTable_id" + id);
		tableTd.parentNode.removeChild(tableTd);
	}
	</script>
        
	<h2>Real Time Contacts</h2>
	
	<table id="contactTable" class="pure-table pure-table-bordered">
		<thead>
			<tr>
				<th>Id</th>
				<th>First Name</th>
				<th>Last name</th>
				<th>Email</th>
				<th>Phone</th>
			</tr>
		</thead>
		</tbody>
			<c:forEach items="${contactForm.contacts}" var="contact">
				<tr id="contactTable_id${contact.id}">
					<td>${contact.id}</td>
					<td>${contact.firstname}</td>
					<td>${contact.lastname}</td>
					<td>${contact.email}</td>
					<td>${contact.phone}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
