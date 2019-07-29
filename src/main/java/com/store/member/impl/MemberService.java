package com.store.member.impl;

import com.store.member.IMemberDao;
import com.store.member.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private IMemberDao iMemberDao;

    @Override
    public Collection<Member> findAll() {
        return iMemberDao.findAll();
    }

    @Override
    public Member findById(Long id) {
        return iMemberDao.findById(id);
    }

    @Override
    public Long save(MemberDto memberDto) {
        return iMemberDao.save(memberDto);
    }

    @Override
    public void update(Long id, MemberDto memberDto) {
        iMemberDao.update(id, memberDto);
    }

    @Override
    public void delete(Long id) {
        iMemberDao.delete(id);
    }
}
