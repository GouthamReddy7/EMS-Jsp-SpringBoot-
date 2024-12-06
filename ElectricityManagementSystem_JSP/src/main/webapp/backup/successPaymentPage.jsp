<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Payment Success</title>

<style>

body {

 font-family: Arial, sans-serif;

 margin: 0;

 padding: 0;

 display: flex;

 flex-direction: column;

 align-items: center;

 justify-content: center;

 height: 100vh;

 background: linear-gradient(135deg, #a8e063, #56ab2f);

 background-repeat: no-repeat;

 background-attachment: fixed;

 color: white;

}

.container {

 text-align: center;

 padding: 30px;

 background: white;

 border-radius: 10px;

 box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);

 max-width: 400px;

 width: 90%;

}

.checkmark {

 width: 100px;

 height: 100px;

 border-radius: 50%;

 background: #4caf50;

 display: flex;

 align-items: center;

 justify-content: center;

 margin: 0 auto 20px auto;

}

.checkmark svg {

 width: 50px;

 height: 50px;

}

h1 {

 font-size: 24px;

 color: #333;

}

p {

 font-size: 18px;

 color: #666;

}

.button {

 margin-top: 20px;

 display: inline-block;

 padding: 10px 20px;

 background: #4caf50;

 color: white;

 text-decoration: none;

 border-radius: 5px;

 font-size: 16px;

 transition: background 0.3s ease;

}

.button:hover {

 background: #45a049;

}

</style>



</head>

<body>

<%

 // Check if the user is authenticated

 String consumerName = (String) session.getAttribute("consumerName");

 String username = (String) session.getAttribute("username");

 Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

 if (isAuthenticated == null || !isAuthenticated) {

  // Redirect to login page if not authenticated

  request.getRequestDispatcher("loginHome.jsp").forward(request, response);

 }

%>

<%

 // Retrieve parameters from the request

 String billId = request.getParameter("billId");

 String consumerId = request.getParameter("consumerId");

 String dueAmount = request.getParameter("dueAmount");

 String month = request.getParameter("month");

 String paidStatus = request.getParameter("paidStatus");

%>


<script>

 function downloadReceipt() {

  // Retrieve parameters dynamically from the JSP/Java context


  const billId = "<%=request.getParameter("billId")%>";

  const consumerId = "<%=request.getParameter("consumerId")%>";

  const dueAmount = "<%=request.getParameter("dueAmount")%>";

  const month = "<%=request.getParameter("month")%>";


  // Create receipt content

  const receiptContent = `

   Payment Receipt

   -----------------------

   Bill ID: ${billId}

   Consumer ID: ${consumerId}

   Amount Paid: Rs.${dueAmount}

   Month: ${month}
   



   <%=consumerName%>, Thank you for your payment!

  `;



  // Create a Blob object for the receipt content

  const blob = new Blob([receiptContent], { type: "text/plain" });



  // Create a download link

  const link = document.createElement("a");

  link.href = URL.createObjectURL(blob);

  link.download = `Receipt_Bill_${billId}.txt`;



  // Trigger download

  link.click();



  // Clean up

  URL.revokeObjectURL(link.href);

 }

</script>




<div class="container">

 <div class="checkmark">

  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="white">

   <path d="M9 16.2l-4.2-4.2-1.4 1.4 5.6 5.6L20.6 7.4 19.2 6z" />

  </svg>

 </div>

 <h1>Payment Successful!</h1>

 <p>Your payment has been processed successfully.</p>

 <a class="button" href="home.jsp">Go to Home</a>

 <button class="button" onclick="downloadReceipt()">Download Receipt</button>

</div>

</body>

</html>