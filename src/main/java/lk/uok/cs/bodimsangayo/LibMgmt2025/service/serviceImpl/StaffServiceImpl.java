package lk.uok.cs.bodimsangayo.LibMgmt2025.service.serviceImpl;

import jakarta.transaction.Transactional;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dao.StaffDao;
import lk.uok.cs.bodimsangayo.LibMgmt2025.dto.StaffDTO;
import lk.uok.cs.bodimsangayo.LibMgmt2025.entities.StaffEntity;
import lk.uok.cs.bodimsangayo.LibMgmt2025.exceptions.StaffNotFoundException;
import lk.uok.cs.bodimsangayo.LibMgmt2025.service.StaffService;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.EntityDTOConvert;
import lk.uok.cs.bodimsangayo.LibMgmt2025.util.UtilData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffDao staffDao;
    private final EntityDTOConvert entityDTOConvert;
    @Override
    public void addStaff(StaffDTO staffDTO) {
        staffDTO.setStaffId(UtilData.generateStaffId());
        staffDTO.setJoinDate(UtilData.generateTodayDate());
        staffDTO.setLastUpdateDate(UtilData.generateTodayDate());
        staffDTO.setLastUpdateTime(UtilData.generateCurrentTime());
        staffDao.save(entityDTOConvert.convertStaffDTOtoStaffEntity(staffDTO));
    }

    @Override
    public void deleteStaff(String staffId) {
        Optional<StaffEntity> foundStaff=staffDao.findById(staffId);
        if(!foundStaff.isPresent()){
            throw new StaffNotFoundException("staff not found");
        }
        staffDao.deleteById(foundStaff.get().getStaffId());

    }

    @Override
    public void updateStaff(String staffId, StaffDTO staffDTO) {
        Optional<StaffEntity> foundStaff=staffDao.findById(staffId);
        if(!foundStaff.isPresent()){
            throw new StaffNotFoundException("staff not found");
        }
        foundStaff.get().setFirstName(staffDTO.getFirstName());
        foundStaff.get().setLastName(staffDTO.getLastName());
        foundStaff.get().setEmail(staffDTO.getEmail());
        foundStaff.get().setPhone(staffDTO.getPhone());
        foundStaff.get().setRole(staffDTO.getRole());
        foundStaff.get().setJoinDate(staffDTO.getJoinDate());
        foundStaff.get().setLastUpdateDate(UtilData.generateTodayDate());
        foundStaff.get().setLastUpdateTime(UtilData.generateCurrentTime());

    }

    @Override
    public StaffDTO getSelectedStaff(String staffId) {
        Optional<StaffEntity> foundStaff=staffDao.findById(staffId);
        if(!foundStaff.isPresent()){
            throw new StaffNotFoundException("staff not found");
        }
        return entityDTOConvert.convertStaffEntitytoStaffDTO(staffDao.getReferenceById(foundStaff.get().getStaffId()));
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        return entityDTOConvert.convertStaffEntityListtoStaffDTOList(staffDao.findAll());
    }
}
