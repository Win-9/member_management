package time.management.repository;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import time.management.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void join(Member member){
        em.persist(member);
        member.addMajor(member);
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

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
