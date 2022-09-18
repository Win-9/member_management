package time.management.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import time.management.domain.Member;
import time.management.dto.MemberAttendListOrderDto;
import time.management.dto.MemberAttendSearchDto;
import time.management.dto.MemberSearchDto;
import time.management.dto.MemberOrderDto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {
    private final EntityManager em;

    public void join(Member member) {
        em.persist(member);
    }

    public Member findById(String id) {
        return em.find(Member.class, id);
    }

    public void deleteMember(Member member) {
        em.remove(member);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :username", Member.class)
                .setParameter("username", name)
                .getResultList();
    }

    public List<Member> findByGrade(int grade) {
        return em.createQuery("SELECT m FROM Member m WHERE m.grade = :grade", Member.class)
                .setParameter("grade", grade)
                .getResultList();
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findPage(int offset, int limit) {
        return em.createQuery("select m from Member m", Member.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public TypedQuery<Member> findMemberListQualification(MemberSearchDto memberSearchDto, MemberOrderDto orderDto) {

        String jpql = "select m from Member m join m.major j ";
        boolean flag = true;

        //학년
        if (memberSearchDto.getGrade() != null) {
            if (flag) {
                jpql += "where ";
                flag = false;
            }
            jpql += "m.grade =: grade ";
        }

        // 전공
        if (StringUtils.hasText(memberSearchDto.getMajor())) {
            if (flag) {
                jpql += "where ";
                flag = false;
            } else {
                jpql += "and ";
            }

            jpql += "j.name like :major ";
        }

        // 잭책
        if (memberSearchDto.getPosition() != null) {
            if (flag) {
                jpql += "where ";
                flag = false;
            } else {
                jpql += "and ";
            }

            jpql += "m.position =: position ";
        }

        //이름
        if (StringUtils.hasText(memberSearchDto.getName())) {
            if (flag) {
                jpql += "where ";
                flag = false;
            } else {
                jpql += "and ";
            }

            jpql += "m.name like :name ";
        }

        // 학번
        if (StringUtils.hasText(memberSearchDto.getStudentID())) {
            if (flag) {
                jpql += "where ";
                flag = false;
            } else {
                jpql += "and ";
            }

            jpql += "m.id like :id";
        }

        //정렬옵션

        if (orderDto.getSortBase() != null) {
            if (orderDto.getSortBase().equals("이름")) {
                jpql += " Order by m.name";
            }else if(orderDto.getSortBase().equals("학번")){
                jpql += " Order by m.id";
            }else{
                jpql += "Order by m.grade";
            }
        }


        if (orderDto.getOrderOption() != null) {
            if (orderDto.getOrderOption().equals("오름차순")) {
                jpql += " asc";
            }else{
                jpql += " desc";
            }
        }

        log.info("query = {}", jpql);

        TypedQuery<Member> memberQuery = em.createQuery(jpql, Member.class);


        if (memberSearchDto.getGrade() != null) {
            memberQuery.setParameter("grade", memberSearchDto.getGrade());
        }
        if (StringUtils.hasText(memberSearchDto.getMajor())) {
            memberQuery.setParameter("major", memberSearchDto.getMajor() + "%");
        }
        if (StringUtils.hasText(memberSearchDto.getName())) {
            memberQuery.setParameter("name", memberSearchDto.getName() + "%");
        }
        if (memberSearchDto.getPosition() != null) {
            memberQuery.setParameter("position", memberSearchDto.getPosition());
        }
        if (StringUtils.hasText(memberSearchDto.getStudentID())) {
            memberQuery.setParameter("id", memberSearchDto.getStudentID() + "%");
        }

        return memberQuery;
    }

    public TypedQuery<Member> findMemberAttendQualification(MemberAttendSearchDto memberAttendSearchDto, MemberAttendListOrderDto memberAttendListOrderDto) {

        String jpql = "select m from Member m join m.major j ";
        boolean flag = true;

        //동적쿼리
        if (StringUtils.hasText(memberAttendSearchDto.getName())) {
            if (flag) {
                jpql += "where ";
                flag = false;
            }
            jpql += "name like: name ";
        }

        if (StringUtils.hasText(memberAttendSearchDto.getStudentID())) {
            if (flag) {
                jpql += "where ";
                flag = false;
            }

            jpql += "STUDENT_ID like: id";
        }

        // 정렬

        if (StringUtils.hasText(memberAttendListOrderDto.getSortBase())){
            if (memberAttendListOrderDto.getSortBase().equals("Attend")){
                jpql +=" Order by m.countInfo.attendanceCount";
            }else if(memberAttendListOrderDto.getSortBase().equals("Quiz")){
                jpql +=" Order by m.countInfo.quizCount";
            }else{
                jpql += " Order by m.countInfo.questionCount";
            }
        }

        if (StringUtils.hasText(memberAttendListOrderDto.getOrderOption())){
            if (memberAttendListOrderDto.getOrderOption().equals("내림차순")){
                jpql +=" desc";
            }else{
                jpql += " asc";
            }
        }


        log.info("query = {}", jpql);


        TypedQuery<Member> memberQuery = em.createQuery(jpql, Member.class);

        if (StringUtils.hasText(memberAttendSearchDto.getName())) {
            memberQuery.setParameter("name", memberAttendSearchDto.getName() + "%");
        }

        if (StringUtils.hasText(memberAttendSearchDto.getStudentID())) {
            memberQuery.setParameter("id", memberAttendSearchDto.getStudentID() + "%");
        }

        return memberQuery;

    }
}
