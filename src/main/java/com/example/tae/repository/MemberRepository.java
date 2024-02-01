package com.example.tae.repository;

import com.example.tae.entity.Account.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.id = :id and m.fromSocial = :social")
    Optional<Member> findByID(@Param("id") String id, @Param("social") boolean social);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.password = :newPassword WHERE m.id = :id")
    void updatePasswordById(@Param("id") String id, @Param("newPassword") String newPassword);
}