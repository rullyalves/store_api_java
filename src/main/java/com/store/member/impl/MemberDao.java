package com.store.member.impl;

import com.store.member.IMemberDao;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class MemberDao implements IMemberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Collection<Member> findAll() {

        try {
            String sql = "SELECT member_id, member_user_id, member_group_id " +
                    "FROM members ";

            RowMapper<Member> rowMapper = JdbcTemplateMapperFactory
                    .newInstance()
                    .newRowMapper(Member.class);

         Collection<Member> members = jdbcTemplate.query(sql,rowMapper);
           return members;
        } catch(DataAccessException e) {
            throw e;
        }
    }

    @Override
    public Member findById(Long id) {
        try {
         String sql = "SELECT member_id,member_user_id, member_group_id FROM members WHERE member_id = ?";
         RowMapper<Member> memberRowMapper = JdbcTemplateMapperFactory
                 .newInstance()
                 .newRowMapper(Member.class);
         Member member = jdbcTemplate.queryForObject(sql, memberRowMapper, id);
         return member;
        } catch (DataAccessException e){
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM members WHERE member_id = ?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e){
            throw e;
        }
    }

    @Override
    public void update(Long id, MemberDto memberDto) {

    }

    @Override
    public Long save(MemberDto memberDto) {
        final KeyHolder generatedKey = new GeneratedKeyHolder();
        try {
            String sql = "INSERT INTO members(member_user_id, member_group_id) VALUES (?,?) ";

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, memberDto.getUserId());
                ps.setLong(2, memberDto.getGroupId());

                return ps;
            }, generatedKey);

            return (long) generatedKey.getKey();

        }catch (DataAccessException e) {
            throw e;
        }
    }
}
