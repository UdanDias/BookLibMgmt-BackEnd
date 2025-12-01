package lk.uok.cs.bodimsangayo.LibMgmt2025.controller;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.BookDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.Role;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.StaffDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.StaffNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/staff")
@RequiredArgsConstructor

public class StaffController {

    private final StaffService staffService;

//    @GetMapping("health")
//    public String healthCheck() {
//        return "health check is running";
//    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStaff(@RequestBody StaffDTO staffDTO){
        if(staffDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            staffService.addStaff(staffDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    @DeleteMapping()
    public ResponseEntity<Void> deleteStaff(@RequestParam("staffId") String staffId){
        if(staffId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            staffService.deleteStaff(staffId);
            return ResponseEntity.noContent().build();
        }catch(StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PatchMapping( consumes = APPLICATION_JSON_VALUE,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void>updateStaff(@RequestParam("staffId") String staffId, @RequestBody StaffDTO staffDTO){
        if(staffId==null||staffDTO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            staffService.updateStaff(staffId, staffDTO);
            return ResponseEntity.ok().build();
        }catch(StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping()
    public ResponseEntity<StaffDTO>getSelectedStaff(@RequestParam("staffId") String staffId){
        if(staffId==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            return ResponseEntity.ok(staffService.getSelectedStaff(staffId));

        }catch(StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("getallstaff")
    public ResponseEntity<List<StaffDTO>> getAllStaff(){

        try{
            return ResponseEntity.ok(staffService.getAllStaff());
        }catch(StaffNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
