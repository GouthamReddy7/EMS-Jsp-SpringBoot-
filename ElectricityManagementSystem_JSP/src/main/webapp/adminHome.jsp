<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quick EBill</title>
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,800" rel="stylesheet">
<div id="adminMenu-placeholder"></div>

<style>
body {
    font-family: 'Montserrat', sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f9f9f9;
    color: #333;
}

/* Header */
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 20px 30px;
    background-color: #007bff;
    color: white;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.header h2 {
    font-size: 24px;
    margin: 0;
}

/* Main Content */
main {
    text-align: center;
    padding: 30px;
}

main h2 {
    margin-bottom: 20px;
    font-size: 34px;
    color: #007bff;
}

main p {
    font-size: 18px;
    color: #555;
}

/* Cards */
.card-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 20px;
    padding: 20px;
}

.card {
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 10px;
    padding: 20px;
    text-align: center;
    transition: transform 0.3s, box-shadow 0.3s;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.card h3 {
    font-size: 20px;
    color: #007bff;
    margin-bottom: 10px;
}

.card p {
    color: #666;
    margin-bottom: 15px;
}

.card a {
    display: inline-block;
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    font-size: 16px;
    transition: background-color 0.3s;
}

.card a:hover {
    background-color: #0056b3;
}

footer {
    background-color: #222;
    color: #fff;
    font-size: 14px;
    bottom: 0;
    position: fixed;
    left: 0;
    right: 0;
    text-align: center;
    z-index: 999;
}

footer p {
    margin: 10px 0;
    color: #fff;
}

footer i {
    color: red;
}

footer a {
    color: #3c97bf;
    text-decoration: none;
}
</style>

</head>
<body>
<%
    // Check if the user is authenticated
    String customerName = (String) session.getAttribute("customerName");
    String username = (String) session.getAttribute("username");
    Boolean isAuthenticated = (Boolean) session.getAttribute("isAuthenticated");

    if (isAuthenticated == null || !isAuthenticated) {
        // Redirect to login page if not authenticated
        request.getRequestDispatcher("loginHome.jsp").forward(request, response);
    }
%>

<!-- Header -->
<main>
    <h2><b>Welcome, Admin!</b></h2>
    <p>Choose an action below to manage the system:</p>
    <div class="card-container">
        <div class="card">
            <h3>Manage Customers</h3>
            <p>View, add, or edit customer details.</p>
            <a href="adminManageCustomer.jsp">Go</a>
        </div>
        <div class="card">
            <h3>View all Bills</h3>
            <p>By using consumer ID.</p>
            <a href="adminViewBill.jsp">Go</a>
        </div>
       <!--   <div class="card">
            <h3>Feedback</h3>
            <p>Generate feedback for complaints.</p>
            <a href="reports.jsp">Go</a>-->
        </div>
    </div>
</main>

<script>
function loadMenu() {
    fetch('adminMenu.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('adminMenu-placeholder').innerHTML = data;
        });
}
document.addEventListener('DOMContentLoaded', loadMenu);
</script>

<jsp:include page="footer.jsp" />

</body>
</html>
