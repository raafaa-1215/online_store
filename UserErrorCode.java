
/**
 * This enum handles account error codes
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */
public enum UserErrorCode
{
    NULL_USER, NULL_EMAIL, NULL_PASSWORD, NULL_NAME, NULL_CATEGORY;
    
    @Override
    public String toString(){
        if(this == NULL_USER){
            return "User cannot be NULL";
        } else if(this == NULL_EMAIL){
            return "Email cannot be NULL";
        } else if(this == NULL_PASSWORD){
            return "Password cannot be NULL";
        } else if(this == NULL_NAME){
            return "Name cannot be NULL";
        } else if(this == NULL_CATEGORY){
            return "Category cannot be NULL";
        }
        return "";
    }
}
