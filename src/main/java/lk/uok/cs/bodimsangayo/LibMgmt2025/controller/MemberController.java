package lk.uok.cs.bodimsangayo.LibMgmt2025.controller;

import com.sun.source.tree.ReturnTree;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.MemberDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.MemberNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor

public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/health")
//    public String health(){
//        return "health check is runnning";
//    }
    @PostMapping(consumes = APPLICATION_JSON_VALUE,produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMember(@RequestBody MemberDTO memberDTO){
        if(memberDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            memberService.addMember(memberDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping()
    public ResponseEntity<Void>deleteMember(@RequestParam("memberId") String memberId){

        try{
            memberService.deleteMember(memberId);
            return ResponseEntity.noContent().build();
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping( consumes = APPLICATION_JSON_VALUE,produces=APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>updateMember(@RequestParam("memberId") String memberId,@RequestBody  MemberDTO memberDTO){
        if(memberDTO==null||memberId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            memberService.updateMember(memberId,memberDTO);
            return ResponseEntity.noContent().build();
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping()
    public ResponseEntity<MemberDTO> getSelectedMember(@RequestParam("memberId") String memberId ){
        if(memberId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            return ResponseEntity.ok(memberService.getSelectedMember(memberId));
        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getallmembers")
    public ResponseEntity<List<MemberDTO>>getAllMembers(){
        try {
            return ResponseEntity.ok(memberService.getAllMembers());

        }catch (MemberNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
