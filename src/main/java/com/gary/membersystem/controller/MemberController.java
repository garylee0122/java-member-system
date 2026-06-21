package com.gary.membersystem.controller;

import com.gary.membersystem.dto.MemberRequest;
import com.gary.membersystem.dto.UpdateMemberRequest;
import com.gary.membersystem.entity.Member;
import com.gary.membersystem.service.MemberService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController                 // 宣告此類別為 REST 控制器，回傳值自動序列化成 JSON（= @Controller + @ResponseBody）
@RequestMapping("/members")     // 設定整個 Controller 的基底路徑，底下所有路由都以 /members 開頭
public class MemberController {

    private final MemberService memberService;

    //Constructor Injection 方式
    public MemberController(MemberService memberService) {  // 建構子注入：Spring 自動將 MemberService Bean 注入，無需 @Autowired
        this.memberService = memberService;
    }

    @PostMapping                                // 對應 HTTP POST /members，新增一筆會員
    public Member createMember(
            @Valid                                  // 觸發 MemberRequest 欄位上的驗證註解（如 @NotBlank、@Email），不通過時拋出 MethodArgumentNotValidException
            @RequestBody MemberRequest request) {   // 將前端送來的 JSON 請求主體反序列化成 MemberRequest 物件

        return memberService.createMember(request);
    }

    @GetMapping                             // 對應 HTTP GET /members，取得全部會員清單
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")                    // 對應 HTTP GET /members/{id}，{id} 為 URL 動態路徑變數
    public Member getMemberById(
            @PathVariable Long id) {        // 將 URL 中的 {id} 取出並綁定到此參數

        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")                    // 對應 HTTP PUT /members/{id}，更新指定會員資料
    public Member updateMember(
            @PathVariable Long id,              // 將 URL 中的 {id} 取出並綁定到此參數
            @Valid                                  // 觸發 UpdateMemberRequest 欄位上的驗證註解（如 @NotBlank、@Email），不通過時拋出 MethodArgumentNotValidException
            @RequestBody UpdateMemberRequest request) {   // 將前端送來的 JSON 請求主體反序列化成 UpdateMemberRequest 物件

        return memberService.updateMember(id, request);
    }

    @DeleteMapping("/{id}")                 // 對應 HTTP DELETE /members/{id}，刪除指定會員
    public String deleteMember(
            @PathVariable Long id) {        // 將 URL 中的 {id} 取出並綁定到此參數

        memberService.deleteMember(id);

        return "Member deleted";
    }
}