document.addEventListener('DOMContentLoaded', loadMenu);



// Load the menu dynamically

function loadMenu() {

 fetch('menu.jsp')

  .then(response => response.text())

  .then(data => {

   document.getElementById('menu-placeholder').innerHTML = data;

  });

}



// Redirect to home page

function goHome() {

 window.location.href = 'home.jsp';

}



// Show card form when "Pay Now" is clicked

function showCardForm() {

 document.getElementById("cardForm").style.display = "block";

}



// Process payment form submission

function processPayment(event) {

 event.preventDefault();



 // Retrieve form values

 const cardNumber = document.getElementById("cardNumber").value.trim();

 const cardHolder = document.getElementById("cardHolder").value.trim();

 const expiryDate = document.getElementById("expiryDate").value.trim();

 const cvv = document.getElementById("cvv").value.trim();



 // Validate card number

 const cardNumberPattern = /^\d{16}$/;

 if (!cardNumberPattern.test(cardNumber) || /^0{16}$/.test(cardNumber) || cardNumber.endsWith("0000")) {

  showError("Invalid card number! It cannot be all zeros and the last four digits cannot be zero.");

  return;

 }



 // Validate card holder name

 if (cardHolder.length < 5 || cardHolder.length > 25) {

  showError("Card Holder Name must be between 5 and 25 characters.");

  return;

 }



 // Validate expiry date

 const currentDate = new Date();

 const [month, year] = expiryDate.split("/");

 const expiryYear = parseInt(`20${year}`, 10);

 const expiryMonth = parseInt(month, 10);



 if (

  isNaN(expiryYear) ||

  isNaN(expiryMonth) ||

  expiryMonth < 1 ||

  expiryMonth > 12 ||

  expiryYear < currentDate.getFullYear() ||

  (expiryYear === currentDate.getFullYear() && expiryMonth < currentDate.getMonth() + 1)

 ) {

  showError("Invalid expiry date! It must be in the future.");

  return;

 }



 // Validate CVV

 if (!/^\d{3}$/.test(cvv)) {

  showError("Invalid CVV! It must be a 3-digit number.");

  return;

 }



 // Generate transaction ID

 const transactionId = "TXN" + Math.floor(Math.random() * 1000000);



 // Display transaction success

 document.getElementById("cardForm").style.display = "none";

 document.getElementById("transactionSuccess").style.display = "block";



 // Generate receipt data

 const customerName = document.getElementById("customerName").textContent;

 const billAmount = document.getElementById("billAmount").textContent;

 const pgCharge = document.getElementById("pgCharge").textContent;

 const totalPayableAmount = document.getElementById("totalPayableAmount").textContent;

 const paymentMethod = document.querySelector('input[name="payment-method"]:checked').value;



 const receiptData = `

--- PAYMENT RECEIPT ---

Customer Name: ${customerName}

Bill Amount: ${billAmount}

PG Charge: ${pgCharge}

Total Payable Amount: ${totalPayableAmount}

Mode of Payment: ${paymentMethod}

Card Holder: ${cardHolder}

Transaction ID: ${transactionId}

Thank you for your payment!

 `;



 // Save receipt data in a hidden element for download

 document.getElementById("receiptData").value = receiptData;



 alert("Payment processed successfully!");

}



// Download receipt

function downloadReceipt() {

 const receiptData = document.getElementById("receiptData").value;

 const blob = new Blob([receiptData], { type: "text/plain" });

 const link = document.createElement("a");

 link.href = URL.createObjectURL(blob);

 link.download = "Payment_Receipt.txt";

 link.click();

}



// Show error message

function showError(message) {

 alert(message);

}