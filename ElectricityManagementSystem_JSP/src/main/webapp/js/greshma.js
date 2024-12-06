function validateForm(event) {



 console.log("Form validation started");

 // Get form field values

 const consumerID = document.getElementById("consumerID").value.trim();

 const billNumber = document.getElementById("billNumber").value.trim();

 const mobileNumber = document.getElementById("mobileNumber").value.trim();

 const email = document.getElementById("email").value.trim();

 const password = document.getElementById("password").value.trim();

 const confirmPassword = document.getElementById("confirmPassword").value.trim();

 const customerName = document.getElementById("customerName").value.trim();

 let isValid = true;

 // Customer Name Validation

 const namePattern = /^[A-Za-z\s]+$/;

 const invalidNamePattern = /^null$/i; // Matches "null", "NULL", "Null", etc.

 if (!customerName || invalidNamePattern.test(customerName) || !namePattern.test(customerName)) {

  alert("Invalid Customer Name. Please enter a valid name.");

  isValid = false;

 }

 // Consumer Number Validation

 if (!/^\d{13}$/.test(consumerID)) {

  showError("Consumer Number must be exactly 13 digits.");

  isValid = false;

 } else if (/^0+$/.test(consumerID)) {

  showError("Consumer Number cannot be all zeros.");

  isValid = false;

 } else if (/0{5}$/.test(consumerID)) {

  showError("The last 5 digits of the Consumer Number cannot be all zeros.");

  isValid = false;

 }

 // Bill Number Validation

 if (!/^\d{5}$/.test(billNumber)) {

  showError("Bill Number must be exactly 5 digits.");

  isValid = false;

 } else if (/^0+$/.test(billNumber)) {

  showError("Bill Number cannot be all zeros.");

  isValid = false;

 }

 // Mobile Number Validation

 if (!/^[7-9]\d{9}$/.test(mobileNumber)) {

  showError("Mobile Number must be 10 digits and start with 7, 8, or 9.");

  isValid = false;

 }

 // Email Validation

 const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

 if (!emailRegex.test(email)) {

  showError("Please enter a valid email address.");

  isValid = false;

 }

 // Password Validation

 const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&#]{6,}$/;

 if (!passwordRegex.test(password)) {

  showError("Password must be at least 6 characters long, with one uppercase letter, one lowercase letter, one digit, and one special character.");

  isValid = false;

 }

 // Confirm Password Validation

 if (password !== confirmPassword) {

  showError("Password and Confirm Password do not match.");

  isValid = false;

 }

 // If all validations pass, submit the form

 if (isValid) {

  alert("Form submitted successfully!");

  return true;

 }

 return false; // Prevent submission if invalid

}

// Function to show error messages as alert boxes

function showError(message) {

 alert(message);

}

