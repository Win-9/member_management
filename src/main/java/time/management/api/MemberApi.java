package time.management.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import time.management.apidto.MemberListApi;
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
    public HashMap<String, Object> membersListApi() {
            //List Dto 변환

        try {
            List<MemberListApi> result = memberService.findAll().stream()
                    .map(m -> new MemberListApi(m))
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

    //학번find
    @GetMapping("/{id}")
    public HashMap<String, Object> memberSoloApi(@PathVariable String id){
        try {
            Member findMember = memberService.findByMemberId(id);
            MemberListApi members = new MemberListApi(findMember);
            resultMap.put("successRequest", "true");
            resultMap.put("member", members);

        }catch (Exception e){
            resultMap.put("successRequest", "false");
            resultMap.put("member", null);
        }

        return resultMap;

    }
}