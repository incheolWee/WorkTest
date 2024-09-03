package com.example.worktest.Work;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkDTO {
    private Long id;
    private Long userId; //생성자 사용자의 ID
    private Long fileId; //파일ID 워크의 원본 파일
    private boolean shared; //공유
    private boolean trashed; //휴지통
    private  boolean finish; // 완료
    private String createDate; //생성일
    private String updateDate; //수정일
    private String deleteDate; //삭제일
    private String openDate; //열람일

}
