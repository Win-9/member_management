package time.management;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import time.management.domain.*;
import time.management.service.MajorService;
import time.management.service.MemberService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDbInit {

    private final TestInit testInit;

    @PostConstruct
    public void init(){
        testInit.dummyInit1();
        testInit.dummyInit2();
    }


    @Component
    @RequiredArgsConstructor
    @Transactional
    static class TestInit {
        private final MemberService memberService;
        private final MajorService majorService;

        public void dummyInit1(){
            Major major = createMajor("TestMajor1");
            majorService.joinMajor(major);

            Member member = createMember("2018xxxx", "Name1", 1,
                    major, Position.부원, "010-xxxx-xxxx", StudentStatus.재학,
                    Gender.남, new CountInfo(0, 0, 0));
            memberService.joinMember(member);
        }

        public void dummyInit2(){
            Major major = createMajor("TestMajor2");
            majorService.joinMajor(major);

            Member member = createMember("2019xxxx", "Name2", 2,
                    major, Position.부원, "010-xxxx-xxxx", StudentStatus.휴학,
                    Gender.여, new CountInfo(0, 0, 0));
            memberService.joinMember(member);
        }

        private Member createMember(String studentId, String name, int grade, Major major, Position position, String phoneNumber, StudentStatus studentStatus, Gender gender, CountInfo countInfo) {
            Member member = new Member();
            member.changeMemberInfo(studentId, name, grade, major);
            member.changeMemberInfoDetails(position, phoneNumber, studentStatus, gender, countInfo);
            return member;
        }

        private Major createMajor(String majorName) {
            Major major = new Major();
            major.createBasicMajor(majorName);
            return major;
        }

    }

}
