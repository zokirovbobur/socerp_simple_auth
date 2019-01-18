package bbro.socerp.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;


    public List<Users> getAllUsers(){
        return (List<Users>) usersRepository.findAll();
    }
    public void addUser(Users users){
        usersRepository.save(users);
    }
    public Users getUserById(Long userId){
       return usersRepository.findByUserId(userId);
    }
    public Users getUserByUserName(String username){
        return usersRepository.findByUserName(username);
    }
    public Users getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
    public void setUserById(Users user){
        usersRepository.save(user);
    }
    public void deleteUser(Long userId){
        usersRepository.removeByUserId(userId);
    }
}
