function resetForm() {
    document.getElementById("registrationForm").reset();
    // Reset the border color to the default
    const formFields = document.querySelectorAll("input[type='text'], input[type='email'], input[type='password']");
    formFields.forEach(field => {
      field.style.borderLeft = "1px solid #ddd"; // Default border color
    });
  }
  // Retrieve stored data
  const title = sessionStorage.getItem("title");
  const customerName = sessionStorage.getItem("customerName");
  const email = sessionStorage.getItem("email");

  // Populate the form fields
  document.getElementById("title").value = title || "";
  document.getElementById("customerName").value = customerName || "";
  document.getElementById("email").value = email || "";
    function register() {
      let isValid = true;
  
      const consumerNumber = document.getElementById("ConsumerNumber");
      const billNumber = document.getElementById("BillNumber");
      const mobile = document.getElementById("mynumber");
      const userId = document.getElementById("UserId");
      const password = document.getElementById("password");
      const confirmPassword = document.getElementById("confirmPassword");
  
      [consumerNumber, billNumber, mobile, userId, password, confirmPassword].forEach(field => {
        field.style.borderLeft = "2px solid #ccc";
      });
  
      if (!/^\d{13}$/.test(consumerNumber.value)) {
        alert("Consumer Number should be exactly 13 digits.");
        consumerNumber.style.borderLeft = "2px solid red";
        return;
      }
  
      if (!/^\d{5}$/.test(billNumber.value)) {
        alert("Bill Number should be exactly 5 digits.");
        billNumber.style.borderLeft = "2px solid red";
        return;
      }
  
      if (!/^\d{10}$/.test(mobile.value)) {
        alert("Mobile Number should be exactly 10 digits.");
        mobile.style.borderLeft = "2px solid red";
        return;
      }
  
      if (userId.value.length < 5 || userId.value.length > 20) {
        alert("User ID should be between 5 and 20 characters.");
        userId.style.borderLeft = "2px solid red";
        return;
      }
  
      if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$!%*?&])[A-Za-z\d@$#!%*?&]{8,30}$/.test(password.value)) {
        alert("Password must be 8-30 characters, include at least one uppercase, one lowercase letter, one number, and one special character.");
        password.style.borderLeft = "2px solid red";
        return;
      }
  
      if (password.value !== confirmPassword.value) {
        alert("Passwords do not match.");
        confirmPassword.style.borderLeft = "2px solid red";
        return;
      }
  
      const customerId = Math.floor(10000 + Math.random() * 90000);
      const name = localStorage.getItem('customerName');
      document.getElementById("customerId").textContent = customerId;
      document.getElementById('customerName').textContent = name;
      document.getElementById("customerMobile").textContent = mobile.value;
      document.getElementById("acknowledgment").style.display = "block";
    }