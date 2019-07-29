package com.store.member;

import com.store.member.impl.Member;
import com.store.member.impl.MemberDto;

import java.util.Collection;

public interface IMemberDao {
    Collection<Member> findAll();
    Member findById(Long id);
    Long save(MemberDto memberDto);
    void update(Long id, MemberDto memberDto);
    void delete(Long id);
}
