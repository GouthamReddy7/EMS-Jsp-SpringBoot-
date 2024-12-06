<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<title>Quick EBill - Register Complaint</title>

<link rel="stylesheet" href="css/register_complaint.css">
<div id="menu-placeholder"></div>

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

		<div class="title-bar">File New Complaint</div>

		<form id="complaintForm" action="ComplaintServlet" method="post">

			<div class="form-grid">

				<div class="form-group">

					<label>Complaint/Service Type:</label> <select id="complaintType"
						name="complaintType" required>

						<option value="">Select Type</option>

						<option value="STREET_LIGHT">STREET LIGHT RELATED</option>

						<option value="BILLING">BILLING RELATED</option>

						<option value="VOLTAGE">VOLTAGE RELATED</option>

						<option value="DISRUPTION">FREQUENT DISRUPTION</option>

						<option value="POLE">POLE RELATED</option>

					</select>

				</div>

				<div class="form-group">

					<label>Category:</label> <select id="category" name="category"
						required>

						<option value="">Select Category</option>

						<!-- Street Light Categories -->

						<optgroup label="Street Light Related">

							<option value="NOT SWITCHED ON">NOT SWITCHED ON</option>

							<option value="NOT SWITCHED OFF">NOT SWITCHED OFF</option>

							<option value="DAMAGED LIGHT">DAMAGED LIGHT</option>

							<option value="FLICKERING LIGHT">FLICKERING LIGHT</option>

							<option value="NEW LIGHT REQUEST">NEW LIGHT REQUEST</option>

						</optgroup>

						<!-- Billing Categories -->

						<optgroup label="Billing Related">

							<option value="HIGH BILL">HIGH BILL</option>

							<option value="METER READING ERROR">METER READING ERROR</option>

							<option value="BILL NOT RECEIVED">BILL NOT RECEIVED</option>

							<option value="DUPLICATE BILL">DUPLICATE BILL</option>

							<option value="REFUND REQUEST">REFUND REQUEST</option>

						</optgroup>

						<!-- Voltage Categories -->

						<optgroup label="Voltage Related">

							<option value="LOW VOLTAGE">LOW VOLTAGE</option>

							<option value="HIGH VOLTAGE">HIGH VOLTAGE</option>

							<option value="VOLTAGE FLUCTUATION">VOLTAGE FLUCTUATION</option>

							<option value="NO POWER SUPPLY">NO POWER SUPPLY</option>

							<option value="FREQUENT VOLTAGE SURGES">FREQUENT VOLTAGE
								SURGES</option>

						</optgroup>

						<!-- Disruption Categories -->

						<optgroup label="Frequent Disruption">

							<option value="POWER OUTAGE">POWER OUTAGE</option>

							<option value="FREQUENT OUTAGES">FREQUENT OUTAGES</option>

							<option value="UNSCHEDULED OUTAGES">UNSCHEDULED OUTAGES</option>

							<option value="LONG OUTAGE DURATION">LONG OUTAGE
								DURATION</option>

							<option value="AREA WIDE DISRUPTION">AREA WIDE
								DISRUPTION</option>

						</optgroup>

						<!-- Pole Categories -->

						<optgroup label="Pole Related">

							<option value="DAMAGED POLE">DAMAGED POLE</option>

							<option value="LEANING POLE">LEANING POLE</option>

							<option value="EXPOSED WIRES ON POLE">EXPOSED WIRES ON
								POLE</option>

							<option value="POLE REPLACEMENT">POLE REPLACEMENT</option>

							<option value="NEW POLE INSTALLATION">NEW POLE
								INSTALLATION</option>

						</optgroup>

					</select>

				</div>

				<div class="form-group">

					<label>Landmark:</label> <input type="text" id="landmark"
						name="landmark" placeholder="Landmark" required>

				</div>

				<div class="form-group">

					<label>Consumer Number:</label> <input type="text"
						id="consumerNumber" name="consumerNumber"
						placeholder="Consumer Number" maxlength="13" pattern="\d{13}"
						required>

				</div>

				<div class="form-group">

					<label class="required">Name:</label> <input type="text"
						id="contactPerson" name="contactPerson" placeholder="Name"
						required pattern="[A-Za-z\s]{5,30}" required>

				</div>

				<div class="form-group">

					<label>Problem Description:</label>

					<textarea id="problemDescription" name="problemDescription"
						placeholder="Describe your issue here" required></textarea>

				</div>

				<div class="form-group">

					<label class="required">Mobile Number:</label> <input type="text"
						id="mobileNumber" name="mobileNumber" placeholder="Mobile Number"
						maxlength="10" pattern="[7-9]{1}[0-9]{9}" required>

				</div>
				<div class="form-group">

					<label>Address:</label>

					<textarea id="address" name="address" placeholder="Address"
						required></textarea>

				</div>

			</div>

			<div class="button-group">

				<button type="reset" class="btn btn-cancel">Reset</button>

				<button type="submit" class="btn btn-submit">Submit
					Complaint</button>

			</div>

		</form>

	</div>
	<script src="js\registerComplaint.js"></script>
	
	<jsp:include page="footer.jsp" />
	
</body>

</html>