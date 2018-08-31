package com.pactera.customercenter.sevice.impl;




import com.pactera.customercenter.domain.Staff;
import com.pactera.customercenter.mapper.StaffMapper;
import com.pactera.customercenter.sevice.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    //根据 推荐人CID获取他推荐人的注册用户列表
    public List<Staff> getStaffInfo(String cid){
        return staffMapper.findStaffInfo(cid);
    }

    public void insert(Staff staff){

        staffMapper.addStaffInfo(staff);
    }

    public Staff  getStaffByCid(String cid){
        return  staffMapper.findStaffByCId(cid);
    }

    @Override
    public Staff findStaffByTelNo(String telno) {
        return staffMapper.findStaffByTelNo(telno);
    }
    @Override
    public void updateStaff(Staff staff){
         staffMapper.updateStaff(staff);
    }
    @Override
    public Staff findStaffByRefereeID(String refereeIDelno){
        return  staffMapper.findStaffByRefereeID(refereeIDelno);
    }
    @Override
    public void saveStaffInfo(Staff staff){
         staffMapper.saveStaffInfo(staff);
    }
    @Override
    public void updateStaffPass(Staff staff){
        staffMapper.updateStaffPass(staff);
    }
    @Override
    public void updateStaffHead (Staff staff){
        staffMapper.updateStaffHead(staff);
    };

}
