package com.of.stuff.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.of.member.dto.MemberDTO;
import com.of.stuff.dto.StuffReqDTO;
import com.of.stuff.service.StuffReqService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StuffController {

	private final StuffReqService stuffReqService;
	
	@GetMapping("/stuff/stuffReqList")
	public String getStuffReqList(
	    @PageableDefault(size = 10, sort = "reqDate", direction = Sort.Direction.DESC) Pageable pageable,
	    @RequestParam(name = "status", required = false, defaultValue = "all") String status,
	    @RequestParam(name = "month", required = false, defaultValue = "all") String month,
	    Model model, HttpSession session) {

	    MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

	    if (loginMember == null) {
	        return "redirect:/";
	    }

	    Page<StuffReqDTO> stuffReqPage;
	    if ("all".equals(status) && "all".equals(month)) {
	        stuffReqPage = stuffReqService.getStuffReqList(loginMember, pageable);
	    } else if (!"all".equals(status) && !"all".equals(month)) {
	        stuffReqPage = stuffReqService.getStuffReqListByStatusAndMonth(loginMember, status, Integer.parseInt(month), pageable);
	    } else if (!"all".equals(status)) {
	        stuffReqPage = stuffReqService.getStuffReqListByStatus(loginMember, status, pageable);
	    } else {
	        stuffReqPage = stuffReqService.getStuffReqListByMonth(loginMember, Integer.parseInt(month), pageable);
	    }

	    model.addAttribute("stuffReqPage", stuffReqPage);
	    model.addAttribute("status", status);
	    model.addAttribute("month", month);

	    return "stuff/stuffReqList";
	}
	
	@GetMapping("/stuff/stuffReq")
	public String getStuffReq(HttpSession session) {
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		if (loginMember == null) {
			return "redirect:/";
		}
		
		return "stuff/stuffReq";
	}
	
	@PostMapping("/stuff/stuffReq")
	public String postStuffReq(@RequestParam("memberName") String memberName, @RequestParam("stuffName") String stuffName, @RequestParam("quantity") int quantity, @RequestParam("purpose") String purpose, HttpSession session, RedirectAttributes redirectAttributes) {

		stuffReqService.saveStuffReq(memberName, stuffName, quantity, purpose);
		
        return "redirect:/stuff/stuffReqList";
	}
	
	@PostMapping("/stuff/cancelStuffReq/{id}")
    public ResponseEntity<StuffReqDTO> cancelRequest(@PathVariable("id") Long id, HttpSession session) {
		
		StuffReqDTO stuffReq = stuffReqService.cancelRequest(id);
		
		return ResponseEntity.ok(stuffReq);
    }
}
