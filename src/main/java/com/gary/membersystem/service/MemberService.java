package com.gary.membersystem.service;

import com.gary.membersystem.dto.MemberRequest;
import com.gary.membersystem.dto.UpdateMemberRequest;
import com.gary.membersystem.entity.Member;
import com.gary.membersystem.exception.DuplicateEmailException;
import com.gary.membersystem.exception.MemberNotFoundException;
import com.gary.membersystem.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //Constructor Injection 方式
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(MemberRequest request) {

        // 先依 Email 查詢是否已有會員存在，findByEmail 回傳 Optional<Member>
        memberRepository
                .findByEmail(request.getEmail())
                // 若 Optional 有值（代表 Email 已被使用），就拋出例外中斷後續流程，類似 C# 的 if (result != null) throw
                .ifPresent(member -> {
                    throw new DuplicateEmailException(
                            "Email 已存在"
                    );
                });

        Member member = new Member();

        member.setName(request.getName());

        member.setEmail(request.getEmail());

        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {

        // 用 Optional 包住 List，filter 判斷非空，寫法上呼應其他方法的 orElseThrow 風格
        return Optional.of(memberRepository.findAll())
                .filter(members -> !members.isEmpty())
                .orElseThrow(() ->
                        new MemberNotFoundException("沒有會員"));
    }

    public Member getMemberById(Long id) {

        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("會員不存在"));
    }

    public Member updateMember(
            Long id,
            UpdateMemberRequest request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("會員不存在"));

        member.setName(request.getName());
        member.setEmail(request.getEmail());

        return memberRepository.save(member);
    }

    public void deleteMember(Long id) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new MemberNotFoundException("會員不存在"));

        memberRepository.delete(member);
    }
}