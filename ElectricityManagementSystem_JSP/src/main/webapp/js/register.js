document.addEventListener('DOMContentLoaded', () => {

 const form = document.getElementById('registrationForm');

 form.addEventListener('submit', validateForm);

});

function validateForm(event) {

 // Prevent form submission

 event.preventDefault();

 // Get form field values

 const consumerID = document.getElementById("consumerID").value.trim();

 const billNumber = document.getElementById("billNumber").value.trim();

 const mobileNumber = document.getElementById("mobileNumber").value.trim();

 const email = document.getElementById("email").value.trim();

 const password = document.getElementById("password").value.trim();

 const confirmPassword = document.getElementById("confirmPassword").value.trim();

 const customerName = document.getElementById("customerName").value.trim();



 // Clear previous error messages

 document.querySelectorAll(".error-message").forEach(el => el.remove());



 let isValid = true;



 // Validate Customer Name

 if (!customerName || customerName.match(/^null$/i)) {

  showError("customerName", "Customer Name cannot be empty or 'null'.");

  isValid = false;

 } else if (!/^[A-Za-z\s]+$/.test(customerName)) {

  showError("customerName", "Customer Name can only contain letters and spaces.");

  isValid = false;

 }



 // Validate Consumer Number

 if (!/^\d{13}$/.test(consumerID)) {

  showError("consumerID", "Consumer Number must be exactly 13 digits.");

  isValid = false;

 } else if (/^0+$/.test(consumerID)) {

  showError("consumerID", "Consumer Number cannot be all zeros.");

  isValid = false;

 } else if (/0{5}$/.test(consumerID)) {

  showError("consumerID", "The last 5 digits of the Consumer Number cannot be all zeros.");

  isValid = false;

 }



 // Validate Bill Number

 if (!/^\d{5}$/.test(billNumber)) {

  showError("billNumber", "Bill Number must be exactly 5 digits.");

  isValid = false;

 } else if (/^0+$/.test(billNumber)) {

  showError("billNumber", "Bill Number cannot be all zeros.");

  isValid = false;

 }



 // Validate Mobile Number

 if (!/^[6-9]\d{9}$/.test(mobileNumber)) {

  showError("mobileNumber", "Mobile Number must be 10 digits and start with 7, 8, or 9.");

  isValid = false;

 }



 // Validate Email

 const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

 if (!emailRegex.test(email)) {

  showError("email", "Please enter a valid email address.");

  isValid = false;

 }



 // Validate Password

 const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{6,}$/;

 if (!passwordRegex.test(password)) {

  showError(

   "password",

   "Password must be at least 6 characters long, with one uppercase letter, one lowercase letter, one digit, and one special character."

  );

  isValid = false;

 }



 // Confirm Password

 if (password !== confirmPassword) {

  showError("confirmPassword", "Password and Confirm Password do not match.");

  isValid = false;

 }



 // Submit the form if valid

 if (isValid) {

  alert("Form submitted successfully!");

  document.getElementById('registrationForm').submit();

 }

}



function showError(fieldId, message) {

 const field = document.getElementById(fieldId);

 const errorMessage = document.createElement("div");

 errorMessage.className = "error-message";

 errorMessage.style.color = "red";

 errorMessage.style.marginTop = "5px";

 errorMessage.textContent = message;



 // Append error message after the field

 if (field && field.parentNode) {

  field.parentNode.appendChild(errorMessage);

 }

}