package kr.ac.hansung.cse.board_and_chatting.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    ADMIN("관리자"),
    USER("유저");

    private final String field;

    @JsonValue
    public String getField() {
        return field;
    }

    @JsonCreator
    public static Authority fromField(String field) {
        for (Authority authority : Authority.values()) {
            if (authority.getField().equals(field)) {
                return authority;
            }
        }
        throw new IllegalArgumentException("해당 Authority는 없습니다.");
    }
}
