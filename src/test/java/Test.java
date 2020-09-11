import com.hua.sys.mapper.MenuMapper;
import com.hua.sys.service.MenuService;

import java.io.File;

/**
 * @author cyh
 * @date 2020/8/17 11:12
 */
public class Test {

    private MenuService menuService;

    @org.junit.Test
    public void TestInterface(){
        File oldName = new File("D:/program.txt");
        File newName = new File("D:/java.txt");
        if(oldName.renameTo(newName)) {
            System.out.println("已重命名");
        } else {
            System.out.println("Error");
        }
    }
}
