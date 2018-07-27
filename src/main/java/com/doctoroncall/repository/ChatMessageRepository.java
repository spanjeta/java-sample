package com.doctoroncall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctoroncall.entities.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{

}
