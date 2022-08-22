package time.management.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import time.management.domain.*;
import time.management.dto.MemberFormDto;
import time.management.service.MajorService;
import time.management.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MajorService majorService;

    @GetMapping("/management/members")
    public String memberListController(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        for (Member member : members) {
            log.info("name = {}", member.getName());
            log.info("index = {}", member.getIndex());
        }

        return "main/members";
    }

    @GetMapping("/management/addMember")
    public String addMemberController(Model model) {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("studentStatus", StudentStatus.values());
        model.addAttribute("positions", Position.values());

        return "main/addMemberForm";
    }

    @PostMapping("/management/addMember")
    public String addMemberFormController(@ModelAttribute MemberFormDto memberFormDto) {

        log.info("name = {}", memberFormDto.getName());
        log.info("major = {}", memberFormDto.getMajor());
        log.info("id = {}", memberFormDto.getStudentID());

        Major findMajor = majorService.findByMajorName(memberFormDto.getMajor());

        //처음온 Major 생성
        if (findMajor == null) {
            findMajor = new Major();
            findMajor.createBasicMajor(memberFormDto.getMajor());
            majorService.joinMajor(findMajor);
        }

        Member member = new Member(memberFormDto.getStudentID(), memberFormDto.getName(),
                memberFormDto.getGrade(), findMajor);

        member.addDetails(memberFormDto.getPosition(), memberFormDto.getPhoneNumber(),
                memberFormDto.getStudentState(), memberFormDto.getGender(),
                new CountInfo(0,0,0));

        memberService.joinMember(member);

        return "redirect:/";
    }

    @GetMapping("/management/modify")
    public String modifyController(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "main/modifyMember";
    }

}
