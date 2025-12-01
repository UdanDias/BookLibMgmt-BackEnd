package lk.uok.cs.bodimsangayo.LibMgmt2025.util;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.LendingDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.BookEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.LendingEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LendingMapping {
    public static LendingDTO toLendingDTO(LendingEntity lendingEntity) {
        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setLendingId(lendingEntity.getLendingId());
        lendingDTO.setBook(lendingEntity.getBook().getBookId());
        lendingDTO.setMember(lendingEntity.getMember().getMemberId());
        lendingDTO.setLendingDate(lendingEntity.getLendingDate());
        lendingDTO.setReturnDate(lendingEntity.getReturnDate());
        lendingDTO.setIsActiveLending(lendingEntity.getIsActiveLending());
        lendingDTO.setOverdueDays(lendingEntity.getOverdueDays());
        lendingDTO.setFineAmount(lendingEntity.getFineAmount());
        return lendingDTO;
    }

    public static LendingEntity toLendingEntity(LendingDTO lendingDTO, BookEntity bookEntity, MemberEntity memberEntity) {
        LendingEntity lendingEntity = new LendingEntity();
        lendingEntity.setLendingId(lendingDTO.getLendingId());
        lendingEntity.setBook(bookEntity);
        lendingEntity.setMember(memberEntity);
        lendingEntity.setLendingDate(lendingDTO.getLendingDate());
        lendingEntity.setReturnDate(lendingDTO.getReturnDate());
        lendingEntity.setIsActiveLending(lendingDTO.getIsActiveLending());
        lendingEntity.setOverdueDays(lendingDTO.getOverdueDays());
        lendingEntity.setFineAmount(lendingDTO.getFineAmount());
        return lendingEntity;
    }

//    public List<LendingDTO>getLendingDTOList(List<LendingEntity>lendingEntityList){
//        return lendingEntityList.stream().map(lendingEntity->{
//            LendingDTO lendingDTO=new LendingDTO();
//        lendingDTO.setLendingId(lendingEntity.getLendingId());
//        if (lendingEntity.getBook()!=null){
//        lendingDTO.setBook(lendingEntity.getBook().getBookId());
//        }
//        if (lendingEntity.getMember()!=null){
//        lendingDTO.setMember(lendingEntity.getMember().getMemberId());
//        }
//        lendingDTO.setLendingDate(lendingEntity.getLendingDate());
//        lendingDTO.setReturnDate(lendingEntity.getReturnDate());
//        lendingDTO.setIsActiveLending(lendingEntity.getIsActiveLending());
//        lendingDTO.setOverdueDays(lendingEntity.getOverdueDays());
//        lendingDTO.setFineAmount(lendingEntity.getFineAmount());
//        return lendingDTO;
public List<LendingDTO> getLendingDTOList(List<LendingEntity> lendingEntityList) {
    return lendingEntityList.stream().map(lendingEntity -> {
        LendingDTO lendingDTO = new LendingDTO();
        lendingDTO.setLendingId(lendingEntity.getLendingId());

        if (lendingEntity.getBook() != null) {
            lendingDTO.setBook(lendingEntity.getBook().getBookId());
        }

        if (lendingEntity.getMember() != null) {
            lendingDTO.setMember(lendingEntity.getMember().getMemberId());
        }

        lendingDTO.setLendingDate(lendingEntity.getLendingDate());
        lendingDTO.setReturnDate(lendingEntity.getReturnDate());
        lendingDTO.setIsActiveLending(lendingEntity.getIsActiveLending());
        lendingDTO.setOverdueDays(lendingEntity.getOverdueDays());
        lendingDTO.setFineAmount(lendingEntity.getFineAmount());

        return lendingDTO;
    }).toList();
}

}

