package com.cn.lianshou.controllrer;

import com.cn.lianshou.common.util.Constant;
import com.cn.lianshou.common.util.ServletUtils;
import com.cn.lianshou.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: com.cn.lianshou.controllrer.FileUploadController.java
 * Author: Wanghh
 * Date: 2018/3/26 17:40
 */
@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 请求文件上传页面
     * @return
     */
    @RequestMapping(value = "/show/upload/file")
    public String file(){

        return "uploadFile";
    }

    /**
     * 文件上传方法
     * @param file
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/show/uploadfile",method = RequestMethod.POST)
    public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println("文件路径：" + path);
        String originalFilename = file.getOriginalFilename();
        String type = file.getContentType();
        System.out.println("目标文件名称：" + originalFilename + ",目标文件类型：" + type);
        File targetFile = new File(path, originalFilename);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        } else if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 获得上传文件的文件扩展名
        String subname = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        System.out.println("文件的扩展名：" + subname);

        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String rootpath = path + File.separator + originalFilename;
        List<String[]> excellist = fileUploadService.readExcel(rootpath);

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            fileUploadService.list(excellist);

            result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "导入成功！");

        } catch (Exception e){

            result.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            result.put(Constant.RESPONSE_CODE_MSG, "导入失败,请检查提交数据格式！");
        }
        ServletUtils.writeToResponse(response,result);

    }

}
