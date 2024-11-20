package com.of.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.of.notification.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
