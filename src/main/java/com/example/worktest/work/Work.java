package com.example.worktest.work;

import jakarta.persistence.*;
import com.example.worktest.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name="work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;

    //null 값을 허용하기 위해서 Integer , Boolean으로 선언
    private String name;
    private String path;
    private Integer xSize;
    private Integer ySize;
    private Boolean shared;
    private Boolean trashed;
    private Boolean finish;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
    private LocalDateTime openDate;

    @PrePersist
    protected void onCreate(){ //초기값 생성
        LocalDateTime now = LocalDateTime.now();
        this.createDate = now;
        this.updateDate = now;
        this.shared = false;
        this.trashed = false;
        this.finish = false;
    }
    @PreUpdate
    protected void onUpdate(){ //수정시
        this.updateDate = LocalDateTime.now();
    }
}
