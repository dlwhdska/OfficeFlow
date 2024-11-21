package com.of.test;

import com.of.attendance.entity.Attendance;
import com.of.attendance.entity.AttendanceStatus;
import com.of.department.entity.Department;
import com.of.meeting.entity.MeetingroomDetail;
import com.of.meeting.entity.MeetingroomReservation;
import com.of.meeting.entity.Participants;
import com.of.member.entity.Member;
import com.of.member.entity.Role;
import com.of.notice.entity.Notice;
import com.of.notification.entity.Notification;
import com.of.notification.entity.NotificationStatus;
import com.of.schedule.entity.Schedule;
import com.of.stuff.entity.Stuff;
import com.of.stuff.entity.StuffReq;
import com.of.stuff.entity.StuffReqStatus;

import com.of.attendance.repository.AttendanceRepository;
import com.of.department.repository.DepartmentRepository;
import com.of.meeting.repository.MeetingroomDetailRepository;
import com.of.meeting.repository.MeetingroomReservationRepository;
import com.of.meeting.repository.ParticipantsRepository;
import com.of.member.repository.MemberRepository;
import com.of.notice.repository.NoticeRepository;
import com.of.notification.repository.NotificationRepository;
import com.of.schedule.repository.ScheduleRepository;
import com.of.stuff.repository.StuffRepository;
import com.of.stuff.repository.StuffReqRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ComprehensiveTestDataGenerator {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private MeetingroomDetailRepository meetingroomDetailRepository;

	@Autowired
	private MeetingroomReservationRepository meetingroomReservationRepository;

	@Autowired
	private ParticipantsRepository participantsRepository;

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private StuffRepository stuffRepository;

	@Autowired
	private StuffReqRepository stuffReqRepository;

	private final Random random = new Random();

	private final String[] FIRST_NAMES = { "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임" };
	private final String[] LAST_NAMES = { "민준", "서준", "예준", "도윤", "시우", "주원", "하준", "지호", "지훈", "준서" };
	private final String[] DEPARTMENTS = { "경영팀", "재무팀", "총괄팀", "인사팀", "개발1팀", "개발2팀", "운영팀" };
	private final String[] POSITIONS = { "사원", "대리", "과장", "부장", "팀장" };
	private final String[] MEETINGROOM_NAMES = { "회의실A", "회의실B", "회의실C", "회의실D", "회의실E" };
	private final String[] NOTICE_TITLES = { "중요 공지", "팀 미팅 안내", "프로젝트 업데이트", "복리후생 변경", "연간 계획" };
	private final String[] NOTIFICATION_CONTENTS = { "새로운 공지사항", "회의 알림", "업무 변경", "급여 명세서 발행", "성과 평가 안내" };
	private final String[] SCHEDULE_TITLES = { "프로젝트 미팅", "팀 워크샵", "고객 미팅", "교육 세션", "성과 리뷰" };
	private final String[] STUFF_NAMES = { "노트북", "모니터", "키보드", "마우스", "프린터", "태블릿", "헤드셋" };

//	@Test
	public void generateAllTestData() {
		List<Department> departments = createDepartments();
		List<Member> members = createMembers(departments);
		List<MeetingroomDetail> meetingRooms = createMeetingRooms();

		createAttendanceRecords(members);
		createMeetingReservations(members, meetingRooms);
		createNotices(members);
		createNotifications(members);
		createSchedules(members);

		List<Stuff> stuffList = createStuff();
		createStuffRequests(members, stuffList);
	}

	private List<Department> createDepartments() {

		for (String deptName : DEPARTMENTS) {
			Department dept = new Department();
			dept.setName(deptName);
			departmentRepository.save(dept);
		}
		return departmentRepository.findAll();
	}

	private List<Member> createMembers(List<Department> departments) {
		Member admin = new Member();
		admin.setId(1L);
		admin.setDepartment(departments.get(0));
		admin.setName("관리자");
		admin.setEmail("admin@company.com");
		admin.setPassword("admin1234");
		admin.setPosition("관리자");
		admin.setTel("01000000000");
		admin.setRole(Role.ADMIN);
		memberRepository.save(admin);

		Member ceo = new Member();
		ceo.setId(2L);
		ceo.setDepartment(departments.get(0));
		ceo.setName("사장");
		ceo.setEmail("ceo@company.com");
		ceo.setPassword("ceo1234");
		ceo.setPosition("사장");
		ceo.setTel("01011111111");
		ceo.setRole(Role.MEMBER);
		memberRepository.save(ceo);

		for (int i = 3; i <= 100; i++) {
			Member member = new Member();
			member.setId((long) i);
			member.setDepartment(departments.get(random.nextInt(departments.size())));

			String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
			String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
			String fullName = firstName + lastName;

			member.setName(fullName);
			member.setEmail(fullName.toLowerCase() + i + "@company.com");
			member.setPassword("pass" + i);
			member.setPosition(POSITIONS[random.nextInt(POSITIONS.length)]);
			member.setTel(String.format("010%04d%04d", random.nextInt(10000), random.nextInt(10000)));
			member.setRole(random.nextInt(10) == 0 ? Role.ADMIN : Role.MEMBER);

			memberRepository.save(member);
		}
		return memberRepository.findAll();
	}

	private List<MeetingroomDetail> createMeetingRooms() {
		for (String roomName : MEETINGROOM_NAMES) {
			MeetingroomDetail room = new MeetingroomDetail();
			room.setName(roomName);
			room.setMaxNum(random.nextInt(10) + 5); 
			room.setMonitor(random.nextInt(3) + 1); 
			room.setProjector(random.nextInt(2) + 1);
			room.setBoard(random.nextInt(2) + 1); 
			room.setLocation("본관 " + roomName + " 구역");
			meetingroomDetailRepository.save(room);
		}
		return meetingroomDetailRepository.findAll();
	}

	private void createAttendanceRecords(List<Member> members) {
		IntStream.range(0, 200).forEach(i -> {
			Attendance attendance = new Attendance();
			Member randomMember = members.get(random.nextInt(members.size()));
			attendance.setMember(randomMember);

			LocalDate attendanceDate = LocalDate.now().minusDays(random.nextInt(365));
			attendance.setAttendanceDate(attendanceDate);

			AttendanceStatus status = generateRealisticAttendanceStatus();
			attendance.setStatus(status);

			switch (status) {
			case CLOCK_IN:
				LocalTime startTime = LocalTime.of(8, 30).plusMinutes(random.nextInt(30));
				attendance.setStartTime(startTime);
				attendance.setEndTime(null);
				break;
			case CLOCK_OUT:
				startTime = LocalTime.of(8, 30).plusMinutes(random.nextInt(30));
				LocalTime endTime = LocalTime.of(18, 0).plusMinutes(random.nextInt(60));
				attendance.setStartTime(startTime);
				attendance.setEndTime(endTime);
				break;
			case LATE:
				startTime = LocalTime.of(9, 0).plusMinutes(random.nextInt(30));
				endTime = LocalTime.of(18, 0).plusMinutes(random.nextInt(60));
				attendance.setStartTime(startTime);
				attendance.setEndTime(endTime);
				break;
			case OFF_WORK:
				startTime = LocalTime.of(8, 30);
				endTime = LocalTime.of(15, 0);
				attendance.setStartTime(startTime);
				attendance.setEndTime(endTime);
				break;
			case ABSENT:
				attendance.setStartTime(null);
				attendance.setEndTime(null);
				break;
			case SICK_LEAVE:
				attendance.setStartTime(null);
				attendance.setEndTime(null);
				break;
			case VACATION:
				attendance.setStartTime(null);
				attendance.setEndTime(null);
				break;
			}

			attendanceRepository.save(attendance);
		});
	}

	private AttendanceStatus generateRealisticAttendanceStatus() {
		int rand = random.nextInt(100);
		if (rand < 65)
			return AttendanceStatus.CLOCK_OUT; 
		if (rand < 75)
			return AttendanceStatus.LATE; 
		if (rand < 80)
			return AttendanceStatus.OFF_WORK;
		if (rand < 88)
			return AttendanceStatus.SICK_LEAVE;
		if (rand < 95)
			return AttendanceStatus.VACATION; 
		return AttendanceStatus.ABSENT;
	}

	private void createMeetingReservations(List<Member> members, List<MeetingroomDetail> meetingRooms) {
		for (int i = 0; i < 100; i++) {
			MeetingroomReservation reservation = new MeetingroomReservation();
			reservation.setMeetingroom(meetingRooms.get(random.nextInt(meetingRooms.size())));
			reservation.setMember(members.get(random.nextInt(members.size())));
			reservation.setMeetingDate(LocalDate.now().plusDays(random.nextInt(30)));
			reservation.setStartTime(LocalTime.of(9, 0).plusHours(random.nextInt(8)));
			reservation.setEndTime(reservation.getStartTime().plusHours(random.nextInt(3) + 1));
			reservation.setPurpose("팀 미팅 " + (i + 1));
			meetingroomReservationRepository.save(reservation);

			int participantCount = random.nextInt(3) + 2;
			for (int j = 0; j < participantCount; j++) {
				Participants participant = new Participants();
				participant.setMember(members.get(random.nextInt(members.size())));
				participant.setMeeting(reservation);
				participantsRepository.save(participant);
			}
		}
	}

	private void createNotices(List<Member> members) {
		for (int i = 0; i < 100; i++) {
			Notice notice = new Notice();
			notice.setMember(members.get(random.nextInt(members.size())));
			notice.setTitle(NOTICE_TITLES[random.nextInt(NOTICE_TITLES.length)] + " " + (i + 1));
			notice.setContent("공지사항 세부 내용 " + (i + 1));
			notice.setRegDate(LocalDate.now().minusDays(random.nextInt(365)));
			noticeRepository.save(notice);
		}
	}

	private void createNotifications(List<Member> members) {
		for (int i = 0; i < 200; i++) {
			Notification notification = new Notification();
			notification.setMember(members.get(random.nextInt(members.size())));
			notification
					.setContent(NOTIFICATION_CONTENTS[random.nextInt(NOTIFICATION_CONTENTS.length)] + " " + (i + 1));
			notification.setCreatedAt(LocalDate.now().minusDays(random.nextInt(365)));
			notification
					.setStatus(random.nextInt(10) == 0 ? NotificationStatus.CONFIRMED : NotificationStatus.UNCONFIRMED);
			notificationRepository.save(notification);
		}
	}

	private void createSchedules(List<Member> members) {
		for (int i = 0; i < 150; i++) {
			Schedule schedule = new Schedule();
			schedule.setMember(members.get(random.nextInt(members.size())));
			schedule.setTitle(SCHEDULE_TITLES[random.nextInt(SCHEDULE_TITLES.length)] + " " + (i + 1));

			LocalDateTime startTime = LocalDateTime.now().plusDays(random.nextInt(30)).plusHours(random.nextInt(24));
			schedule.setStartTime(startTime);
			schedule.setEndTime(startTime.plusHours(random.nextInt(4) + 1));

			scheduleRepository.save(schedule);
		}
	}

	private List<Stuff> createStuff() {
		for (String stuffName : STUFF_NAMES) {
			Stuff stuff = new Stuff();
			stuff.setName(stuffName);
			stuff.setStock(random.nextInt(100) + 50);
			stuffRepository.save(stuff);
		}
		return stuffRepository.findAll();
	}

	private void createStuffRequests(List<Member> members, List<Stuff> stuffList) {
		for (int i = 0; i < 100; i++) {
			StuffReq stuffReq = new StuffReq();
			stuffReq.setMember(members.get(random.nextInt(members.size())));
			stuffReq.setStuff(stuffList.get(random.nextInt(stuffList.size())));
			stuffReq.setReqDate(LocalDate.now().minusDays(random.nextInt(365)));
			stuffReq.setQuantity(random.nextInt(10) + 1);
			stuffReq.setPurpose("업무 목적 " + (i + 1));
			stuffReq.setStatus(StuffReqStatus.values()[random.nextInt(StuffReqStatus.values().length)]);
			stuffReqRepository.save(stuffReq);
		}
	}
}