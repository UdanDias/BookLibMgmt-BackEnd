package lk.uok.cs.bodimsangayo.LibMgmt2025.controller;

import lk.uok.cs.bodimsangayo.LibMgmt2025.LibMgmt2025Application;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.BookDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.BookNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;
//    @Autowired
//    public BookController(BookService bookService){
//        this.bookService = bookService;
//    }



//    @GetMapping("/health")
//    public String healthCheck(){
//        return "Health check running";
//    }

    @PostMapping(consumes= APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>addBook(@RequestBody BookDTO bookDTO){
        if(bookDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookService.addBook(bookDTO);
        System.out.println(bookDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping()
    public ResponseEntity<Void>deleteBook(@RequestParam("bookId") String bookId ){
        if(bookId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            bookService.deleteBook(bookId);
            return ResponseEntity.noContent().build();
        }catch(BookNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PatchMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>updateBook(@RequestParam("bookId") String bookId, @RequestBody BookDTO bookDTO ){
        if(bookId==null||bookDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            bookService.updateBook(bookId,bookDTO);
            return ResponseEntity.noContent().build();
        }catch(BookNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping()
    public ResponseEntity<BookDTO>getSelectedBook(@RequestParam("bookId") String bookId){
        if(bookId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.ok(bookService.getSelectedBook(bookId));
        }catch(BookNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getallbooks")
    public ResponseEntity<List<BookDTO>>getAllBooks(){

        try{
            return ResponseEntity.ok(bookService.getAllBooks());
        }catch(BookNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
