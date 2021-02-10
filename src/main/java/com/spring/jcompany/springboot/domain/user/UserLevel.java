package com.spring.jcompany.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserLevel {
    INTERN("INTERN", "인턴"),
    STAFF("STAFF", "사원"),
    ASSOCIATE("ASSOCIATE", "주임"),
    ASSOCIATE_MANAGER("ASSOCIATE_MANAGER", "대리"),
    MANAGER("MANAGER", "과장"),
    SENIOR_MANAGER("SENIOR_MANAGER", "차장"),
    EXECUTIVE_MANAGER("EXECUTIVE_MANAGER", "부장"),
    ASSOCIATE_EXECUTIVE_DIRECTOR("ASSOCIATE_EXECUTIVE_DIRECTOR", "이사"),
    EXECUTIVE_DIRECTOR("EXECUTIVE_DIRECTOR", "상무이사"),
    EXECUTIVE_VICE_PRESIDENT("EXECUTIVE_VICE_PRESIDENT", "전무이사"),
    SENIOR_EXECUTIVE_VICE_PRESIDENT("SENIOR_EXECUTIVE_VICE_PRESIDENT", "부사장"),
    PRESIDENT("PRESIDENT", "사장");

    private final String key;
    private final String title;
}
