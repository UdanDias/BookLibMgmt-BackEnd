package lk.uok.cs.bodimsangayo.LibMgmt2025.service;

import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.LendingDTO;

import java.util.List;

public interface LendingService {
    void addLending(LendingDTO lendingDTO);
    void deleteLending(String lendingId);
    void handoverBook(String lendingId );
    LendingDTO getSelectedLending(String lendingId);
    List<LendingDTO> getAllLending();
}
