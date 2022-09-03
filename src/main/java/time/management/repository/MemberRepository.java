package time.management.repository;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import time.management.domain.Member;
import time.management.dto.MemberSearchDto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {
    private final EntityManager em;

    public void join(Member member){
        em.persist(member);
    }

    public Member findById(String id){
        return em.find(Member.class, id);
    }

    public void deleteMember(Member member){
        em.remove(member);
    }

    public List<Member> findByName(String name){
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :username", Member.class)
                .setParameter("username", name)
                .getResultList();
    }

    public List<Member> findByGrade(int grade){
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

    public List<Member> findManyQualification (MemberSearchDto memberSearchDto) {

        String jpql = "select m from Member m join m.major j ";
        boolean flag = true;

        if (memberSearchDto.getGrade() != null) {
            if (flag) {
                jpql += "where ";
                flag = false;
            }
            jpql += "m.grade =: grade ";
        }

        if (StringUtils.hasText(memberSearchDto.getMajor())) {

            if (flag) {
                jpql += "where ";
                flag = false;
            }else{
                jpql += "and ";
            }

            jpql += "j.name =: major ";
        }

        if (StringUtils.hasText(memberSearchDto.getStudentID())) {

            if (flag) {
                jpql += "where ";
                flag = false;
            }else{
                jpql += "and ";
            }

            jpql += "m.id =: id";
        }

        log.info("query = {}", jpql);

        TypedQuery<Member> memberQuery = em.createQuery(jpql, Member.class);

        if (memberSearchDto.getGrade() != null) {
            memberQuery.setParameter("grade", memberSearchDto.getGrade());
        }
        if (StringUtils.hasText(memberSearchDto.getMajor())) {
            memberQuery.setParameter("major", memberSearchDto.getMajor());
        }

        if (StringUtils.hasText(memberSearchDto.getStudentID())) {
            memberQuery.setParameter("id", memberSearchDto.getStudentID());
        }

        return memberQuery.getResultList();

    }
}
