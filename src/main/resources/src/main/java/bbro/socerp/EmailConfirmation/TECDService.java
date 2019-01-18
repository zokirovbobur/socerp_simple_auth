package bbro.socerp.EmailConfirmation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TECDService {
    @Autowired
    private TECDRepository TECDRepository;

    public void addNewTECD(TemporaryEmailConfirmationDetails temporaryEmailConfirmationDetails){
        deleteByEmail(temporaryEmailConfirmationDetails.getUserEmail());
        TECDRepository.save(temporaryEmailConfirmationDetails);
    }
    public TemporaryEmailConfirmationDetails getTECDbyEmail(String userEmail){
       return TECDRepository.findByUserEmail(userEmail);
    }
    public void deleteByEmail(String userEmail){
        TECDRepository.removeByUserEmail(userEmail);}
}
