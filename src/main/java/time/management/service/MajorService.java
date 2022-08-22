package time.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.Major;
import time.management.domain.Member;
import time.management.repository.MajorRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MajorService {

    private final MajorRepository majorRepository;

    @Transactional
    public void joinMajor(Major major){
        majorRepository.join(major);
    }

    public Major findByMajorName(String name){
        return majorRepository.findOne(name);
    }
}
