function loadMenu() {

	fetch('menu.jsp')

		.then(response => response.text())

		.then(data => {

			document.getElementById('menu-placeholder').innerHTML = data;

		});

}



function updateCategory() {

	const complaintType = document.getElementById('complaintType').value;

	const categorySelect = document.getElementById('category');

	categorySelect.innerHTML = '<option value="">Select Category</option>'; // Reset options



	if (complaintType && categoryMap[complaintType]) {

		categoryMap[complaintType].forEach(category => {

			const option = document.createElement('option');

			option.value = category;

			option.textContent = category;

			categorySelect.appendChild(option); // Append the category options

		});

	}

}



function generateComplaintId() {

	return 'COMP' + Date.now().toString().slice(-6);

}



function submitComplaint(event) {

	event.preventDefault();



	const mobileNumber = document.getElementById('mobileNumber').value;

	if (!/^\d{10}$/.test(mobileNumber) || mobileNumber.startsWith("0") || (!/^[6-9]\d{9}$/.test(mobileNumber))) {

		alert('Please give valid mobile number.');

		return;
		

	}



	const consumerNumber = document.getElementById('consumerNumber').value;

	if (consumerNumber && !/^\d{13}$/.test(consumerNumber)) {

		alert('Consumer number must be 13 digits');

		return;

	}



	const name = document.getElementById('contactPerson').value;

	if (!/^[A-Za-z\s]{5,30}$/.test(name)) {

		alert('Name must be between 5 and 30 characters and cannot contain numbers');

		return;

	}



	const problemDescription = document.getElementById('problemDescription').value;

	const address = document.getElementById('address').value;

	if (problemDescription.trim() === "" || address.trim() === "") {

		alert('Problem description and address are required');

		return;

	}



	const complaintId = generateComplaintId();

	alert(`Complaint submitted successfully!\nComplaint ID: ${complaintId}`);

	resetForm();

}



function resetForm() {

	document.getElementById('complaintForm').reset();

	updateCategory();

}

function logout() {

	if (confirm('Are you sure you want to logout?')) {

		window.location.href = 'loginHome.jsp';
	}
}
document.addEventListener('DOMContentLoaded', loadMenu);
