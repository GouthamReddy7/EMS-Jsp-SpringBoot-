<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<div id="menu-placeholder"></div>
<link rel="stylesheet" href="css\bill_history.css">

</head>

<body>

	<div id="menu-placeholder"></div>

	<div class="container">

		<h2>Bill History</h2>



		<table>

			<tr>

				<th>Date</th>

				<th>Time</th>

				<th>Electricity Consumed (kWh)</th>

				<th>Amount</th>

			</tr>


		</table>

	</div>

	<script>
function loadMenu() 
{

	fetch('menu.jsp')
	
	.then(response => response.text())
	
	.then(data => {
	
	document.getElementById('menu-placeholder').innerHTML = data;
	
	});

}
document.addEventListener('DOMContentLoaded', loadMenu);
</script>

</body>

</html>