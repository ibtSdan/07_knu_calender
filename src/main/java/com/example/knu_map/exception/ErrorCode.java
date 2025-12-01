package com.example.knu_map.exception;

public enum ErrorCode {
    INVALID_JSON(4000, "필드 타입을 확인해주세요."),
    VALIDATION_FAILED(4002, "요청 데이터가 유효하지 않습니다."),

    CATEGORY_REQUIRED(4101, "카테고리를 선택해주세요."),
    CATEGORY_INVALID(4102, "올바른 카테고리를 선택해주세요."),
    TITLE_REQUIRED(4103, "제목을 입력해주세요."),
    TITLE_TOO_LONG(4104, "제목은 최대 200자까지 가능합니다."),
    DATE_REQUIRED(4105, "날짜를 입력해주세요."),
    DATE_INVALID(4106, "날짜 형식이 올바르지 않습니다."),
    LINK_REQUIRED(4107, "링크를 입력해주세요."),
    LINK_TOO_LONG(4108, "링크 길이는 최대 1000자까지 가능합니다."),
    LINK_INVALID(4109, "링크는 http:// 또는 https://로 시작해야 합니다.");


    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
