
/**
 * This class handles User abstract functions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */
public abstract class User
{
    // Atributos
    private String email;
    private String password;
    
    // Construtores
    public User(String email, String password)
    {
        if(email == null){
            throw new UserIllegalArgumentException(UserErrorCode.NULL_EMAIL);
        } else if(!email.contains("@") || !email.contains(".")){
            throw new UserIllegalStateException(StringErrorCode.INVALID_EMAIL);
        } else {
            this.email = email;
        }
        if(password == null){
            throw new UserIllegalArgumentException(UserErrorCode.NULL_PASSWORD);
        } else if(password.length() < 1 || password.length() > 20){
            throw new UserIllegalStateException(StringErrorCode.INVALID_PASSWORD);
        } else {
            this.password = password;
        }
    }

    // Getters
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
}
