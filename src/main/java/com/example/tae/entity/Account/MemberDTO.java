package com.example.tae.entity.Account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

@Log4j2
@Getter
@Setter
@ToString
public class MemberDTO extends User {
    private final Member member;

    public MemberDTO(Member member) {
        super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(getRoles(member)));
        this.member = member;
    }

    private static String[] getRoles(Member member) {
        String[] roles = member.getRoleSet().stream().map(role -> "ROLE_" + role.name()).toArray(String[]::new);
        log.info("Roles: {}", Arrays.toString(roles));
        return roles;
    }
}