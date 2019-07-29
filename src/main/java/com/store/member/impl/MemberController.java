package com.store.member.impl;

import com.store.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("v1")
public class MemberController {

    @Autowired
    private IMemberService iMemberService;

    @GetMapping("api/members/{id}")
    ResponseEntity<Member> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(iMemberService.findById(id));
    }

    @GetMapping("api/members")
    ResponseEntity<Collection<Member>> findAll(){
       return ResponseEntity.ok(iMemberService.findAll());
    }

    @PostMapping("api/members")
    ResponseEntity save(
            @Valid @RequestBody MemberDto memberDto,
            UriComponentsBuilder uri) {

        Long id = iMemberService.save(memberDto);

        UriComponents urlCreated = uri.path("v1/api/members/{id}").buildAndExpand(id);
        return ResponseEntity.created(urlCreated.toUri()).build();

    }

    @PatchMapping("api/members/{id}")
    ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody MemberDto memberDto){
        return null;
    }

    @DeleteMapping("api/members/{id}")
    ResponseEntity delete(@PathVariable("id") Long id){
        iMemberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
