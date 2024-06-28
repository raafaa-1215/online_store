
/**
 * This class handles user exceptions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */
public class UserIllegalArgumentException extends IllegalArgumentException
{
    // Construtor
    public UserIllegalArgumentException(UserErrorCode error)
    {
        super(error.toString());
    }
}
