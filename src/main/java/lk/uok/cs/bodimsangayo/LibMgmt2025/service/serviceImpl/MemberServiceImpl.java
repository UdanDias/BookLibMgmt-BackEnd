package lk.uok.cs.bodimsangayo.LibMgmt2025.service.serviceImpl;

import jakarta.transaction.Transactional;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dao.MemberDao;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.MemberDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.MemberEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.MemberNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.MemberService;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.EntityDTOConvert;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao;
    private final EntityDTOConvert entityDTOConvert;
    @Override
    public void addMember(MemberDTO memberDTO)
    {
        memberDTO.setMemberId(UtilData.generateMemberID());
        memberDTO.setMembershipDate(UtilData.generateTodayDate());
        memberDao.save(entityDTOConvert.convertMemberDTOtoMemberEntity(memberDTO));
    }

    @Override
    public void deleteMember(String memberId) {
        Optional<MemberEntity> foundMember=memberDao.findById(memberId);
        if(!foundMember.isPresent()){
            throw new MemberNotFoundException("Member not found");
        }
        memberDao.deleteById(memberId);

    }

    @Override
    public void updateMember(String memberId, MemberDTO memberDTO) {
        Optional<MemberEntity> foundMember=memberDao.findById(memberId);
        if(!foundMember.isPresent()){
            throw new MemberNotFoundException("Member not found");
        }
        foundMember.get().setName((memberDTO.getName()));
        foundMember.get().setEmail(memberDTO.getEmail());
        foundMember.get().setMembershipDate(memberDTO.getMembershipDate());



    }

    @Override
    public MemberDTO getSelectedMember(String memberId) {
        Optional<MemberEntity> foundMember=memberDao.findById(memberId);
        if(!foundMember.isPresent()){
            throw new MemberNotFoundException("Selected Member not found");
        }
        return entityDTOConvert.convertMemberEntitytoMemberDTO(memberDao.getReferenceById(foundMember.get().getMemberId()));
    }

    @Override
    public List<MemberDTO> getAllMembers() {

        return entityDTOConvert.convertMemberEntityListtoMemberDTOList(memberDao.findAll());
    }
}
