<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Complaints - Quick E-Bill</title>
<div id="adminMenu-placeholder"></div>

<style>
@import
	url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

/* Global Styles */
body {
	font-family: 'Montserrat', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f9f9f9;
	color: #333;
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 15px 30px;
	background-color: #007bff;
	color: white;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.header h2 {
	font-size: 24px;
	margin: 0;
}

.content {
	padding: 30px 15px;
}

.content h2 {
	color: #007bff;
	font-size: 24px;
	margin-bottom: 15px;
	text-align: center;
}

.filter-group {
	text-align: center;
	margin-bottom: 20px;
}

.filter-group select {
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ddd;
	border-radius: 5px;
	margin-right: 10px;
}

.filter-group button {
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	background-color: #007bff;
	color: white;
	transition: background-color 0.3s ease;
}

.filter-group button:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	max-width: 1000px;
	margin: 0 auto;
	border-collapse: collapse;
	margin-bottom: 20px;
	background: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
	overflow: hidden;
}

table th, table td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

table th {
	background-color: #007bff;
	color: white;
	text-align: center;
	font-weight: 600;
}

table td {
	text-align: center;
}

.no-data {
	text-align: center;
	margin: 20px;
	color: #666;
	font-size: 18px;
}
</style>
<script>
        // Filter complaints based on status
        function filterComplaints() {
            const status = document.getElementById("statusFilter").value;
            const rows = document.querySelectorAll("tbody tr");

            rows.forEach(row => {
                const complaintStatus = row.dataset.status;
                row.style.display = (status === "All" || status === complaintStatus) ? "" : "none";
            });
        }
    </script>
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
	<!-- Content -->
	<main class="content">
		<h2>Complaints Status</h2>
    <form action="UpdateComplaintStatusAdminServlet" method="get">

		<!-- Complaints Table -->
		<table>
				<thead>
					<tr>
						<th>Complaint ID</th>
						<th>Customer Name</th>
						<th>Problem</th>
						<th>Consumer ID</th>
						<th>Status</th>
						<th>Select</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="complaint" items="${complaintList}">
						<tr>
							<td>${complaint.complaintId}</td>
							<td>${complaint.customerName}</td>
							<td>${complaint.problem}</td>
							<td>${complaint.consumerId}</td>
							<td>${complaint.status}</td>
							<td><button type="submit" ><a href="UpdateComplaintStatusAdminServlet?complaintId=${complaint.complaintId}">Approve</a></button></td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
			</form>
      	
		<!-- No Data Message -->
		<div class="no-data" style="display: none;">No complaints found
			for the selected filter.</div>
	</main>
	<script>
    function loadMenu() {

    	fetch('adminMenu.jsp')

    		.then(response => response.text())

    		.then(data => {

    			document.getElementById('adminMenu-placeholder').innerHTML = data;

    		});
    }
    document.addEventListener('DOMContentLoaded', loadMenu);

    </script>
    	<jsp:include page="footer.jsp" />
    
</body>
</html>
