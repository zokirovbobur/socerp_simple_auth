package bbro.socerp.EmailConfirmation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TemporaryEmailConfirmationDetails {
    @Id
    @GeneratedValue
    private Long tecdId;
    @Column(unique = true)
    private String userEmail;
    @Column(unique = true)
    private String verificationCode;

    public TemporaryEmailConfirmationDetails(String userEmail, String verificationCode) {
        this.userEmail = userEmail;
        this.verificationCode = verificationCode;
    }

    public TemporaryEmailConfirmationDetails() {
    }

    public Long getTecdId() {
        return tecdId;
    }

    public void setTecdId(Long tecdId) {
        this.tecdId = tecdId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
