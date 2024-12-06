// Get form elements

const sendOtpBtn = document.getElementById('sendOtpBtn');

const validateOtpBtn = document.getElementById('validateOtpBtn');

const resetPasswordBtn = document.getElementById('resetPasswordBtn');



const otpSection = document.getElementById('otp-section');

const resetPasswordSection = document.getElementById('reset-password-section');



// Step 1: Send OTP

sendOtpBtn.addEventListener('click', () => {

 // Simulate sending OTP (In real scenario, an API request should be sent)

 alert('OTP has been sent to your email.');

 otpSection.style.display = 'block';

});



// Step 2: Validate OTP

validateOtpBtn.addEventListener('click', () => {

 const otp = document.getElementById('otp').value;

 if (otp === '123456') { // Placeholder OTP for demo purposes

  alert('OTP validated successfully.');

  resetPasswordSection.style.display = 'block';

 } else {

  alert('Invalid OTP. Please try again.');

 }

});



// Step 3: Reset Password

resetPasswordBtn.addEventListener('click', () => {

 const newPassword = document.getElementById('new-password').value;

 const confirmPassword = document.getElementById('confirm-password').value;



 if (newPassword === confirmPassword) {

  alert('Password reset successful!');

  // Redirect to login page or handle further steps

 } else {

  alert('Passwords do not match. Please try again.');

 }

});