package kr.ac.hansung.cse.board_and_chatting.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    FREE("자유 게시판")


    ;

    private final String name;

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Category fromName(String name) {
        for (Category category : Category.values()) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        throw new IllegalArgumentException(name);
    }
}
