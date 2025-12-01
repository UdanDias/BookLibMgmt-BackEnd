package lk.uok.cs.bodimsangayo.LibMgmt2025.dao;

import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao extends JpaRepository<MemberEntity, String> {
}
