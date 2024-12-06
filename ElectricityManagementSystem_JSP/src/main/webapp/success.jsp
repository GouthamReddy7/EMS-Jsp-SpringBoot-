<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Complaints</title>
<style>
table {
	width: 50%;
	border-collapse: collapse;
	margin: 20px auto;
}
th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}
th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<h2 style="text-align: center;">Complaint List</h2>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="complaint" items="${complaintList}">
				<tr>
					<td>${complaint.complaintId}</td>
					<td>${complaint.complaintType}</td>
					<td>${complaint.category}</td>
					<td>${complaint.landmark}</td>
					<td>${complaint.customerName}</td>
					<td>${complaint.problem}</td>
					<td>${complaint.consumerId}</td>
					<td>${complaint.address}</td>
					<td>${complaint.mobileNumber}</td>
					<td>${complaint.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>