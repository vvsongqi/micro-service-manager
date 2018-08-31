package com.pactera.customercenter.mapper;


import com.pactera.customercenter.domain.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface StaffMapper {

    public List<Staff> findStaffInfo(String cid);
    public int addStaffInfo(Staff staff);
    public int delStaffInfo(String id);
    public Staff  findStaffByCId(String cid);
    public int updateStaff(Staff staff);
    public Staff  findStaffByTelNo(String telno);
    public Staff findStaffByRefereeID(String refereeIDelno);
    public void saveStaffInfo(Staff staff);
    public void updateStaffPass(Staff staff);
    public void updateStaffHead (Staff staff);
}
