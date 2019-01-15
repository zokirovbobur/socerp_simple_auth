package bbro.socerp.Auth;

import bbro.socerp.EmailConfirmation.EmailSender;
import bbro.socerp.EmailConfirmation.TECDService;
import bbro.socerp.EmailConfirmation.TemporaryEmailConfirmationDetails;
import bbro.socerp.ServerMessages.ServerMessage;
import bbro.socerp.User.Users;
import bbro.socerp.User.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("auth")
public class SignUpController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private TECDService tecdService;
    @Autowired
    private EmailSender emailSender;

    @GetMapping("usernameisfree/{username}")
    public ServerMessage usernameIsFree(@PathVariable String username){
        Users user = usersService.getUserByUserName(username);
        ServerMessage serverMessage = new ServerMessage("","Checking");
        try {
            if(user.getUserName().equals(username))
            {
                serverMessage.setMessage("username is already occupied");
            }
        }
        catch (NullPointerException e){
            serverMessage.setMessage("username is free");
        }
        return serverMessage;
    }
    @GetMapping("emailisfree/{email}")
    public ServerMessage emailIsFree(@PathVariable String email){
        Users user = usersService.getUserByEmail(email);
        ServerMessage serverMessage = new ServerMessage("","Checking");

        try {
            if(user.getEmail().equals(email))
            {
                serverMessage.setMessage("email is already occupied");
            }
        }
        catch (NullPointerException e){
            serverMessage.setMessage("email is free");
        }
        return serverMessage;
    }

    @PostMapping("askConfirm")
    public ServerMessage askConfirm(@RequestBody TemporaryEmailConfirmationDetails tecd){
        String tecdNumber = getRandomNumberString();
        tecd.setVerificationCode(tecdNumber);
        tecdService.addNewTECD(tecd);
        System.out.println("tecd service");
        emailSender.sendMail(tecdNumber,tecd.getUserEmail());

        return new ServerMessage("Verification Code sent","info");
    }
    @PostMapping("getConfirm")
    public ServerMessage getConfirm(@RequestBody TemporaryEmailConfirmationDetails tecd){
        boolean confirmed;
        try {
            confirmed = tecdService.getTECDbyEmail(tecd.getUserEmail()).getVerificationCode().equals(tecd.getVerificationCode());
        }catch (NullPointerException e){
            confirmed = false;
        }
        ServerMessage serverMessage = new ServerMessage("","info");
        if (confirmed){
            serverMessage.setMessage("confirmed");

            Users user = new Users("",tecd.getUserEmail());
            usersService.getUserByEmail(tecd.getUserEmail());
            try {
                usersService.addUser(user);
               // usersService.setUserById(user);
            }catch (org.springframework.dao.DataIntegrityViolationException e){
                e.printStackTrace();
            }


            System.out.println("initial sign up for new person with email: " + tecd.getUserEmail());

            serverMessage.setUserId(usersService.getUserByEmail(tecd.getUserEmail()).getUserId());


            //deletes temp data for confirm email
           // tecdService.deleteByEmail(tecd.getUserEmail());
        }else {
            serverMessage.setMessage("not confirmed");
        }
        return serverMessage;
    }
    @PutMapping("updateUser")
    public void newPassword(@RequestBody Users user){
        usersService.addUser(user);
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
