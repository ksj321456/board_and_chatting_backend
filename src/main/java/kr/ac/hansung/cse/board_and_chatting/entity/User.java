package kr.ac.hansung.cse.board_and_chatting.entity;

import jakarta.persistence.*;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_login_id_unique",
                        columnNames = {"user_login_id"}
                )
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, name = "user_login_id")
    private String userId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    @Lob
    private byte[] userPicture;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

}
