package time.management.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import time.management.domain.Member;
import time.management.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/management/members")
    public String memberListController(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        for (Member member : members) {
            log.info("index = {}", member.getIndex());
        }
        return "main/members";
    }
}
