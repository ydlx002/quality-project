package com.gxjtkyy.quality.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传工具类
 * @Package com.gxjtkyy.quality.utils
 * @Author lizhenhua
 * @Date 2018/6/6 9:33
 */
public class UploadUtil {

    /**
     * @author Bobby
     * 上载文件，返回文件路径 /images/xxx/xx/xx.xls
     * @param request
     * @param name :productName 或者 categoryName 或者（图片）productId
     * @param filetype :文件类型 file 的name属性 :image excel
     * @return
     */
    public static Map<String,String> uploadFile(HttpServletRequest request, String name, String filetype, String urlConfig){
        Map<String,String> map = new HashMap<String, String>();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //构建保存的目录
        String logPathDir = "";
        String rootPath = urlConfig;
        //得到保存目录的真实路径
        logPathDir = rootPath+File.separator+filetype;
        //根据真实路径创建目录
        File logSaveFile = new File(logPathDir);
        if(!logSaveFile.exists())
            logSaveFile.mkdirs();
        //页面控件的文件流
        MultipartFile multipartFile = multipartRequest.getFile(filetype);//file 的name属性
        //获取原文件名称 IMG0001.jpg
        String fileName = multipartFile.getOriginalFilename();
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String logFileName =  name+suffix;//构建文件名称
        //拼成完整的文件保存路径加文件
        String filePath = logPathDir + File.separator + logFileName;
        File file = new File(filePath);
        map.put("filePath", filePath);
        map.put("relPath", filePath);//相对路径
        map.put("fileName", logFileName);

        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}
