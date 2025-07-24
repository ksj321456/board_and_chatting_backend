package kr.ac.hansung.cse.board_and_chatting.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import kr.ac.hansung.cse.board_and_chatting.dto.UserDto;
import kr.ac.hansung.cse.board_and_chatting.entity.enums.Authority;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Authority authority;

    @Lob
    private byte[] userPicture;

    private LocalDateTime createdAt;

    // User -> UserDto 변환 메소드
    public static UserDto toDto(User user) {
        UserDto userDto = UserDto.builder()
                .nickname(user.getNickname())
                .userId(user.getUserId())
                .password(user.getPassword())
                .authority(user.getAuthority())
                .userPicture(user.getUserPicture())
                .build();
        return userDto;
    }
}
