package com.example.worktest.Work;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Table(name = "work_test")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId; //생성자 사용자의 ID
    private Long fileId; //파일ID 워크의 원본 파일

    private boolean shared=false; //공유
    private boolean trashed=false; //휴지통
    private  boolean finish=false; // 완료
    private LocalDateTime createDate=LocalDateTime.now(); //생성일
    private LocalDateTime updateDate=LocalDateTime.now(); //수정일
    private LocalDateTime deleteDate; //삭제일
    private LocalDateTime openDate; //열람일

//    @PrePersist //DB에 저장하기전에 실행
//    protected void onCreate(){ //생성시
//        LocalDateTime now = LocalDateTime.now();
//        this.createDate = now; //현재시간
//        this.updateDate = now;
//    }
    @PreUpdate //DB에 수정되기 전에 실행
    protected void onUpdate(){ //수정시
        this.updateDate = LocalDateTime.now(); //현재시간
    }
}
