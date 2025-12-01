package lk.uok.cs.bodimsangayo.LibMgmt2025.service.serviceImpl;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dao.BookDao;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dao.LendingDao;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dao.MemberDao;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.LendingDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.BookEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.LendingEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.MemberEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.BookNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.EnoughBookNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.LendingDataNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.MemberNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.LendingService;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.EntityDTOConvert;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.LendingMapping;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.UtilData;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LendingServiceImpl implements LendingService {
    private final LendingDao lendingDao;
    private final MemberDao memberDao;
    private final BookDao bookDao;
    private final LendingMapping lendingMapping;
    private final EntityDTOConvert entityDTOConvert;

    @Value("${perDayFine}")
    private Double perDayAmount;
    @Override
    public void addLending(LendingDTO lendingDTO) {

        String bookId=lendingDTO.getBook();
        String memberId=lendingDTO.getMember();
        BookEntity book = bookDao.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not found"));
        MemberEntity member = memberDao.findById(memberId).orElseThrow(()-> new MemberNotFoundException("Member Not found"));

        if(bookDao.availQty(bookId)>0){
            if(bookDao.deductBasedOnLending(bookId)>0){
                lendingDTO.setLendingId(UtilData.generateLendingId());
                lendingDTO.setLendingDate(UtilData.generateTodayDate());
                lendingDTO.setReturnDate(UtilData.generateBookReturnDate());
                lendingDTO.setIsActiveLending(true);
                lendingDTO.setFineAmount(0.0);
                lendingDTO.setOverdueDays(0L);
                lendingDao.save(LendingMapping.toLendingEntity(lendingDTO,book,member));

            }else{
                throw new PersistenceException("cannot update book data with 0 available quantity");
            }

        }else {
            throw new EnoughBookNotFoundException("not enough books found");
        }

    }

    @Override
    public void deleteLending(String lendingId) {
        var foundLending=lendingDao.findById(lendingId).orElseThrow(()->new LendingDataNotFoundException("Lending Data not found"));
        lendingDao.deleteById(lendingId);
        if(foundLending.getIsActiveLending()==true){
            bookDao.addBookBasedBookHandover(foundLending.getBook().getBookId());
        }

    }

    @Override
    public void handoverBook(String lendingId) {
        LendingEntity foundLending=lendingDao.findById(lendingId).orElseThrow(()-> new LendingDataNotFoundException("Lending data not found"));
        var returnDate=foundLending.getReturnDate();
        var overDue=calcOverDue(returnDate);
        var fineAmount=calcFine(overDue);

        foundLending.setOverdueDays(overDue);
        foundLending.setFineAmount(fineAmount);
        foundLending.setIsActiveLending(false);
        bookDao.addBookBasedBookHandover(foundLending.getBook().getBookId());
    }

    @Override
    public LendingDTO getSelectedLending(String lendingId) {
        var foundLending=lendingDao.findById(lendingId).orElseThrow(()-> new LendingDataNotFoundException("Lending Data not found"));
        return LendingMapping.toLendingDTO(foundLending);

    }

    @Override
    public List<LendingDTO> getAllLending() {
        return lendingMapping.getLendingDTOList(lendingDao.findAll());
    }

    private Long calcOverDue(LocalDate returnDate) {
        LocalDate today=UtilData.generateTodayDate();
//        LocalDate returnDate=UtilData.generateBookReturnDateCalc();

        if(returnDate.isBefore(today)){
            return ChronoUnit.DAYS.between(returnDate,today);
        }
        return 0L;
    }
    private Double calcFine(Long dateCount){
        return dateCount*perDayAmount;
    }
}
