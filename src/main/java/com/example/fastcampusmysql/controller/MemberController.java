package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {
    final private MemberWriteService memberWriteService;

    final private MemberReadService memberReadService;

    @PostMapping
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        var member = memberWriteService.register(command);

        return memberReadService.toDto(member);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PostMapping("/{id}/name")
    public MemberDto changeNickName(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickName(id, nickname);
        return memberReadService.getMember(id);
    }
}
