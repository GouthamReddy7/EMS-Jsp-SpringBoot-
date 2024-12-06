<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>

<div id="menu-placeholder"></div>

<title>Bill List</title>

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
	<h2>Bill Number:</h2>

	<form id="tableForm" action="viewBillServlet" method="GET">

		<div class="table-container">

			<table>

				<thead>

					<tr>
						<th>Bill ID</th>
						<th>Consumer ID</th>
						<th>Due Amount</th>
						<th>Month</th>
						<th>Status</th>
					</tr>

				</thead>

				<tbody>

					<tr>
						<td>${bill.billId}</td>
						<td>${bill.consumerId}</td>
						<td>${bill.dueAmount}</td>
						<td>${bill.month}</td>
						<td>${bill.paidStatus}</td>
					</tr>

				</tbody>

			</table>

		</div>

	</form>

	<script>

function loadMenu() {

  fetch('adminMenu.jsp')

  .then(response => response.text())

  .then(data => {

    document.getElementById('menu-placeholder').innerHTML = data;

  });

}

document.addEventListener('DOMContentLoaded', loadMenu);

</script>

</body>

</html>