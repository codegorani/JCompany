package com.spring.jcompany.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserLevel {
    DD_INTERN("INTERN", "인턴"),
    DC_STAFF("STAFF", "사원"),
    DB_ASSOCIATE("ASSOCIATE", "주임"),
    DA_ASSOCIATE_MANAGER("ASSOCIATE_MANAGER", "대리"),
    CC_MANAGER("MANAGER", "과장"),
    CB_SENIOR_MANAGER("SENIOR_MANAGER", "차장"),
    CA_EXECUTIVE_MANAGER("EXECUTIVE_MANAGER", "부장"),
    BC_ASSOCIATE_EXECUTIVE_DIRECTOR("ASSOCIATE_EXECUTIVE_DIRECTOR", "이사"),
    BB_EXECUTIVE_DIRECTOR("EXECUTIVE_DIRECTOR", "상무이사"),
    BA_EXECUTIVE_VICE_PRESIDENT("EXECUTIVE_VICE_PRESIDENT", "전무이사"),
    AB_SENIOR_EXECUTIVE_VICE_PRESIDENT("SENIOR_EXECUTIVE_VICE_PRESIDENT", "부사장"),
    AA_PRESIDENT("PRESIDENT", "사장");

    private final String key;
    private final String title;
}
