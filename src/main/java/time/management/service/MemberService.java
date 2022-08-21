package time.management.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.Member;
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
}
