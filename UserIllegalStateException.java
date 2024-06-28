
/**
 * This handles exceptions for users
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (12/06/2024)
 */
public class UserIllegalStateException extends IllegalStateException
{
    // Construtor
    public UserIllegalStateException(StringErrorCode error)
    {
        super(error.toString());
    }
}
