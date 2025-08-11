package kr.ac.hansung.cse.board_and_chatting.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


public interface BaseEntity {

    @PrePersist
    void onCreate();

    @PreUpdate
    void onUpdate();
}
