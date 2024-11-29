package com.of.stuff.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.of.member.dto.MemberDTO;
import com.of.member.entity.Member;
import com.of.member.repository.MemberRepository;
import com.of.stuff.dto.StuffReqDTO;
import com.of.stuff.entity.Stuff;
import com.of.stuff.entity.StuffReq;
import com.of.stuff.entity.StuffReqStatus;
import com.of.stuff.repository.StuffRepository;
import com.of.stuff.repository.StuffReqRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StuffReqService {

	private final StuffReqRepository stuffReqRepository;
    private final MemberRepository memberRepository;
    private final StuffRepository stuffRepository;
	
	private String convertStatus(StuffReqStatus status) {
		return switch (status) {
			case PENDING -> "승인대기";
			case APPROVED -> "승인";
			case REJECTED -> "반려";
		};
	}
	
	private Member convertToMember(MemberDTO memberDTO) {
        return Member.builder()
                .id(memberDTO.getId())
                .name(memberDTO.getName())
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .position(memberDTO.getPosition())
                .tel(memberDTO.getTel())
                .role(memberDTO.getRole())
                .build();
    }
	
	private StuffReqDTO convertToDTO(StuffReq stuffReq) {
	    return StuffReqDTO.builder()
	            .id(stuffReq.getId())
	            .memberName(stuffReq.getMember().getName()) 
	            .stuffName(stuffReq.getStuff().getName())   
	            .reqDate(stuffReq.getReqDate())
	            .quantity(stuffReq.getQuantity())
	            .purpose(stuffReq.getPurpose())
	            .reject(stuffReq.getReject())
	            .statusText(convertStatus(stuffReq.getStatus()))
	            .build();
	}

	
	public Page<StuffReqDTO> getStuffReqList(MemberDTO loginMemberDTO, Pageable pageable) {
		
		Member loginMember = convertToMember(loginMemberDTO);
		
		Page<StuffReq> stuffReqPage = stuffReqRepository.findByMember(loginMember, pageable);
		
		return stuffReqPage.map(stuffReq -> StuffReqDTO.builder()
				.id(stuffReq.getId())
				.memberName(stuffReq.getMember().getName())
				.stuffName(stuffReq.getStuff().getName())
				.reqDate(stuffReq.getReqDate())
				.quantity(stuffReq.getQuantity())
				.purpose(stuffReq.getPurpose())
				.reject(stuffReq.getReject())
				.statusText(convertStatus(stuffReq.getStatus()))
				.build());
		
	}
	
	public StuffReq saveStuffReq(String memberName, String stuffName, int quantity, String purpose) {
		
        Member member = memberRepository.findByName(memberName)
            .orElseThrow(() -> new RuntimeException("Member not found"));

        Stuff stuff = stuffRepository.findByName(stuffName)
            .orElseThrow(() -> new RuntimeException("Stuff not found"));

        StuffReq stuffReq = new StuffReq();
        stuffReq.setMember(member);
        stuffReq.setStuff(stuff);
        stuffReq.setQuantity(quantity);
        stuffReq.setPurpose(purpose);
        stuffReq.setReqDate(LocalDate.now());
        stuffReq.setStatus(StuffReqStatus.PENDING);

        return stuffReqRepository.save(stuffReq);
    }
	
	public StuffReqDTO cancelRequest(Long id) {
		
		Optional<StuffReq> optionalStuffReq = stuffReqRepository.findById(id);
		
		if (optionalStuffReq.isPresent()) {
	        StuffReq stuffReq = optionalStuffReq.get();
	        StuffReqDTO stuffReqDTO = convertToDTO(stuffReq);
	        
	        stuffReqRepository.deleteById(id);

	        return stuffReqDTO;
	    }

	    return null;
	}

	public Page<StuffReqDTO> getStuffReqListByStatus(MemberDTO loginMemberDTO, String status, Pageable pageable) {
	    Member loginMember = convertToMember(loginMemberDTO);
	    StuffReqStatus filterStatus = StuffReqStatus.valueOf(status.toUpperCase());

	    Page<StuffReq> stuffReqPage = stuffReqRepository.findByMemberAndStatus(loginMember, filterStatus, pageable);

	    return stuffReqPage.map(stuffReq -> convertToDTO(stuffReq));
	}
	
	public Page<StuffReqDTO> getStuffReqListByStatusAndMonth(MemberDTO loginMemberDTO, String status, int month, Pageable pageable) {
		
		Member loginMember = convertToMember(loginMemberDTO);
		
		StuffReqStatus filterStatus = StuffReqStatus.valueOf(status.toUpperCase()); 
		
		YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), month); 
		LocalDate startDate = yearMonth.atDay(1); 
		LocalDate endDate = yearMonth.atEndOfMonth();
		
		Page<StuffReq> stuffReqPage = stuffReqRepository.findByMemberAndStatusAndReqDateBetween(loginMember, filterStatus, startDate, endDate, pageable);
		
		return stuffReqPage.map(stuffReq -> convertToDTO(stuffReq));
		
	}
	
	public Page<StuffReqDTO> getStuffReqListByMonth(MemberDTO loginMemberDTO, int month, Pageable pageable) {
		
		Member loginMember = convertToMember(loginMemberDTO);
		
		YearMonth yearMonth = YearMonth.of(LocalDate.now().getYear(), month); 
		LocalDate startDate = yearMonth.atDay(1); LocalDate endDate = yearMonth.atEndOfMonth();
		
		Page<StuffReq> stuffReqPage = stuffReqRepository.findByMemberAndReqDateBetween(loginMember, startDate, endDate, pageable); 
		
		return stuffReqPage.map(stuffReq -> convertToDTO(stuffReq));
		
	}
	
}
