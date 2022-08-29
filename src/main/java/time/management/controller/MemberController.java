package time.management.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import time.management.domain.*;
import time.management.dto.MemberFormDto;
import time.management.service.MajorService;
import time.management.service.MemberService;
import time.management.validation.MemberValidator;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MajorService majorService;
    private final MemberValidator memberValidator;

    /**
     * memberList Get
     *
     * @param model
     * @return
     */
    @GetMapping("/management/members")
    public String memberListController(Model model, @RequestParam(defaultValue = "1") int page) {
//        List<Member> members = memberService.findAll();
        int realPage = (page - 1) * 10;
        List<Member> pageMember = memberService.findByPage(realPage, 10);
        model.addAttribute("members", pageMember);

        int totalSize = memberService.findAll().size();

        model.addAttribute("size", totalSize/10);



        for (Member member : pageMember) {
            log.info("name = {}", member.getName());
        }

        return "main/members";
    }

    /**
     * addMember Get
     *
     * @param model
     * @return
     */
    @GetMapping("/management/addMember")
    public String addMemberController(Model model) {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("studentStatus", StudentStatus.values());
        model.addAttribute("positions", Position.values());
        model.addAttribute("member", new MemberFormDto());

        return "main/addMemberForm";
    }

    /**
     * addMemberForm Post
     *
     * @param memberFormDto
     * @return
     */
    @PostMapping("/management/addMember")
    public String addMemberFormController(@ModelAttribute(name = "member") MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

        log.info("name = {}", memberFormDto.getName());
        log.info("major = {}", memberFormDto.getMajor());
        log.info("id = {}", memberFormDto.getStudentID());

        memberValidator.validate(memberFormDto, bindingResult);


        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            model.addAttribute("studentStatus", StudentStatus.values());
            model.addAttribute("positions", Position.values());
            return "main/addMemberForm";
        }

        Major findMajor = majorService.findByMajorName(memberFormDto.getMajor());

        //처음온 Major 생성
        if (findMajor == null) {
            findMajor = new Major();
            findMajor.createBasicMajor(memberFormDto.getMajor());
            majorService.joinMajor(findMajor);
        }

        Member member = new Member();
        member.changeMemberInfo(memberFormDto.getStudentID(), memberFormDto.getName(),
                memberFormDto.getGrade(), findMajor);

        member.changeMemberInfoDetails(memberFormDto.getPosition(), memberFormDto.getPhoneNumber(),
                memberFormDto.getStudentState(), memberFormDto.getGender(),
                new CountInfo(0, 0, 0));

        memberService.joinMember(member);

        return "redirect:/management/addMember";
    }

    /**
     * modifyMember Get
     *
     * @param model
     * @return
     */
    @GetMapping("/management/modify")
    public String modifyController(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "main/modifyMemberList";
    }

    /**
     * modifyMember Get
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/management/modify/{id}")
    public String modifyMemberController(@PathVariable String id, Model model) {
        log.info("modifyMemberController");

        Member findMember = memberService.findByMemberId(id);
        model.addAttribute("findMember", findMember);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("studentStatuses", StudentStatus.values());
        model.addAttribute("positions", Position.values());

        return "main/modifyMember";
    }

    @Transactional
    @PostMapping("/management/modify/{id}")
    public String modifyMemberFormController(@PathVariable String id, @ModelAttribute MemberFormDto memberFormDto) {
        Member findMember = memberService.findByMemberId(id);

        Major findMajor = majorService.findByMajorName(memberFormDto.getMajor());

        if (findMajor == null) {
            findMajor = new Major();
            findMajor.createBasicMajor(memberFormDto.getMajor());
            majorService.joinMajor(findMajor);//새로운 major 생성
        }

        memberService.updateMember(findMember, memberFormDto, findMajor);//새로운 major 생성

        return "redirect:/management/modify/{id}";
    }

    @GetMapping("/management/attendList")
    public String memberAttendListController(Model model){

        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "main/attendList";
    }

    @GetMapping("/management/delete/{id}")
    public String deleteMemberController(@PathVariable String id) {
        log.info("deleteMemberController");
        Member findMember = memberService.findByMemberId(id);
        memberService.deleteMember(findMember);

        return "redirect:/management/members";
    }
}
