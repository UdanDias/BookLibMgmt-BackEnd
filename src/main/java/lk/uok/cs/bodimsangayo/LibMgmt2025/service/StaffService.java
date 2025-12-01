package lk.uok.cs.bodimsangayo.LibMgmt2025.service;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    void addStaff(StaffDTO staffDTO);
    void deleteStaff(String staffId);
    void updateStaff(String staffId,StaffDTO staffDTO);
    StaffDTO getSelectedStaff(String staffId);
    List<StaffDTO> getAllStaff();
}
