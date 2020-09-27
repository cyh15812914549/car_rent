import com.hua.bus.entity.Customer;
import com.hua.sys.service.MenuService;

import java.io.InputStream;

/**
 * @author cyh
 * @date 2020/8/17 11:12
 */
public class Test {

    private MenuService menuService;

    Customer customer;

    @org.junit.Test
    public void TestInterface(){
        InputStream logoStream = Test.class.getClassLoader().getResourceAsStream("images/avatar.jpg");
        System.out.println(logoStream+"logoStream这里");
    }
}
