package com.of.notification.dto;

import java.time.LocalDate;

import com.of.notification.entity.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
	
	private Long id;
	private String memberName;
	private String content;
	private LocalDate createdAt;
	private NotificationStatus status;
	
}