<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/complaint_status.css">
<div id="menu-placeholder"></div>

<title>Complaint Status</title>
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

	<div class="container">
		<h3 class="bill-heading">🔍 Check Your Complaint Status</h3>

		<!-- Form for searching complaints -->
		<form id="complaintForm" method="GET" action="ComplaintViewServlet">
			<div class="search-section">
				<h2 class="complaint-heading">Complaint Number:</h2>
				<input type="text" name="complaintId" id="complaintId"
					class="input-box" maxlength=10 minlength=10 placeholder="Enter your Complaint ID" required />
			</div>

			<div class="bg-button">
				<button class="btn btn-reset" type="reset">Reset</button>
				<button class="btn btn-submit" type="submit">Get Status</button>
			</div>
		</form>
	</div>

	<script>
    function loadMenu() {
        fetch('menu.jsp')
            .then(response => response.text())
            .then(data => {
                document.getElementById('menu-placeholder').innerHTML = data;
            });
    }

    document.addEventListener('DOMContentLoaded', loadMenu);
</script>

	<jsp:include page="footer.jsp" />

</body>
</html>
