function loadMenu() 
{

	fetch('menu.jsp')
	
	.then(response => response.text())
	
	.then(data => {
	
	document.getElementById('menu-placeholder').innerHTML = data;
	
	});

}
document.addEventListener('DOMContentLoaded', loadMenu);

// Function to validate payment form

function showCardForm() {

      document.getElementById("cardForm").style.display = "block";

    }
    //Above method may not work
// Function to validate payment form

// Function to validate payment form

function validatePaymentForm(event) {

 // Prevent form submission to validate first

 event.preventDefault();



 const cardNumber = document.getElementById("cardNumber").value;

 const cardHolder = document.getElementById("cardHolder").value;

 const expiryDate = document.getElementById("expiryDate").value;

 const cvv = document.getElementById("cvv").value;



 // Validate Card Number

 if (!/^\d{16}$/.test(cardNumber)) {

  alert("Card number must be exactly 16 digits.");

  return false;

 }

 if (/0000$/.test(cardNumber) || /^0{16}$/.test(cardNumber)) {

  alert("Card number cannot end with four zeros or contain all zeros.");

  return false;

 }



 // Validate Card Holder Name

 if (!/^[A-Za-z ]{5,22}$/.test(cardHolder)) {

  alert("Card holder name must be 5-22 characters long and contain only alphabets.");

  return false;

 }



 // Validate Expiry Date

 const expiryRegex = /^(0[1-9]|1[0-2])\/([0-9]{2})$/;

 if (!expiryRegex.test(expiryDate)) {

  alert("Expiry date must be in MM/YY format.");

  return false;

 }

 const [month, year] = expiryDate.split("/").map(Number);

 const currentYear = new Date().getFullYear() % 100; // Get the last two digits of the current year



 if (year < 24 || month < 1 || month > 12) {

  alert("Expiry date must not be earlier than 2024 and should be in MM/YY format.");

  return false;

 }



 // Validate CVV

 if (!/^\d{3}$/.test(cvv)) {

  alert("CVV must be exactly 3 digits.");

  return false;

 }



 // If all validations pass, submit the form

 document.getElementById("paymentForm").submit();

}