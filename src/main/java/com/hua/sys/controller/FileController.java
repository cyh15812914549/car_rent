package com.hua.sys.controller;

import com.hua.sys.constast.SysConstast;
import com.hua.sys.utils.AppFileUtils;
import com.hua.sys.utils.DataGridView;
import com.hua.sys.utils.RandomUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传下载的控制器
 * @author cyh
 * @date 2020/9/8 15:10
 */
@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * 添加
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("/uploadFile")
    @ResponseBody
    public DataGridView uploadFile(@RequestParam(value="file",required=false) MultipartFile file) throws IllegalStateException, IOException{
        // 文件上传的父目录
        String parentPath = AppFileUtils.PATH;
        // 得到当前日期作为文件夹名称
        String dirName = RandomUtils.getCurrentDateForString();
        // 构造文件夹对象
        File dirFile = new File(parentPath,dirName);
        System.out.println(dirFile+"dirFile");
        if (!dirFile.exists()){
            //创建文件夹
            dirFile.mkdir();
        }
        //得到文件原名
        String oldName = file.getOriginalFilename();
        //根据文件原名得到新名
        String newName = RandomUtils.createFileNameUseTime(oldName, SysConstast.FILE_UPLOAD_TEMP);

        //创建文件
        File dest = new File(dirFile,newName);
        System.out.println(dest+"dest");
        //上传文件写到服务器上指定的文件
        file.transferTo(dest);
        Map<String,Object> map = new HashMap<>();
        map.put("src",dirName+"/"+newName);
        return new DataGridView(map);
    }

    /**
     * 不下载只显示
     */
    @RequestMapping("/downloadShowFile")
    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response){
        return AppFileUtils.downloadFile(response,path,"");
    }

    /**
     * 下载图片
     * @param path
     * @param response
     * @return
     */
    @RequestMapping("/downloadFile")
    public ResponseEntity<Object> downloadFile(String path, HttpServletResponse response) {
        String oldName="";
        return AppFileUtils.downloadFile(response, path, oldName);
    }
}
