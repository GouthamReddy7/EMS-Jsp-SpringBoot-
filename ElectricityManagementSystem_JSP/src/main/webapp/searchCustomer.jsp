<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Customers - Quick E-Bill</title>
         <div id="adminMenu-placeholder"></div>
    

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f1f1f1;
            color: #333;
        }

        .content {
            padding: 20px;
            max-width: 900px;
            margin: 0 auto;
            background: white;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            margin-top: 30px;
        }

        h3 {
            text-align: center;
            color: #007bff;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .search-container {
            text-align: center;
            margin-bottom: 20px;
        }

        .search-container form {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        input[type="text"] {
            padding: 10px;
            font-size: 16px;
            width: 300px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: white;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        button[type="submit"] {
            background-color: #007bff;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
            box-shadow: 0 4px 10px rgba(0, 91, 187, 0.5);
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
            font-weight: 600;
            text-transform: uppercase;
        }

        table td {
            background-color: #f9f9f9;
        }

        table tr:nth-child(even) td {
            background-color: #f1f1f1;
        }

        p {
            text-align: center;
            font-size: 18px;
            color: #555;
            margin-top: 20px;
        }

        @media (max-width: 600px) {
            .search-container form {
                flex-direction: column;
                gap: 15px;
            }

            input[type="text"] {
                width: 100%;
            }
        }
    </style>
</head>

<body>
    <div class="content">
    <!-- Search Customer Form -->
    <div class="search-container">
        <h3>Search Customer Using Email</h3>
        <form action="CustomerSearchServlet" method="GET">
            <input type="text" name="email" placeholder="Enter Customer Email" required />
            <button type="submit">Search</button>
        </form>
    </div>

    <!-- Displaying customer data -->
    <c:if test="${not empty customer}">
        <table id="customerTable" class="show">
            <thead>
                <tr>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Email</th>
                    <th>Mobile Number</th>
                    <th>User ID</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${customer.consumerId}</td>
                    <td>${customer.customerName}</td>
                    <td>${customer.email}</td>
                    <td>${customer.mobileNo}</td>
                    <td>${customer.userId}</td>
                </tr>
            </tbody>
        </table>
    </c:if>
    
    <c:if test="${not empty errorMessage}">
    <p style="color: red; text-align: center; margin-top: 20px;">
        ${errorMessage}
    </p>
</c:if>
    
</div>
<jsp:include page="footer.jsp" />

</body>
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
</html>
