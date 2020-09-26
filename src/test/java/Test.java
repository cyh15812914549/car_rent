import com.hua.bus.entity.Customer;
import com.hua.bus.service.CustomerService;
import com.hua.bus.vo.CustomerVo;
import com.hua.sys.mapper.MenuMapper;
import com.hua.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

/**
 * @author cyh
 * @date 2020/8/17 11:12
 */
public class Test {

    private MenuService menuService;

    Customer customer;

    @org.junit.Test
    public void TestInterface(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CustomerService customerService = (CustomerService) context.getBean("CustomerServiceImpl");
        for (Customer customer : customerService.queryAllCustomerList(customer)) {
            System.out.println(customer.getIdentity());
        }
    }
}
