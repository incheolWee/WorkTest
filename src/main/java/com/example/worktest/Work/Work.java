package com.example.worktest.Work;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    @Id
    private Long id;
    private Long userId; //생성자 사용자의 ID
    private Long fileId; //파일ID 워크의 원본 파일
    private boolean shared; //공유
    private boolean trashed; //휴지통
    private  boolean finish; // 완료
    private LocalDateTime createDate; //생성일
    private LocalDateTime updateDate; //수정일
    private LocalDateTime deleteDate; //삭제일
    private LocalDateTime openDate; //열람일
}
