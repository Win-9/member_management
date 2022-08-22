package time.management.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.Major;

import javax.persistence.EntityManager;


@RequiredArgsConstructor
@Repository
public class MajorRepository {
    private final EntityManager em;

    @Transactional
    public void join(Major major){
        em.persist(major);
    }

    public Major findOne(String majorName){
        return em.find(Major.class, majorName);
    }
}
