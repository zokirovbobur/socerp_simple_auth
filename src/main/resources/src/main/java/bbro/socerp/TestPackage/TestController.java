package bbro.socerp.TestPackage;

import bbro.socerp.EmailConfirmation.EmailSender;
import bbro.socerp.ServerMessages.ServerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
        @RequestMapping("test")
public class TestController {

    @Autowired
    EmailSender emailSender;


    @GetMapping("intest")
    public ServerMessage test(){


        return new ServerMessage("test","test1");
    }
    @GetMapping
    public ServerMessage test2(){


        return new ServerMessage("test","test2");
    }
    @GetMapping("intest/sa")
    public ServerMessage test3(){


        return new ServerMessage("test","test3");
    }
}
