package com.example.worktest.user;

import com.example.worktest.sign.Sign;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import com.example.worktest.work.Work;
@Entity
@Getter
@Setter
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private LocalDateTime loginTime;

    private Integer level;

    // User와 Work 간의 1:N 관계 (User는 여러 개의 Work를 가질 수 있음)
    @OneToMany(mappedBy = "user")
    private List<Work> works;

    // User와 Sign 간의 1:N 관계 (User는 여러 개의 Sign을 가질 수 있음)
    @OneToMany(mappedBy = "user")
    private List<Sign> signs;
    // 엔티티가 처음 생성될 때 createDate를 자동으로 설정
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }

    // 엔티티가 업데이트될 때 updateDate를 자동으로 설정
    @PreUpdate
    protected void onUpdate() {
        updateDate = LocalDateTime.now();
    }
}
