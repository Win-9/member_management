package time.management.validation;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import time.management.domain.Member;
import time.management.dto.MemberFormDto;

@Component
public class MemberValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberFormDto memberFormDto = (MemberFormDto) target;
        //ID valid
        if (!StringUtils.hasText(memberFormDto.getStudentID())) {
            errors.rejectValue("studentID", "required", "학번 입력은 필수 입니다." );
        }

        //major valid
        if (!StringUtils.hasText(memberFormDto.getMajor())){
            errors.rejectValue("major", "required", "학과 입력은 필수 입니다.");
        }

        //name valid
        if (!StringUtils.hasText(memberFormDto.getName())){
            errors.rejectValue("name", "required","이름 입력은 필수 입니다.");
        }

        //grade valid
        if (memberFormDto.getGrade() == null){
            errors.rejectValue("grade", "required","이름 입력은 필수 입니다.");
        }

        //phone valid
        if (!StringUtils.hasText(memberFormDto.getPhoneNumber())){
            errors.rejectValue("phoneNumber", "required", "전화번호 입력은 필수 입니다.");
        }
    }
}
