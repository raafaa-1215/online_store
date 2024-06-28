/**
 * This handles error codes for clothing
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */
public enum ClothingErrorCode
{
    NULL_CLOTHING, NULL_NAME, NULL_CATEGORY, NULL_TYPE, INVALID_BUY_VALUE, INVALID_SELL_VALUE, INVALID_STOCK_QUANTITY;
    
    @Override
    public String toString(){
        if(this == NULL_CLOTHING){
            return "Clothing cannot be NULL";
        } else if(this == NULL_NAME){
            return "Name cannot be NULL";
        } else if(this == NULL_CATEGORY){
            return "Category cannot be NULL";
        } else if(this == NULL_TYPE){
            return "Type cannot be NULL";
        } else if(this == INVALID_BUY_VALUE){
            return "Buy value must be greater than 0";
        } else if(this == INVALID_SELL_VALUE){
            return "Sell value must be greater than the buy value";
        } else if(this == INVALID_STOCK_QUANTITY){
            return "Quantity must be greater than 0";
        }
        return "";
    }
}
