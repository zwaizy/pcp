/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.vcread.pcp.controller;

import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.entity.UserDept;
import com.vcread.pcp.service.FrameDepartmentService;
import com.vcread.pcp.service.UserDeptService;
import com.vcread.pcp.util.zip.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * spring-boot-demo-12-1
 * 
 * @author wujing
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	private static final String SUFFIEX = ".zip";
    @Autowired
    private UserDeptService userDeptService;

    @Autowired
    private FrameDepartmentService frameDepartmentService;
	@RequestMapping(value = "upload")
	@ResponseBody
	public String upload(@RequestParam("roncooFile") MultipartFile file) {
		if (file.isEmpty()) {
			return "文件为空";
		}

		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);

		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);

		// 文件上传路径
		String filePath = "d:/roncoo/";

		// 解决中文问题，liunx下中文路径，图片显示问题
//		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + fileName);

		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		try {
			file.transferTo(dest);
			if (suffixName.equals(SUFFIEX)) {
				ZipUtils.unzip(filePath + fileName,filePath);
			}
			return "上传成功";
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "上传失败";
	}


    /**
     * 文件列表
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping(value = "show")
    @ResponseBody
    public Map<String,Object> show (String fileName,HttpServletRequest request){
		Map<String,Object> resultMap=new HashMap<String,Object>();
        int role=Integer.parseInt(request.getSession().getAttribute("role").toString());
        String userName=request.getSession().getAttribute("user").toString();
        String deptName="";
        List<String> myList =new ArrayList<String>();
        String path="E://dianxin";
        if(!StringUtils.isEmpty(fileName)){
            path=path+"/"+fileName;
        }
        File f = new File(path);
        String[] childs = f.list();
        if(role == 7){
            FrameDepartment frameDepartment=new FrameDepartment();
            UserDept userDept=userDeptService.getUserDept(userName);
            String dept_code=userDept.getUser_code().toString();
            String fram_code=userDept.getFram_code().toString();
            frameDepartment=frameDepartmentService.getFrameDepartment(dept_code,fram_code);
            deptName= frameDepartment.getDept_name();
            for(int i=0; i<childs.length; i++) {
                if(deptName.equals(childs[i])){
                    myList.add(childs[i]);
                    break;
                }
            }
        }else{
            Collections.addAll(myList, childs);

        }
        resultMap.put("myList",myList);
        resultMap.put("filePath",path);
        return resultMap;
    }

}
