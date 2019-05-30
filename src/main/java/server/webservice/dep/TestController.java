package server.webservice.dep;

import com.example.demo.POJO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private User user;
    @RequestMapping("/test")
    public  String  getTest(){

        System.out.println("user"+ user);
        return  "dd";
    }
}
