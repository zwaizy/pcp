/**
 * 2015-2016 龙果学院 (www.roncoo.com)
 */
package com.vcread.pcp.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.vcread.pcp.configure.WebSecurityConfig;
import com.vcread.pcp.dto.FileDTO;
import com.vcread.pcp.entity.FrameDepartment;
import com.vcread.pcp.entity.UserDept;
import com.vcread.pcp.result.Result;
import com.vcread.pcp.result.ResultGenerator;
import com.vcread.pcp.service.FrameDepartmentService;
import com.vcread.pcp.service.UserDeptService;
import com.vcread.pcp.util.zip.ZipUtils;

/**
 * spring-boot-demo-12-1
 * 
 * @author wujing
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	

	@Value("${sp.excelsPath}")
	public String excelsPath;

	private static final String SUFFIEX = ".zip";
	
	private static final String PATH = "/files/";
	
    @Autowired
    private UserDeptService userDeptService;

    @Autowired
    private FrameDepartmentService frameDepartmentService;
	@RequestMapping(value = "upload")
	@ResponseBody
	public Result upload(@RequestParam("file") MultipartFile file,String dirName) {
		if (file.isEmpty()) {
			return ResultGenerator.genFailResult("文件不存在！");
		}

		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);

		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);

		// 文件上传路径
		String filePath = excelsPath;

		// 解决中文问题，liunx下中文路径，图片显示问题
//		fileName = UUID.randomUUID() + suffixName;
		File dest = new File(filePath + dirName + File.separator + fileName);

		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		try {
			file.transferTo(dest);
			if (suffixName.equalsIgnoreCase(SUFFIEX)) {
				ZipUtils.unzip(filePath + dirName + File.separator + fileName,filePath + dirName + File.separator );
//				ZipUtils.deleteFile(filePath + dirName + File.separator + fileName);
			}
			return ResultGenerator.genSuccessResult();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResultGenerator.genFailResult("上传失败！");
	}


    /**
     * 文件列表
     * @param fileName
     * @param request
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping(value = "show")
    @ResponseBody
    public Result show (String fileName,HttpServletRequest request) throws Exception{
        boolean role = (boolean) request.getSession().getAttribute(WebSecurityConfig.SESSION_ROLE);
        String userName=request.getSession().getAttribute(WebSecurityConfig.SESSION_KEY).toString();
        String deptName="";
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFlag(role);
        String path = excelsPath;
        if(!StringUtils.isEmpty(fileName)){
            path=path+fileName;
            File f = new File(path);
            String[] childs = f.list(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return !name.endsWith(".ZIP") && !name.endsWith(".zip");
				}
			});
            if(role){
            	fileDTO.setFileName(Arrays.asList(childs));
            }else{
                UserDept userDept=userDeptService.getUserDept(userName);
                String dept_code=userDept.getFram_seq().toString();
                String fram_code=userDept.getFram_code().toString();
                FrameDepartment frameDepartment=frameDepartmentService.getFrameDepartment(dept_code,fram_code);
                if(frameDepartment != null){
                	deptName= frameDepartment.getDept_name();
                	List<String> list = new ArrayList<String>();
                	for(int i=0; i<childs.length; i++) {
                		try {
                			String name = childs[i].split("\\.")[0].split("_")[1];
                			if(deptName.equals(name)){
                				list.add(childs[i]);
//                				path = PATH + fileName + File.separator + childs[i];
                				fileDTO.setFileName(list);
                				//fileDTO.setPath(path);
                				break;
                			}
						} catch (Exception e) {
							logger.error("fileName is illeagele,file = " + childs[i]);
						}
                	}
                }
            }
        }else{
            File f = new File(path);
            String[] childs = f.list();
            fileDTO.setFileName(Arrays.asList(childs));
        }
		fileDTO.setPath(excelsPath);
		return ResultGenerator.genSuccessResult(fileDTO);
    }

    /**
     * 删除文件
     * @param path
     * @param fileName
     * @return
     */
    @RequestMapping(value = "delFile")
    @ResponseBody
    public Result delFile(String dirName, String fileName) {
        // 文件路径
        File targetFile = new File(excelsPath + dirName + File.separator + fileName);
        if (targetFile.exists()) {
	        boolean bool=targetFile.delete();
	        if(bool){
	            return  ResultGenerator.genSuccessResult();
	        } else {
	            return  ResultGenerator.genFailResult("文件删除失败！");
	        }
        }
        return  ResultGenerator.genFailResult("文件不存在！");
    }
    
    @RequestMapping(value = "downloadFile")
    public void downloadLocal(HttpServletResponse response,HttpServletRequest request,String dirName,String fileName) throws Exception {
    	//获得请求文件名  
    	response.setCharacterEncoding("UTF-8");
        //设置文件MIME类型  
        response.setContentType("application/x-download");
        InputStream in = new FileInputStream(excelsPath+dirName+File.separator+fileName);  
        /*
         * 解决各浏览器的中文乱码问题
         */
        String userAgent = request.getHeader("User-Agent");
        byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
                : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
        fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
        response.setHeader("Content-disposition",
                String.format("attachment; filename=\"%s\"", fileName));
        
//        response.setHeader("Content-Disposition", "attachment;filename="+fileName);  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
//        String fullFileName = request.getServletContext().getRealPath("/download/" + fileName);  
        //System.out.println(fullFileName);  
        //读取文件  
        OutputStream out = response.getOutputStream();  
          
        //写文件  
        int b;  
        while((b=in.read())!= -1)  
        {  
            out.write(b);  
        }  
        
        in.close();  
        out.close();  
    }
}
