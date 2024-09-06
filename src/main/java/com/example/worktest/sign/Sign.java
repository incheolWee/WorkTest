package com.example.worktest.sign;

import jakarta.persistence.*;
import com.example.worktest.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sign")
public class Sign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User와의 다대일 관계 (각 Sign은 한 명의 User에 속함)
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    // 서명의 경로 (파일 저장 경로 등)
    private String path;

    private LocalDateTime createDate;

    private Boolean saved;

    // 엔티티가 처음 생성될 때 createDate를 자동으로 설정
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }
}
