
/**
 * This class handles exceptions for clothing
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */ 
public class ClothingIllegalArgumentException extends IllegalArgumentException
{
    // Construtor
    public ClothingIllegalArgumentException(ClothingErrorCode error)
    {
        super(error.toString());
    }
}
