package com.example.tae.controller;

import com.example.tae.entity.Account.Member;
import com.example.tae.entity.Account.MemberDTO;
import com.example.tae.entity.Account.MemberRole;
import com.example.tae.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
public class LoginController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/total")
    public String total(@AuthenticationPrincipal MemberDTO memberDTO) {
        return "total";
    }

    @GetMapping("/memberList")
    public String mList(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "memberList";
    }

    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }

    @PostMapping("/joinProcess")
    public String joinProcess(@RequestParam("id") String id, @RequestParam("email") String email,
                              @RequestParam("password") String password, @RequestParam("password2") String password2, Model model){
        if(memberRepository.existsById(id)){
            model.addAttribute("error","idExists");
            return "join";
        }

        if(!password.equals(password2)) {
            model.addAttribute("error","passwordMismatch");
            return "join";
        }

        Member newMember = Member.builder().id(id).email(email).password(passwordEncoder.encode(password))
                .fromSocial(false).build();
        newMember.addMemberRole(MemberRole.USER);
        memberRepository.save(newMember);

        return "redirect:/memberList";
    }

    @PostMapping("/del/{id}")
    public String delM(@PathVariable("id") String id){
        memberRepository.deleteById(id);
        return "redirect:/memberList";
    }

    @PostMapping("/resetPassword")
    public String resPassword(@RequestParam(name = "id") String id, @RequestParam(name = "email") String email, Model model){
        try {
            Optional<Member> optionalMember = memberRepository.findById(id);

            if(optionalMember.isPresent()) {
                memberRepository.updatePasswordById(id, passwordEncoder.encode(email));

                model.addAttribute("resetPasswordAlert", true);
                model.addAttribute("resetPasswordMessage", "임시 비밀번호를 당신의 이메일로 지정 완료");
            } else {
                // 주어진 id로 멤버를 찾을 수 없는 경우 처리
                model.addAttribute("resetPasswordAlert", true);
                model.addAttribute("resetPasswordMessage", "멤버를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            log.error("Exception occurred during password reset", e);
            model.addAttribute("resetPasswordAlert", true);
            model.addAttribute("resetPasswordMessage", "비밀번호 초기화 중에 오류가 발생했습니다.");
        }
        return "redirect:/memberList";
    }
}