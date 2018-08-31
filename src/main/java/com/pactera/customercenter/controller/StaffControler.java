package com.pactera.customercenter.controller;
import com.alibaba.fastjson.JSONObject;
import com.pactera.customercenter.domain.DataIsSucces;
import com.pactera.customercenter.domain.Staff;
import com.pactera.customercenter.sevice.StaffService;
import com.pactera.customercenter.util.bean.BaseReturnBean;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@EnableWebMvc
@RestController
@RequestMapping("/staff")
public class StaffControler {
    @Value("${web.upload-path}")
    private String webUploadPath;
    protected Logger logger= LoggerFactory.getLogger(StaffControler.class);

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/getStaffInfoListByRefereeID" ,method = RequestMethod.GET)
    public BaseReturnBean getStaffInfoList( String cid) throws Exception{
        BaseReturnBean  baseReturnBean  = new BaseReturnBean();
        System.out.println(" getStaffInfo()......");
        List staff = staffService.getStaffInfo(cid);
        System.out.println(staff.size());
//        if(1==1){
//            throw  new BaseExcetion("1000","测试自定义异常");//可以获取到JSON数据，当前抛出的自定义异常信息
//        }
        String  msg  = "查询员工列表信息成功";
        baseReturnBean.setMeta("0000",msg);
//        System.out.println(msg);
        baseReturnBean.setList(staff);
        return baseReturnBean;
    }


    @RequestMapping(value = "/getStaffByCID" ,method = RequestMethod.GET)
    public BaseReturnBean getStaff (String cid) throws Exception{
        BaseReturnBean  baseReturnBean  = new BaseReturnBean();

        Staff staff = staffService.getStaffByCid(cid);

        String  msg  = "查询员工信息成功";
        baseReturnBean.setMeta("0000",msg);
//        System.out.println(msg);
        baseReturnBean.setData(staff);
        return baseReturnBean;
    }

    @RequestMapping(value = "/updateStaff" ,method = RequestMethod.GET)
    public BaseReturnBean updateStaff (String cid,String name,String sex) throws Exception{
        BaseReturnBean  baseReturnBean  = new BaseReturnBean();
       Staff staff = new Staff();
       staff.setCid(cid);
       staff.setName(name);
       staff.setSex(sex);
        try {
            staffService.updateStaff(staff);
        }catch (Exception e){
            String  msg  = "修改员工信息失败";
            baseReturnBean.setMeta("0001",msg);
            DataIsSucces  data = new DataIsSucces();
            data.setIsSuccess("0002");
            data.setDesc("更新失败");
            baseReturnBean.setData(data);
        }
        String  msg  = "修改员工信息成功";
        baseReturnBean.setMeta("0000",msg);
//        System.out.println(msg);
        DataIsSucces  data = new DataIsSucces();
        data.setIsSuccess("0001");
        data.setDesc("更新成功");
        baseReturnBean.setData(data);
        return baseReturnBean;
    }



    @RequestMapping(value = "/getStaffInfos", method = RequestMethod.GET)
    public ModelAndView getStaffInfos(Model model) {
        System.out.println(" getStaffInfos()......");
        String  cid="1";
        List<Staff> staffs = staffService.getStaffInfo(cid);
        //model.addAttribute("staffs",staffs);
        ModelAndView modelAndView  = new ModelAndView("/staff");
       // modelAndView.setViewName("staff");
        modelAndView.addObject("staffs",staffs);
        return modelAndView;
    }

//    @RequestMapping("/getStaffInfos")
//      public String  getStaffInfos(Map<String, Object> model){
//        System.out.println("hahhhafdkafkdajfafdasfdasdf");
//              return "staff";
//     }



