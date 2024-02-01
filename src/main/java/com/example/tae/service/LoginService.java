package com.example.tae.service;

import com.example.tae.entity.Account.Member;
import com.example.tae.entity.Account.MemberDTO;
import com.example.tae.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to load user by username: {}", username);
        Optional<Member> memberOptional = memberRepository.findByID(username, false);

        if (memberOptional.isPresent()) {
            return new MemberDTO(memberOptional.get());
        } else {
            log.error("Not found user name : {}", username);

            throw new UsernameNotFoundException("Not found user name : " + username);
        }
    }
}