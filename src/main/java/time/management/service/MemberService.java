package time.management.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.Major;
import time.management.domain.Member;
import time.management.dto.MemberAttendSearchDto;
import time.management.dto.MemberFormDto;
import time.management.dto.MemberSearchDto;
import time.management.repository.MemberRepository;

import javax.persistence.TypedQuery;
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

    public List<Member> findByPage(int offset, int limit) {
        return memberRepository.findPage(offset, limit);
    }

    @Transactional
    public void updateMemberCountInfo(Member member , int attendCount, int quizCount, int questionCount) {
        member.changeCountInfo(attendCount, quizCount, questionCount);
    }

    public List<Member> findByManyQualificationMemberListWithPaging(MemberSearchDto memberSearchDto, int offset, int limit) {
        TypedQuery<Member> memberQuery = memberRepository.findMemberListQualification(memberSearchDto);
        return memberQuery
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public int findByManyQualificationMemberListTotalCount(MemberSearchDto memberSearchDto){
        TypedQuery<Member> memberQuery = memberRepository.findMemberListQualification(memberSearchDto);

        return memberQuery.getResultList().size();
    }

    public List<Member> findByManyQualificationMemberAttendWithPaging(MemberAttendSearchDto memberAttendSearchDto, int offset, int limit){
        TypedQuery<Member> queryMember = memberRepository.findMemberAttendQualification(memberAttendSearchDto);

        return queryMember
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public int findByManyQualificationMemberAttendTotalCount(MemberAttendSearchDto memberAttendSearchDto){
        TypedQuery<Member> memberQuery = memberRepository.findMemberAttendQualification(memberAttendSearchDto);

        return memberQuery.getResultList().size();
    }
}
