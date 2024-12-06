document.addEventListener('DOMContentLoaded', loadMenu);

function loadMenu() {

	fetch('menu.jsp')

		.then(response => response.text())

		.then(data => {

			document.getElementById('menu-placeholder').innerHTML = data;

		});
}

function goHome() {

 window.location.href = 'home.jsp';

}

function showCardForm() {

 document.getElementById("cardForm").style.display = "block";

}

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
  
   const transactionId = "TXN" + Math.floor(Math.random() * 1000000);

 }}