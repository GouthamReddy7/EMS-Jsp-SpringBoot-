<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Customer - Quick E-Bill</title>
     <div id="adminMenu-placeholder"></div>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }

        .content {
            padding: 20px;
            max-width: 900px;
            margin: 30px auto;
            background: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        h2, h3 {
            text-align: center;
            color: #007bff;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            margin: 20px auto;
            border-collapse: collapse;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
        }

        table th {
            background-color: #007bff;
            color: white;
            font-weight: bold;
            text-transform: uppercase;
        }

        table td {
            background-color: #f9f9f9;
        }

        table tr:nth-child(even) td {
            background-color: #f1f1f1;
        }

        table td input[type="radio"] {
            transform: scale(1.3);
        }

        button {
            padding: 12px 20px;
            font-size: 16px;
            font-weight: 600;
            color: white;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
            margin-top: 20px;
        }

        button:hover {
            background-color: #218838;
            transform: translateY(-2px);
        }

        button:active {
            transform: translateY(0);
        }

        .no-customers {
            text-align: center;
            font-size: 18px;
            color: #555;
            margin-top: 20px;
        }

        @media (max-width: 600px) {
            table th, table td {
                font-size: 14px;
                padding: 10px;
            }

            button {
                font-size: 14px;
                padding: 10px 15px;
            }
        }
    </style>
</head>

<body>
    <div class="content">
        <h2>Delete Customer</h2>
        <h3>Select a Customer to Delete</h3>

        <!-- Display Customers in a table with radio buttons -->
        <c:if test="${not empty customerList}">
            <form action="DeleteCustomerServlet" method="POST" onsubmit="return confirmDeletion()" >
                <table>
                    <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>Customer Name</th>
                            <th>Email</th>
                            <th>Mobile Number</th>
                            <th>Select</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Iterate over the list of customers -->
                        <c:forEach var="customer" items="${customerList}">
                            <tr>
                                
                                <td>${customer.consumerId}</td>
                                <td>${customer.customerName}</td>
                                <td>${customer.email}</td>
                                <td>${customer.mobileNo}</td>
                                <td>
                                    <input type="radio" name="consumerId" value="${customer.consumerId}" required />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Submit button to delete the selected customer -->
                <div style="text-align: center;">
                    <button type="submit">Delete Selected Customer</button>
                </div>
            </form>
        </c:if>

        <!-- No customers found 
        <c:if test="${empty customerList}">
            <div class="no-customers">No customers found to delete.</div>
        </c:if>-->
    </div>
</body>
<script>
function loadMenu() {
    fetch('adminMenu.jsp')
        .then(response => response.text())
        .then(data => {
            document.getElementById('adminMenu-placeholder').innerHTML = data;
        });
}
function confirmDeletion() {
    return confirm("Are you sure you want to delete this customer?");
}

document.addEventListener('DOMContentLoaded', loadMenu);
</script>
</html>
