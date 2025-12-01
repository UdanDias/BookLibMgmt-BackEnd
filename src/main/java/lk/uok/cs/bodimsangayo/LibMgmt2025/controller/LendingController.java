package lk.uok.cs.bodimsangayo.LibMgmt2025.controller;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.LendingDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.MemberDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.BookNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.DataPersistException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.LendingDataNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.MemberNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.LendingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/lending")
@RequiredArgsConstructor
public class LendingController {

    private final LendingService lendingService;

//    @GetMapping("health")
//    public String healthCheck(){
//        return "health check is running";
//    }
    @PostMapping(consumes = APPLICATION_JSON_VALUE , produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addLending(@RequestBody LendingDTO lendingDTO){
        try{
            lendingService.addLending(lendingDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(BookNotFoundException | MemberNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(DataPersistException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping
    public ResponseEntity<Void> deleteLending(@RequestParam("lendingId") String lendingId){
        if(lendingId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            lendingService.deleteLending(lendingId);
            return ResponseEntity.noContent().build();
        }catch(LendingDataNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PatchMapping(consumes = APPLICATION_JSON_VALUE , produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> handOverBook(@RequestParam("lendingId") String lendingId){
        try{
            lendingService.handoverBook(lendingId);
            return ResponseEntity.noContent().build();
        }catch(LendingDataNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping()
    public ResponseEntity<LendingDTO>getSelectedLending(@RequestParam("lendingId") String lendingId){
        if(lendingId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            return ResponseEntity.ok(lendingService.getSelectedLending(lendingId));
        }catch(LendingDataNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getalllendings")
    public ResponseEntity<List<LendingDTO>> getAllLendings(){
        return ResponseEntity.ok(lendingService.getAllLending());
    }
}
