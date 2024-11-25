/**
 * 
 */
function goBack() {
	window.location.href = '/main';
}

function searchByMonth() {
    const selectedMonth = document.getElementById('monthSelector').value;
    const currentUrl = new URL(window.location.href);
    
    currentUrl.searchParams.set('page', '0');
    
    if (selectedMonth) {
        currentUrl.searchParams.set('month', selectedMonth);
    } else {
        currentUrl.searchParams.delete('month');
    }
    
    window.location.href = currentUrl.toString();
}

document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const month = urlParams.get('month');
    if (month) {
        document.getElementById('monthSelector').value = month;
    }
});