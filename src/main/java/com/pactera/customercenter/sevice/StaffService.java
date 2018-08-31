package com.pactera.customercenter.sevice;



import com.pactera.customercenter.domain.Staff;

import java.util.List;

public interface StaffService {

    public List<Staff> getStaffInfo(String cid);

    public void insert(Staff staff);

    public Staff   getStaffByCid(String cid);
    public Staff   findStaffByTelNo(String telno);

    public void  updateStaff(Staff staff);
    public Staff findStaffByRefereeID(String refereeIDelno);
    public void saveStaffInfo(Staff staff);
    public void updateStaffPass(Staff staff);
    public void updateStaffHead (Staff staff);
}
