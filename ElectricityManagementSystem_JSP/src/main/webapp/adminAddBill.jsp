<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Bills - Quick E-Bill</title>
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

.header .logout-btn {
	background-color: #ff4b2b;
	color: white;
	border: none;
	padding: 8px 15px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
	transition: background-color 0.3s ease;
}

.header .logout-btn:hover {
	background-color: #c82333;
}

/* Content Section */
.content {
	padding: 30px 25px;
	text-align: center;
}

.content h2 {
	color: #007bff;
	font-size: 26px;
	margin-bottom: 15px;
}

.content .instructions {
	font-size: 16px;
	color: #555;
	margin-bottom: 25px;
}

table {
	width: 90%;
	max-width: 800px;
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
	padding: 20px;
	text-align: left;
}

table th {
	background-color: #007bff;
	color: white;
	text-align: center;
	font-weight: 600;
}

table td {
	background-color: #f9f9f9;
}

table td input {
	width: 100%;
	padding: 3px;
	border: 1px solid #ddd;
	border-radius: 4px;
	font-size: 14px;
}

.button-group {
	display: flex;
	justify-content: center;
	gap: 15px;
	margin-top: 20px;
}

.button-group button {
	padding: 10px 20px;
	font-size: 16px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.add-bill-btn {
	background-color: #28a745;
	color: white;
}

.add-bill-btn:hover {
	background-color: #218838;
}

.nav-button {
	background-color: #007bff;
	color: white;
}

.nav-button:hover {
	background-color: #0056b3;
}

</style>
</head>
<body>
	<!-- Main Content -->
	<main class="content">
		<h2>Add Bills for Customers</h2>
		<p class="instructions">Please fill in the details below to add a bill for a customer.</p>

		<!-- Add Bills Form -->
		<form action="registerBillServlet" method="post">
		<table class="styled-table">

  <thead>

    <tr>

      <th>Consumer ID</th>

      <th>Due Amount</th>

      <th>Month</th>

    </tr>

  </thead>

  <tbody>

    <tr>

      <td>

        <input type="text" id="consumerId" name="consumerId" class="form-input" minlength="13" maxlength="13" placeholder="Enter Consumer ID" required>

      </td>

      <td>

        <input type="text" id="dueAmount" name="dueAmount" class="form-input" placeholder="Enter Due Amount" required>

      </td>

      <td>

        <select id="month" name="month" class="form-select" required>

          <option value="">--Select Month--</option>

          <option value="January">January</option>

          <option value="February">February</option>

          <option value="March">March</option>

          <option value="April">April</option>

          <option value="May">May</option>

          <option value="June">June</option>

          <option value="July">July</option>

          <option value="August">August</option>

          <option value="September">September</option>

          <option value="October">October</option>

          <option value="November">November</option>

          <option value="December">December</option>

        </select>

      </td>

    </tr>

  </tbody>

</table>



			<div class="button-group">
				<button type="submit" class="add-bill-btn">Add Bill</button>
				<button type="button" class="nav-button"
					onclick="window.location.href='adminHome.jsp';">Go Back to
					Home</button>
			</div>
		</form>
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
