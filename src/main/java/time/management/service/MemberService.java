package time.management.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.Major;
import time.management.domain.Member;
import time.management.dto.MemberFormDto;
import time.management.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = false)
    public void joinMember(Member member){
         memberRepository.join(member);
    }

    public Member findByMemberId(String id){
        return memberRepository.findById(id);
    }

    @Transactional
    public void deleteMember(Member member) {
        memberRepository.deleteMember(member);
    }

    public List<Member> findByMemberName(String name){
        return memberRepository.findByName(name);
    }

    public List<Member> findByMemberGrade(int grade){
        return memberRepository.findByGrade(grade);
    }

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Transactional
    public void updateMember(Member member, MemberFormDto memberFormDto, Major major){
        member.getMajor().deleteMember(member);// 속한 Major 변경
        member.changeMemberInfo(memberFormDto.getName(), memberFormDto.getGrade(), major);

        member.changeMemberInfoDetails(memberFormDto.getPosition(), memberFormDto.getPhoneNumber(),
                memberFormDto.getStudentState(), memberFormDto.getGender());
    }
}
