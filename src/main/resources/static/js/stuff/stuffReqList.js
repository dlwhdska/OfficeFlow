/**
 * 
 */
function goBack() {
	window.location.href = '/main';
}

document.addEventListener('DOMContentLoaded', function() {
	const viewDetailsLinks = document.querySelectorAll('.view-details');

	viewDetailsLinks.forEach(link => {
		link.addEventListener('click', function(event) {
			event.preventDefault();

			const id = this.getAttribute('data-id');
			const reqDate = this.getAttribute('data-req-date');
			const stuffName = this.getAttribute('data-stuff-name');
			const quantity = this.getAttribute('data-quantity');
			const purpose = this.getAttribute('data-purpose');
			const status = this.getAttribute('data-status');
			const reject = this.getAttribute('data-reject');

			document.getElementById('modal-id').textContent = id;
			document.getElementById('modal-req-date').textContent = reqDate;
			document.getElementById('modal-stuff-name').textContent = stuffName;
			document.getElementById('modal-quantity').textContent = quantity;
			document.getElementById('modal-purpose').textContent = purpose;
			document.getElementById('modal-status').textContent = status;
			document.getElementById('modal-reject').textContent = reject;

			const cancelButton = document.querySelector('[onclick="cancelStuffReq(this)"]');
			cancelButton.setAttribute('data-id', id);
			const modal = new bootstrap.Modal(document.getElementById('stuffReqDetailModal'));
			modal.show();
		});
	});
});

function cancelStuffReq(button) {

	const id = button.getAttribute('data-id');

	const url = `/stuff/cancelStuffReq/${id}`;

	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
	})
		.then(response => {
			if (response.ok) {
				alert('요청이 취소되었습니다.');
				location.reload();
			} else {
				alert('요청 취소에 실패했습니다.');
			}
		})
		.catch(error => {
			alert('서버와의 연결에 문제가 발생했습니다.');
		});
}

document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status'); 
	const month = urlParams.get('month');

    if (status && month) {
        document.getElementById('statusFilter').value = status;
        document.getElementById('monthFilter').value = month; 
    }
});

function filterByStatusAndMonth() {
    const status = document.getElementById('statusFilter').value;
    const month = document.getElementById('monthFilter').value;
    
    const currentUrl = new URL(window.location.href);
    currentUrl.searchParams.set('status', status);
    currentUrl.searchParams.set('month', month);
    currentUrl.searchParams.set('page', '0'); 
    
    window.location.href = currentUrl.toString();
}
