package lk.uok.cs.bodimsangayo.LibMgmt2025.util;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.BookDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.LendingDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.MemberDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.StaffDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.BookEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.LendingEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.MemberEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.StaffEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityDTOConvert {
    private final ModelMapper modelMapper;

    public BookEntity converBookDTOtoBookEntity(BookDTO bookDTO){
        return modelMapper.map(bookDTO,BookEntity.class);
    }
    public BookDTO convertBookENtitytoBookDTO(BookEntity bookEntity){
        return modelMapper.map(bookEntity,BookDTO.class);
    }
    public List<BookDTO> convertBookEntityListtoBookDTOLIST(List<BookEntity> bookEntityList){
        return modelMapper.map(bookEntityList,new TypeToken<List<BookDTO>>(){}.getType());
    }

    public StaffEntity convertStaffDTOtoStaffEntity(StaffDTO staffDTO){
        return modelMapper.map(staffDTO,StaffEntity.class);
    }
    public StaffDTO convertStaffEntitytoStaffDTO(StaffEntity staffEntity){
        return modelMapper.map(staffEntity,StaffDTO.class);
    }
    public List<StaffDTO> convertStaffEntityListtoStaffDTOList(List<StaffEntity> staffEntityList){
        return modelMapper.map(staffEntityList,new TypeToken<List<StaffDTO>>(){}.getType());
    }

    public MemberEntity convertMemberDTOtoMemberEntity(MemberDTO memberDTO){
        return modelMapper.map(memberDTO,MemberEntity.class);
    }
    public MemberDTO convertMemberEntitytoMemberDTO(MemberEntity memberEntity){
        return modelMapper.map(memberEntity,MemberDTO.class);
    }
    public List<MemberDTO> convertMemberEntityListtoMemberDTOList(List<MemberEntity>memberEntityList){
        return modelMapper.map(memberEntityList,new TypeToken<List<MemberDTO>>(){}.getType());
    }

   public LendingEntity convertLendingDTOtoLendingEntity(LendingDTO lendingDTO){
        return modelMapper.map(lendingDTO,LendingEntity.class);
   }
   public LendingDTO convertLendingDTOtoLendingDTO(LendingEntity lendingEntity){
        return modelMapper.map(lendingEntity,LendingDTO.class);
   }
   public List<LendingDTO> convertLendingEntityListtoLendingDTOList(List<LendingEntity> lendingEntityList){
        return modelMapper.map(lendingEntityList,new TypeToken<List<LendingDTO>>(){}.getType());
   }




}
