package com.spring.jcompany.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserTeam {
    STRATEGIC_PLANNING("STRATEGIC_PLANNING", "전략기획팀"),
    MANAGEMENT_SUPPORT("MANAGEMENT_SUPPORT", "경영지원팀"),
    FINANCE("FINANCE", "재무팀"),
    QUALITY_GUARANTIED("QUALITY_GUARANTIED", "품질보증팀"),
    AIR_MANAGEMENT_SYSTEM("AIR_MANAGEMENT_SYSTEM", "항공관리시스템팀"),
    AIR_SALES_SYSTEM("AIR_SALES_SYSTEM", "항공영업시스템팀"),
    ONLINE_PLATFORM("ONLINE_PLATFORM", "온라인플랫폼팀"),
    LCC_AIRPORT_BUSINESS("LCC_AIRPORT_BUSINESS", "LCC/공항사업팀"),
    LCC_AIRPORT_CONSULTING("LCC_AIRPORT_CONSULTING", "LCC/공항컨설팅팀"),
    SYSTEM_TECHNOLOGY_MANAGEMENT("SYSTEM_TECHNOLOGY_MANAGEMENT", "시스템기술관리팀");

    private final String key;
    private final String team;
}
