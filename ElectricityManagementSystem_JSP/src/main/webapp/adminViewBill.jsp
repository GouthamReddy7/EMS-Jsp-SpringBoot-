<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/viewAllBilltableCustomer.css">
<div id="menu-placeholder"></div>

<title>bill Status</title>
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
	<div class="container">
		<h3 class="bill-heading">🔍 View Bills</h3>

		<!-- Form for searching bills -->

		<form id="billForm" method="GET">

			<div class="search-section">

				<h2 class="bill-heading">Bill ID:</h2>

				<input type="text" name="billNumber" id="billNumber" minlength="5" maxlength="5" class="input-box" placeholder="Enter your Bill Number" oninput="validateConsumerId(this)" />

				<h2 class="bill-heading">Consumer ID:</h2>

				<input type="text" name="consumerId" id="consumerId" minlength="13" maxlength="13" 
				    class="input-box" placeholder="Enter your Consumer ID" 
				    oninput="validateConsumerId(this)" />


			</div>

			<div class="bg-button">

				<button class="btn btn-reset" type="button" onclick="resetForm()">Reset</button>

				<button class="btn btn-submit" type="button" onclick="submitForm()">Get
					Status</button>

			</div>

		</form>
	</div>
	<script>
	function validateConsumerId(input) {
	    // Remove any non-numeric characters
	    input.value = input.value.replace(/\D/g, '');

	    // Check length constraints if necessary (already handled via HTML minlength/maxlength)
	    if (input.value.length > 13) {
	        input.value = input.value.substring(0, 13);
	    }
	}

	function loadMenu() 
	{

		fetch('adminMenu.jsp')
		
		.then(response => response.text())
		
		.then(data => {
		
		document.getElementById('menu-placeholder').innerHTML = data;
		
		});

	}
	document.addEventListener('DOMContentLoaded', loadMenu);
	function submitForm() {

		  const billNumber = document.getElementById('billNumber').value.trim();

		  const consumerId = document.getElementById('consumerId').value.trim();

		  const form = document.getElementById('billForm');

		  // Ensure only one field is filled
		  if (billNumber && consumerId) {
		    alert("Please enter only one field: either Bill Number or Consumer ID.");
		    return;
		  }
		  if (!billNumber && !consumerId) {

		    alert("Please enter a value in one of the fields.");

		    return;

		  }

		  // Dynamically set the form action based on the field filled

		  if (billNumber) {

		    form.action = "viewBillServlet?billNumber=" + encodeURIComponent(billNumber);

		  } else if (consumerId) {

		    form.action = "viewAllBillServlet?consumerId=" + encodeURIComponent(consumerId);

		  }

		  // Submit the form

		  form.submit();

		}


		function resetForm() {

		  document.getElementById('billForm').reset();

		}
</script>

</body>
</html>
