<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Customers - Quick E-Bill</title>
     <div id="adminMenu-placeholder"></div>
    
    <style>
        @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
        
        body {
            font-family: 'Montserrat', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }

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

        .content {
            padding: 30px;
            text-align: center;
        }

        .content h2 {
            color: #007bff;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .card-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            padding: 20px;
        }

        .card {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            padding: 20px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .card h3 {
            font-size: 20px;
            color: #007bff;
            margin-bottom: 10px;
        }

        .card p {
            color: #666;
            margin-bottom: 20px;
        }

        .card button, .card a {
            padding: 10px 20px;
            border: none;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            text-decoration: none;
            display: inline-block;
        }

        .card button:hover, .card a:hover {
            background-color: #218838;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
            display: none; /* Initially hidden */
        }

        table.show {
            display: table;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        table th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
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
        request.getRequestDispatcher("loginHome.jsp").forward(request, response);
    }
%>
<div class="content">
    <h2>Manage Customers</h2>

    <div class="card-container">
        <!-- View All Customers -->
        <div class="card">
            <h3>View Customers</h3>
            <p>See the complete list of customers.</p>
            <form action="CustomerDetailsServlet" method="GET">
                <button type="submit">View All</button>
            </form>
        </div>

        <!-- Search Customer -->
        <div class="card">
            <h3>Search Customer</h3>
            <p>Find a customer by their details.</p>
			<button onclick="window.location.href='searchCustomer.jsp'" type="button">Search</button>
        </div>

        <!-- Delete Customer -->
        <div class="card">
            <h3>Delete Customer</h3>
            <p>Remove a customer from the system.</p>
            <form action="DeleteCustomerServlet" method="GET">
                <button type="submit">Delete</button>
            </form>
        </div>
    </div>

    <!-- Customer Table -->
    <table id="customerTable" class="<c:if test='${not empty customerList}'>show</c:if>">
        <thead>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Email</th>
                <th>Mobile Number</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="customer" items="${customerList}">
                <tr>
                    <td>${customer.consumerId}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.mobileNo}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
	<jsp:include page="footer.jsp" />

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
</body>
</html>
