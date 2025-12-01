package lk.uok.cs.bodimsangayo.LibMgmt2025.service.serviceImpl;

import jakarta.transaction.Transactional;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dao.BookDao;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.BookDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.BookEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.BookNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.BookService;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.EntityDTOConvert;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final EntityDTOConvert entityDTOConvert;
    @Override
    public void addBook(BookDTO bookDTO) {

        bookDTO.setBookId(UtilData.generateBookId());
        bookDTO.setLastUpdateDate(UtilData.generateTodayDate());
        bookDTO.setLastUpdateTime(UtilData.generateCurrentTime());
        bookDao.save(entityDTOConvert.converBookDTOtoBookEntity(bookDTO));
    }

    @Override
    public void deleteBook(String bookId) {
        Optional<BookEntity> foundBook=bookDao.findById(bookId);
        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }
        bookDao.deleteById(bookId);
    }

    @Override
    public void updateBook(String bookId, BookDTO bookDTO) {
        Optional<BookEntity>foundBook=bookDao.findById(bookId);
        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Book not found");
        }
        foundBook.get().setBookName(bookDTO.getBookName());
        foundBook.get().setAuthor(bookDTO.getAuthor());
        foundBook.get().setEdition(bookDTO.getEdition());
        foundBook.get().setIsbn(bookDTO.getIsbn());
        foundBook.get().setPublisher(bookDTO.getPublisher());
        foundBook.get().setTotalQty(bookDTO.getTotalQty());
        foundBook.get().setAvailableQty(bookDTO.getAvailableQty());
        foundBook.get().setPrice(bookDTO.getPrice());
        foundBook.get().setLastUpdateDate(UtilData.generateTodayDate());
        foundBook.get().setLastUpdateTime(UtilData.generateCurrentTime());


    }

    @Override
    public BookDTO getSelectedBook(String bookId) {
        Optional<BookEntity> foundBook=bookDao.findById(bookId);
        if(!foundBook.isPresent()){
            throw new BookNotFoundException("Selected Book not found");
        }
        return entityDTOConvert.convertBookENtitytoBookDTO(bookDao.getReferenceById(foundBook.get().getBookId()));

    }

    @Override
    public List<BookDTO> getAllBooks() {
        return entityDTOConvert.convertBookEntityListtoBookDTOLIST(bookDao.findAll());
    }
}
