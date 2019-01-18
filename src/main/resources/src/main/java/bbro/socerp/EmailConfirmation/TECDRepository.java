package bbro.socerp.EmailConfirmation;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface TECDRepository extends CrudRepository<TemporaryEmailConfirmationDetails,Long> {
    TemporaryEmailConfirmationDetails findByUserEmail(String userEmail);
    @Transactional
    void removeByUserEmail(String userEmail);
}
