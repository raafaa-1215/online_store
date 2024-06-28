
/**
 * This enum defines error codes for accounts
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (12/06/2024)
 */
public enum StringErrorCode
{
    INVALID_EMAIL, INVALID_NAME, INVALID_PASSWORD, NOT_GLOBAL_ADMIN;
    
    @Override
    public String toString(){
        if(this == INVALID_EMAIL){
            return "String is not an email";
        } else if(this == INVALID_NAME){
            return "String is not a name";
        } else if(this == INVALID_PASSWORD){
            return "String needs to be between 6 and 20 characters";
        } else if(this == NOT_GLOBAL_ADMIN){
            return "Need to be global admin to proceed";
        }
        return "";
    }
}
