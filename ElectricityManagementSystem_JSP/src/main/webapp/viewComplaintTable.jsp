<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>

<head>

<div id="menu-placeholder"></div>

<title>Complaint List</title>

<link rel="stylesheet" href="css/viewAllComplaintTable.css">

</head>

<body>
	<%
	// Check if the user is authenticated

	String customerName = (String) session.getAttribute("customerName");

	String username = (String) session.getAttribute("username");

	Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

	if (isAuthenticated == null || !isAuthenticated) {

		// Redirect to login page if not authenticated

		request.getRequestDispatcher("loginHome.jsp").forward(request, response);

	}
	%>

	<h2>Complaint Number:</h2>

	<form id="tableForm" action="ComplaintViewServlet" method="GET">

		<div class="table-container">

			<table>

				<thead>

					<tr>

						<th>ID</th>

						<th>Type</th>

						<th>Category</th>

						<th>Landmark</th>

						<th>Customer Name</th>

						<th>Problem</th>

						<th>Consumer ID</th>

						<th>Address</th>

						<th>Mobile Number</th>

						<th>Status</th>

					</tr>

				</thead>

				<tbody>

					<tr>

						<td>${complaint.complaintId}</td>

						<td>${complaint.complaintType}</td>

						<td>${complaint.category}</td>

						<td>${complaint.landmark}</td>

						<td>${complaint.customerName}</td>

						<td>${complaint.problem}</td>

						<td>${complaint.consumerId}</td>

						<td>${complaint.address}</td>

						<td>${complaint.mobileNumber}</td>

						<td>${complaint.status}</td>

					</tr>

				</tbody>

			</table>

		</div>

	</form>

	<script>

function loadMenu() {

  fetch('menu.jsp')

  .then(response => response.text())

  .then(data => {

    document.getElementById('menu-placeholder').innerHTML = data;

  });

}

document.addEventListener('DOMContentLoaded', loadMenu);

</script>

</body>

</html>