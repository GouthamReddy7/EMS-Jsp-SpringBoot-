<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bean.Bill" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css\table.css">

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
	<div id="menu-placeholder"></div>

	<div class="content">
	<h2>View Unpaid Bill & Pay</h2>
		<div class="bill-table-container">
			
			<form action="ViewUnpaidBillsCustomerServlet" method="get">
			<table>
					<thead>
						<tr>
							<th>Bill ID</th>
							<th>Due Amount</th>
							<th>Month</th>
							<th>Customer Paid Status</th>
							<th>Payment</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bill" items="${billList}">
							<tr>
								<td>${bill.billId}</td>
								<td>${bill.dueAmount}</td>
								<td>${bill.month}</td>	
								<td>${bill.paidStatus}</td>
								<td><button type="button" ><a href="billPayment.jsp?billId=${bill.billId}&consumerId=${bill.consumerId}&dueAmount=${bill.dueAmount}&month=${bill.month}&paidStatus=${bill.paidStatus}">Pay Bill</a></button>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				
			</form>
		</div>
	</div>
	<jsp:include page="footer.jsp" />

	<script src="js\view_bill.js"></script>

</body>

</html>
