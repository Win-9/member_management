package time.management.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import time.management.apidto.MemberAttendDto;
import time.management.apidto.MemberAttendMoreThanOnceDto;
import time.management.apidto.MemberInfoApiDto;
import time.management.domain.Member;
import time.management.service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberApi {

    private final MemberService memberService;
    private HashMap<String, Object> resultMap = new HashMap<>();

    //memberListApi
    @GetMapping("/memberList")
    public HashMap<String, Object> membersListApi(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                  @RequestParam(value = "limit", defaultValue = "10") int limit) {
            //List Dto 변환

        try {
            List<MemberInfoApiDto> result = memberService.findAllWithPaing(offset, limit).stream()
                    .map(m -> new MemberInfoApiDto(m))
                    .collect(Collectors.toList());

            resultMap.put("successRequest", "true");
            resultMap.put("totalSize", memberService.findAll().size());
            resultMap.put("memberList", result);
        } catch (Exception e) {
            resultMap.put("successRequest", "fail");
            resultMap.put("totalSize", -9999);
            resultMap.put("memberList", null);
        }

        return resultMap;
    }

    //학번Api
    @GetMapping("/{id}")
    public HashMap<String, Object> memberSoloApi(@PathVariable String id){
        try {
            Member findMember = memberService.findByMemberId(id);
            MemberInfoApiDto members = new MemberInfoApiDto(findMember);
            resultMap.put("successRequest", "true");
            resultMap.put("member", members);

        }catch (Exception e){
            resultMap.put("successRequest", "false");
            resultMap.put("member", null);
        }

        return resultMap;
    }

    //출석api
    @GetMapping("/attend/{id}")
    public HashMap<String, Object> memberSoloAttendApi(@PathVariable String id){
        try {
            Member findMember = memberService.findByMemberId(id);
            MemberAttendDto memberAttendDto = new MemberAttendDto(findMember);
            resultMap.put("successRequest", "true");
            resultMap.put("member", memberAttendDto);

        }catch (Exception e){
            resultMap.put("successRequest", "false");
            resultMap.put("member", null);
        }

        return resultMap;
    }

    @GetMapping("/attend/attendMoreThanOnceMemberList")
    public HashMap<String, Object> memberAttendMoreThanOnce(){
        try {
            List<MemberAttendMoreThanOnceDto> memberDto = memberService.findAll().stream()
                    .filter(m -> m.getCountInfo().getAttendanceCount() != 0)
                    .map(m -> new MemberAttendMoreThanOnceDto(m))
                    .collect(Collectors.toList());
            resultMap.put("successRequest", "true");
            resultMap.put("size", memberDto.size());
            resultMap.put("member", memberDto);

        }catch (Exception e){
            resultMap.put("successRequest", "false");
            resultMap.put("size", -999);
            resultMap.put("member", null);
        }

        return resultMap;
    }
}
