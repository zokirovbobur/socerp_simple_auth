package bbro.socerp.User;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findByUserId(Long userId);
    Users findByUserName(String userName);
    Users findByEmail(String email);


    @Transactional
    void removeByUserId(Long userId);

}
