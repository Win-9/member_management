package time.management.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import time.management.domain.Major;
import time.management.domain.Member;

import javax.persistence.EntityManager;


@RequiredArgsConstructor
@Repository
public class MajorRepository {
    private final EntityManager em;

    public void join(Major major){
        em.persist(major);
    }
}
