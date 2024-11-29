/**
 * 
 */
function goBack() {
	window.location.href = '/main';
}

function filterByMonth() {
    const month = document.getElementById('monthFilter').value;
    const currentUrl = new URL(window.location.href);
    
	currentUrl.searchParams.set('month', month);
    currentUrl.searchParams.set('page', '0');
    
    window.location.href = currentUrl.toString();
}

document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const month = urlParams.get('month');
    if (month) {
        document.getElementById('monthFilter').value = month;
    }
});