<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="ISO-8859-1">

<title>User Registration</title>

<link rel="stylesheet" href="css/c_registration.css">

<script src="js/register.js"></script>

</head>

<body>

	<header> </header>

	<div class="main-registration-card">
		<h1 class="main-heading">Quick EBill</h1>

		<form id="registrationForm" method="post" action="CustomerServlet" onsubmit="validateform()">

			<!-- Consumer Details Section -->

			<div class="section">

				<h3 class="section-heading">Consumer Details</h3>

				<div class="form-group">

					<label for="consumerID">Consumer Number:</label> <input type="text"
						id="consumerID" name="consumerID" minlength="13" maxlength="13"
						placeholder="Consumer Number" required>

				</div>

				<div class="form-group">

					<label for="billNumber">Bill Number:</label> <input type="text"
						id="billNumber" name="billNumber" minlength="5" maxlength="5"
						placeholder="Bill Number" required>

				</div>

			</div>
			<!-- Customer Details Section -->

			<div class="section">

				<h3 class="section-heading">Customer Details</h3>

				<div class="form-group">

					<label for="title">Title:</label> <select id="title" name="title"
						required>

						<option value="">--Select Title--</option>

						<option value="Mr">Mr</option>

						<option value="Mrs">Mrs</option>

						<option value="Ms">Ms</option>

					</select>

				</div>

				<div class="form-group">

					<label for="customerName">Customer Name:</label> <input type="text"
						id="customerName" name="customerName" maxlength="50"
						placeholder="Enter Name" required>

				</div>

				<div class="form-group">

					<label for="email">Email:</label> <input type="email" id="email"
						name="email" placeholder="Enter Email" required>

				</div>

				<div class="form-group">

					<label>Mobile Number: <span class="required">*</span></label>

					<div style="display: flex; align-items: center;">

						<select id="mobileCode" name="mobileCode"
							style="width: 90px; height: 100%; border: 1px solid #ddd; border-radius: 5px;">
							<option value="+91" selected>+91 (India)</option>
							<option value="+1">+1 (US)</option>
							<option value="+61">+61 (Australia)</option>
						</select> <input type="text" id="mobileNumber" name="mobileNumber"
							placeholder="10-digit Mobile No" maxlength="10" required
							style="flex-grow: 1; margin-left: 5px; border: 1px solid #ddd; border-radius: 5px; padding: 8px;">
					</div>
				</div>
			</div>
			<!-- Account Details Section -->

			<div class="section">

				<h3 class="section-heading">Account Details</h3>

				<div class="form-group">

					<label for="userID">User ID:</label> <input type="text" id="userID"
						name="userID" minlength="5" maxlength="20"
						placeholder="Enter User ID" required>

				</div>
				
				<div class="form-group">

					<label for="passwordHint">Favourite Place:</label> <input type="text" id="passwordHint"
						name="passwordHint"
						placeholder="Enter Password Hint" required>

				</div>
				

				<div class="form-group">

					<label for="password">Password:</label> <input type="password"
						id="password" name="password" minlength="6"
						placeholder="Enter Password" required>

				</div>

				<div class="form-group">

					<label for="confirmPassword">Confirm Password:</label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						minlength="6" placeholder="Confirm Password" required>

				</div>

			</div>

			<!-- Form Actions -->

			<div class="button-container">
				<button class="button button-register"
					onclick="window.location.href='loginHome.jsp'">Go Back</button>
				<button type="reset" class="button button-reset">Reset</button>
				 <button type="submit" class="button button-register" name="action" value="register">Register</button>
			</div>
		</form>
	</div>
	
</body>

</html>