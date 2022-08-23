package time.management.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.Major;
import time.management.domain.Member;
import time.management.service.MemberService;

import java.util.List;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MajorRepository majorRepository;

    @Test
    @Transactional
    public void join(){
        Major major = createMajor();
        Member member = createMember(major);

        memberService.joinMember(member);

        System.out.println("=====");
        Member findMember = memberService.findByMemberId("2018111");
        System.out.println("=====");

        System.out.println("===!==");
        List<Member> all = memberService.findAll();
        System.out.println("===!==");

        Assertions.assertEquals(findMember, member);
    }

    private Member createMember(Major major) {
        Member member = new Member();
        member.changeMemberInfo("2018111", "응우옌", 1, major);
        return member;
    }

    private Major createMajor() {
        Major major = new Major();
        major.createBasicMajor("Computer Engin");
        return major;
    }

    @Test
    @Transactional
    public void remove(){
        Major major = createMajor();
        Member member = createMember(major);
        majorRepository.join(major);
        memberService.joinMember(member);

        memberService.deleteMember(member);

        List<Member> all = memberService.findAll();
        Assertions.assertEquals(all.size(), 0);

    }

}