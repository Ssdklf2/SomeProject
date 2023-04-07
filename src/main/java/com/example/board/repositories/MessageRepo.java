package com.example.board.repositories;

import com.example.board.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {
    List<Message> findByTitle(String title);

}