    @RequestMapping("/addStaffInfo")
    @Transactional    //事务控制注解
     public Staff addStaffInfo() {
        Staff staff = new Staff();
        staff.setCid("14");
        staff.setName("rxc14");
        staff.setAge(14);
        staffService.insert(staff);

//        int i = 1/0;//测试事务
//        Staff staff1 = new Staff();
//        staff1.setId("13");
//        staff1.setName("rxc13");
//        staff1.setAge(13);
//        staffService.insert(staff1);


        return staff;
    }
    //登陆接口
    @RequestMapping(value="/login",method = RequestMethod.GET)
    @ResponseBody
    public DataIsSucces login(String telno, String pass) throws UnsupportedEncodingException {
        DataIsSucces  dataIsSucces  = new DataIsSucces();
        System.out.println(telno + pass);
        Staff sys = staffService.findStaffByTelNo(telno);
        if (sys != null) {
            if (pass.equals(sys.getPass())) {
                dataIsSucces.setIsSuccess("0001");
                dataIsSucces.setDesc("登陆成功");
            } else {
                dataIsSucces.setIsSuccess("0002");
                dataIsSucces.setDesc("用户名或密码错误");
            }

        } else {
            dataIsSucces.setIsSuccess("0002");
            dataIsSucces.setDesc("用户名不存在");
        }
        System.out.println(dataIsSucces.toString());
        return dataIsSucces;
    }
    //注册接口
    @RequestMapping(value="/register",method = RequestMethod.GET)
    @ResponseBody
    @Transactional    //事务控制注解
    public DataIsSucces  register(String telno, String pass,String refereeID)throws Exception{
        DataIsSucces  dataIsSucces  = new DataIsSucces();
        System.out.println(telno + pass);
        Staff sys = staffService.findStaffByTelNo(telno);
        if(sys!=null){
            dataIsSucces.setIsSuccess("0002");
            dataIsSucces.setDesc("该用户已被注册");
        }else{
            if(refereeID!=null){
                Staff byRefereeID = staffService.findStaffByTelNo(refereeID);
                if(byRefereeID==null){
                    dataIsSucces.setIsSuccess("0002");
                    dataIsSucces.setDesc("推荐人ID不存在");
                    return dataIsSucces;
                }
            }
            Staff staff=new Staff();
            staff.setCid(UUID.randomUUID().toString().replace("-",""));
            staff.setTelno(telno);
            staff.setPass(pass);
            staff.setRefereeID(refereeID);
            staffService.saveStaffInfo(staff);
            dataIsSucces.setIsSuccess("0001");
            dataIsSucces.setDesc("注册成功");
        }
        System.out.println(dataIsSucces.toString());
        return dataIsSucces;
    }

    //修改密码
    @RequestMapping(value="/updatePass",method = RequestMethod.GET)
    @ResponseBody
    @Transactional    //事务控制注解
    public DataIsSucces  updatePass(String telno, String pass)throws Exception{
        DataIsSucces  dataIsSucces  = new DataIsSucces();
        System.out.println(telno + pass);
        Staff sys = staffService.findStaffByTelNo(telno);
        if(sys!=null){
            sys.setPass(pass);
            staffService.updateStaffPass(sys);
            dataIsSucces.setIsSuccess("0001");
            dataIsSucces.setDesc("修改成功");
        }else{
            dataIsSucces.setIsSuccess("0002");
            dataIsSucces.setDesc("修改失败用户名不存在");
        }
        System.out.println(dataIsSucces.toString());
        return dataIsSucces;
    }
    //返回二维码需要的链接地址和邀请码
    @RequestMapping(value="/inviteCode",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> inviteCode(String telno){
        //JSONObject json=new JSONObject();
        Map map= new HashMap();
        System.out.println(telno);
        //Staff sys=staffService.findStaffByTelNo(telno);
        //if(sys!=null){
            map.put("refereeID",telno);
            map.put("address","www.baidu.com");
        //}
        //json.put("data",map);
        //System.out.println(json.toString());
        return map;
    }

    //上传头像
    @RequestMapping(value="/uploadPic",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadPic(@RequestParam("file")MultipartFile file,String cid){
        Map map = new HashMap<>();
        // 返回上传的文件是否为空，即没有选择任何文件，或者所选文件没有内容。
        // 防止上传空文件导致奔溃
        if (file.isEmpty()) {
            map.put("isSuccess", "0002");
            map.put("desc", "文件为空");
            return map;
        }

        // 初始文件名
        String fileName = file.getOriginalFilename();
        // 获取图片的扩展名
        String extensionName = StringUtils.substringAfter(fileName, ".");
        // 新的图片文件名 = uuid+"."图片扩展名
        String newFileName = UUID.randomUUID().toString().replace("-","") + "." + extensionName;
        // 创建文件路径
        File dest = new File(webUploadPath + newFileName);

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            // 假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            // transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);
            // 将链接保存到URL中
            Staff sys = staffService.getStaffByCid(cid);
            if(sys!=null){
                sys.setHeadPortrait(webUploadPath + newFileName);
                staffService.updateStaffHead(sys);
                map.put("isSuccess", "0001");
                map.put("desc", "上传成功");
                map.put("picUrl", webUploadPath + newFileName);
            }else{
                map.put("isSuccess","0002");
                map.put("desc","修改失败用户名不存在");
            }
            return map;
        } catch (Exception e) {
            map.put("isSuccess", "0002");
            map.put("desc", "系统错误");
            return map;
        }
    }




}
