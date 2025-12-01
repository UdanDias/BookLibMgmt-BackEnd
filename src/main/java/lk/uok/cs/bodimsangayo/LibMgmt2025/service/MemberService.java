package lk.uok.cs.bodimsangayo.LibMgmt2025.service;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    void addMember(MemberDTO memberDTO);
    void deleteMember(String memberId);
    void updateMember(String memberId,MemberDTO memberDTO);
    MemberDTO getSelectedMember(String memberId);
    List<MemberDTO> getAllMembers();
}
