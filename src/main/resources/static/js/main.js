/**
 * 
 */
function updateTime() {
	const now = new Date();

	const formattedDate = now.toLocaleDateString('ko-KR', {
		year: 'numeric',
		month: 'long',
		day: 'numeric',
	});

	const weekday = now.toLocaleString('ko-KR', {
		weekday: 'short'
	});

	const hours = String(now.getHours()).padStart(2, '0');
	const minutes = String(now.getMinutes()).padStart(2, '0');
	const seconds = String(now.getSeconds()).padStart(2, '0');

	const formattedTime = `${hours}시 ${minutes}분 ${seconds}초`;

	document.getElementById('currentTime').innerHTML = `${formattedDate} (${weekday})<br>${formattedTime}`;
}

setInterval(updateTime, 1000);

updateTime();

function clockIn() {
    fetch('/attendance/clockIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'same-origin'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('출근 처리 중 오류가 발생했습니다.');
        }
        return response.json();
    })
    .then(data => {
        let time = data.startTime;
		
        if (time.includes('.')) {
            time = time.split('.')[0]; 
        }
		
        const [hours, minutes, seconds] = time.split(':');
        const formattedTime = `${hours}시 ${minutes}분 ${seconds}초`;
        document.getElementById('clockInTime').innerHTML = formattedTime;
        alert('출근이 정상적으로 처리되었습니다.');
    })
    .catch(error => {
        console.error('Error:', error);
        alert(error.message);
    });
}

function clockOut() {
    fetch('/attendance/clockOut', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'same-origin'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('퇴근 처리 중 오류가 발생했습니다.');
        }
        return response.json();
    })
    .then(data => {
        let time = data.endTime;
		
        if (time.includes('.')) {
            time = time.split('.')[0]; 
        }
		
        const [hours, minutes, seconds] = time.split(':');
        const formattedTime = `${hours}시 ${minutes}분 ${seconds}초`;
        document.getElementById('clockOutTime').innerHTML = formattedTime;
        alert('퇴근이 정상적으로 처리되었습니다.');
    })
    .catch(error => {
        console.error('Error:', error);
        alert(error.message);
    });
}
