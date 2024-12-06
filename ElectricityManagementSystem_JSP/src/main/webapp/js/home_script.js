const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});
document.addEventListener("DOMContentLoaded", function () {

  const params = new URLSearchParams(window.location.search);

  if (params.has("error")) {

    document.getElementById("error-message").innerText = "Invalid email or password.";

    document.getElementById("error-message").style.display = "block";

  }

});

function saveData() {
       // Get the values from the form
       const title = document.getElementById("title").value;
       const customerName = document.getElementById("customerName").value;
       const email = document.getElementById("email").value;

       // Save to sessionStorage or localStorage
       sessionStorage.setItem("title", title);
       sessionStorage.setItem("customerName", customerName);
       sessionStorage.setItem("email", email);

       // Redirect to the next page
       window.location.href = "register.jsp";
   }
signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});