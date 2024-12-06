<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quick EBill - Bill Payment</title>
<link rel="stylesheet" href="css\bill_payment.css">

</head>
<body>
	<%
	String billId = request.getParameter("billId");
	String consumerId = request.getParameter("consumerId");
	String dueAmount = request.getParameter("dueAmount");
	String month = request.getParameter("month");
	String paidStatus = request.getParameter("paidStatus");
	%>
	<div id="menu-placeholder"></div>

	<div class="payment-container">
		<div class="bill-details">
			<h3>Bill Details</h3>

			<div class="bill-row">
				<span>Bill Amount</span> <span id="billAmount"><%= dueAmount%></span>
			</div>

			<div class="bill-row">
				<span>Total Payable Amount(incl. GST)</span> <span id="totalPayableAmount"><%= dueAmount%></span>
			</div>


			<br>

			<table style="width: 100%">
				<tr class="table-style">
					<th>Consumer Number</th>
					<th>Bill Number</th>
					<th>Payable Amount</th>
				</tr>
				<tr>
					<td><%= consumerId%></td>
					<td><%= billId%></td>
					<td><%= dueAmount%></td>
				</tr>
				<tbody id="billDetailsTable">
					<!-- Dynamically generated bill rows will go here -->
				</tbody>
			</table>

		</div>

		<div class="payment-methods">
			<h3>Select Payment Method</h3>
			<label> <input type="radio" name="payment-method"
				value="credit"> Credit Card
			</label> <label style="margin-left: 20px"> <input type="radio"
				name="payment-method" value="debit"> Debit Card
			</label>
		</div>

		<div class="button-group">
			<button onclick="showCardForm()" class="btn btn-primary">Pay Now</button>
			<button onclick="goHome()" class="btn btn-secondary">Back to
				Home</button>
		</div>

		<div id="cardForm" class="card-form">
			<h3>Enter Card Details</h3>
			<form id="paymentForm" onsubmit="processPayment(event)">
				<div class="form-group">
					<label>Card Number:</label> <input type="text" id="cardNumber"
						required minlength="16" maxlength="16" pattern="\d{16}">
				</div>
				<div class="form-group">
					<label>Card Holder Name:</label> <input type="text" id="cardHolder"
						required minlength="5">
				</div>
				<div class="form-group">
					<label>Expiry Date (MM/YY):</label> <input type="text"
						id="expiryDate" required pattern="(0[1-9]|1[0-2])\/([0-9]{2})"
						placeholder="MM/YY">
				</div>
				<div class="form-group">
					<label>CVV:</label> <input type="password" id="cvv" required
						minlength="3" maxlength="3" pattern="\d{3}">
				</div>
				<button type="submit" class="btn btn-primary">Make Payment</button>
			</form>
		</div>

		<div id="transactionSuccess" class="transaction-success">
			<h3>Payment Successful!</h3>
			<p>
				Transaction ID: <span id="transactionId"></span>
			</p>
			<button onclick="downloadReceipt()" class="btn btn-primary">Download
				Receipt</button>
		</div>
	</div>

	<script src="js\newbillPayment.js"></script>
</body>
</html>
