<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Bills - Quick E-Bill</title>
<div id="adminMenu-placeholder"></div>

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap')
	;

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
	padding: 20px 30px;
	background-color: #007bff;
	color: white;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.header h2 {
	font-size: 24px;
	margin: 0;
}

.header button {
	background-color: #ff4b2b;
	color: white;
	border: none;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
}

.header button:hover {
	background-color: #c82333;
}

.content {
	padding: 20px;
}

.content h2 {
	color: #007bff;
	font-size: 26px;
	margin-bottom: 20px;
	text-align: center;
}

.form-group {
	margin-bottom: 20px;
	display: flex;
	justify-content: center;
	gap: 15px;
}

.form-group input, .form-group select {
	padding: 10px;
	font-size: 14px;
	border: 1px solid #ddd;
	border-radius: 5px;
}

.form-group button {
	padding: 10px 20px;
	font-size: 14px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	background-color: #28a745;
	color: white;
	transition: background-color 0.3s ease;
}

.form-group button:hover {
	background-color: #218838;
}

table {
	width: 100%;
	max-width: 800px;
	margin: 20px auto;
	border-collapse: collapse;
	background: white;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

table th, table td {
	padding: 12px;
	text-align: center;
	border: 1px solid #ddd;
}

table th {
	background-color: #007bff;
	color: white;
	font-weight: bold;
}

table tbody tr:nth-child(even) {
	background-color: #f2f2f2;
}

table tbody tr:hover {
	background-color: #e9f4ff;
}

table td button {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 8px 12px;
	font-size: 12px;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

table td button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<%
	// Check if the user is authenticated

	String customerName= (String) session.getAttribute("customerName");
	
	String username = (String) session.getAttribute("username");

	Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

	if (isAuthenticated == null || !isAuthenticated) {

		// Redirect to login page if not authenticated

		request.getRequestDispatcher("loginHome.jsp").forward(request, response);

	}
	%>
	<!-- Main Content -->
	<div class="content">
		<h2>Manage Bills</h2>

		<!-- Add/Update/Delete Bill Form -->
		<form action="BillServlet" method="post">
			<div class="form-group">
				<input type="text" name="billId"
					placeholder="Bill ID (optional for Add)"> <input
					type="text" name="customerName" placeholder="Customer Name"
					required> <input type="text" name="amount"
					placeholder="Amount" required> <select name="status"
					required>
					<option value="">Select Status</option>
					<option value="Paid">Paid</option>
					<option value="Pending">Pending</option>
					<option value="Overdue">Overdue</option>
				</select>
				<button type="submit" name="action" value="Add">Add</button>
				<button type="submit" name="action" value="Update">Update</button>
				<button type="submit" name="action" value="Delete">Delete</button>
			</div>
		</form>

		<!-- Bills Table -->
		<table>
			<thead>
				<tr>
					<th>Bill ID</th>
					<th>Customer Name</th>
					<th>Amount</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>101</td>
					<td>John Doe</td>
					<td>$50</td>
					<td>Paid</td>
					<td><button>View</button></td>
				</tr>
				<tr>
					<td>102</td>
					<td>Jane Smith</td>
					<td>$100</td>
					<td>Pending</td>
					<td><button>View</button></td>
				</tr>
				<tr>
					<td>103</td>
					<td>Mark Taylor</td>
					<td>$75</td>
					<td>Overdue</td>
					<td><button>View</button></td>
				</tr>
			</tbody>
		</table>
	</div>
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
</body>
</html>
