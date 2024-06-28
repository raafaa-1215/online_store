
/**
 * This handles exceptions for clothing
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (05/06/2024)
 */
public class StockIllegalArgumentException extends IllegalArgumentException
{
    // Construtor
    public StockIllegalArgumentException()
    {
        super(ClothingErrorCode.NULL_CLOTHING.toString());
    }
}
