package bbro.socerp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }
    @PostMapping
    public void addUser(@RequestBody Users user){
        usersService.addUser(user);
    }

    @PutMapping
    public void setUserById(@RequestBody Users users){
        usersService.setUserById(users);
    }

    @GetMapping("{userId}")
    public Users getUser(@PathVariable Long userId){
        return usersService.getUserById(userId);
    }

    @DeleteMapping("{userId}")
    public void deleteById(@PathVariable Long userId){usersService.deleteUser(userId);}
}
