/**
 * 
 */
document.addEventListener('DOMContentLoaded', function() {

	const quantityInput = document.getElementById('quantity');
	const quantityError = document.getElementById('quantityError');
	const purposeInput = document.getElementById('purpose');
	const purposeError = document.getElementById('purposeError');
	const stuffReqForm = document.getElementById('stuffReqForm');

	quantityInput.addEventListener('input', function() {
		const value = this.value;

		if (/[^0-9]/.test(value)) {
			quantityError.style.display = 'block';
		} else {
			quantityError.style.display = 'none'; 
		}
	});

	purposeInput.addEventListener('input', function() {
		const value = this.value.trim();
		this.value = value;

		purposeError.style.display = 'none';
	});

	stuffReqForm.addEventListener('submit', function(event) {
		const quantity = parseInt(quantityInput.value);
		const purpose = purposeInput.value;

		if (isNaN(quantity) || quantity <= 0) {
			event.preventDefault();
			quantityError.textContent = '숫자만 입력 가능합니다.';
			quantityError.style.display = 'block';
		} 

		if (purpose.length === 0) {
			event.preventDefault();
			purposeError.textContent = '요청사유를 입력해 주세요.'
			purposeError.style.display = 'block';
		}
	});
});