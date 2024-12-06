function loadMenu() 
{

	fetch('menu.jsp')
	
	.then(response => response.text())
	
	.then(data => {
	
	document.getElementById('menu-placeholder').innerHTML = data;
	
	});

}
document.addEventListener('DOMContentLoaded', loadMenu);
function updateTotal() {
        let totalAmount = 0;
        const checkboxes = document.querySelectorAll('.bill-check');
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                totalAmount += parseFloat(checkbox.dataset.amount);
            }
        });
        document.getElementById('totalAmount').textContent = "₹" + totalAmount.toFixed(2);
    }
    
    function proceedToPay() {
        const selectedConsumer = [];
        const checkboxes = document.querySelectorAll('.bill-check');
        checkboxes.forEach((checkbox, index) => {
            if (checkbox.checked) {
                const row = checkbox.closest("tr");
                const consumerNumber = row.cells[0].textContent.trim();
                const amount = row.cells[3].textContent.trim();
                selectedConsumer.push({ consumerNumber, amount });
            }
        });
        if (selectedConsumer.length === 0) {
            alert("Please select at least one consumer to proceed.");
            return;
        }
        localStorage.setItem("selectedConsumers", JSON.stringify(selectedConsumer));
        window.location.href = 'billPayment.jsp';
    }
	