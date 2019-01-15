package bbro.socerp.Auth;

import bbro.socerp.ServerMessages.ServerMessage;
import bbro.socerp.User.Users;
import bbro.socerp.User.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("login")
public class LoginController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    public ServerMessage login(@RequestBody Users user){
        Users checkUser;
        ServerMessage serverMessage = new ServerMessage();

        try {
            checkUser = usersService.getUserByUserName(user.getUserName());
            if(checkUser.getPassword().equals(user.getPassword())){
                serverMessage.setMessageType("Authentication");
                serverMessage.setMessage("success");
                serverMessage.setUserId(checkUser.getUserId());
            }
            else {
                serverMessage.setMessageType("error");
                serverMessage.setMessage("password is incorrect!");
            }
        }catch (NullPointerException e)
        {
            serverMessage.setMessageType("error");
            serverMessage.setMessage("login is incorrect");
        }

        return serverMessage;
    }
}
