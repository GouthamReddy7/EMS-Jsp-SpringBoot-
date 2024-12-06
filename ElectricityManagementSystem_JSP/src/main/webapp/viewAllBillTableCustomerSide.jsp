<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>

<head>
<div id="menu-placeholder"></div>

<title>bills of ConsumerID</title>

<link rel="stylesheet" href="css/viewAllBillTable.css">

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
	<h2>Bill List</h2>
	<form id="tableForm" action="billViewAllServlet" method="GET">
		<div class="table-container">
			<table>
				<thead>
					<tr>
						<th>Bill ID</th>
						<th>Consumer ID</th>
						<th>Due Amount</th>
						<th>Month</th>
						<th>Approved Status</th>
						<th>Customer Paid Status</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="bill" items="${billList}">
						<tr>
							<td>${bill.billId}</td>
							<td>${bill.consumerId}</td>
							<td>${bill.dueAmount}</td>
							<td>${bill.month}</td>
							<td>${bill.paymentApprovedStatus}</td>
							<td>${bill.paidStatus}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</form>
	<script>
	function loadMenu() 
	{
		fetch('menu.jsp')
		.then(response => response.text())		
		.then(data => {
		document.getElementById('menu-placeholder').innerHTML = data;
		});
	}
	document.addEventListener('DOMContentLoaded', loadMenu);
	</script>
<jsp:include page="footer.jsp" />

</body>

</html>