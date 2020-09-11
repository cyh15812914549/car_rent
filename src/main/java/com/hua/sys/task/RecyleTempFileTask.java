package com.hua.sys.task;

import com.hua.sys.constast.SysConstast;
import com.hua.sys.utils.AppFileUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

/**回收temp结尾的图片
 * @author cyh
 * @date 2020/9/10 21:00
 */
@Component
@EnableScheduling
public class RecyleTempFileTask {

    /**
     * 每天晚上12点执行
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void recyleTempFile(){
        File file = new File(AppFileUtils.PATH);
        deleteFile(file);
    }

    /**
     * 删除图片
     * @param file
     */
    public void deleteFile(File file){
        if (file != null){
            File[] files = file.listFiles();
            if (files != null && files.length>0){
                for (File f : files) {
                    if (f.isFile()){
                        if (f.getName().endsWith(SysConstast.FILE_UPLOAD_TEMP)){
                            f.delete();
                        }
                    }else {
                        deleteFile(f);
                    }
                }
            }
        }
    }
}
