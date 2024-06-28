
/**
 * This class handles clothing actions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */
public class Clothing
{
    private static int codeNumber = 1;
    
    // Atributes
    private int code;
    private String name;
    private Category category;
    private Type type;
    private double buyPrice;
    private double sellPrice;
    private int quantity;

    // Constructor
    public Clothing(String name, Category category, Type type, double buyPrice, double sellPrice, int quantity)
    {
        if(name == null){
            throw new ClothingIllegalArgumentException(ClothingErrorCode.NULL_NAME);
        } else {
            this.name = name;
        }
        if(category == null){
            throw new ClothingIllegalArgumentException(ClothingErrorCode.NULL_CATEGORY);
        } else {
            this.category = category;
        }
        if(type == null){
            throw new ClothingIllegalArgumentException(ClothingErrorCode.NULL_TYPE);
        } else {
            this.type = type;
        }
        if(buyPrice <= 0){
            throw new ClothingIllegalArgumentException(ClothingErrorCode.INVALID_BUY_VALUE);
        } else {
            this.buyPrice = buyPrice;
        }
        if(sellPrice < buyPrice){
            throw new ClothingIllegalArgumentException(ClothingErrorCode.INVALID_SELL_VALUE);
        } else {
            this.sellPrice = sellPrice;
        }
        if(quantity <= 0){
            throw new ClothingIllegalArgumentException(ClothingErrorCode.INVALID_STOCK_QUANTITY);
        } else {
            this.quantity = quantity;
        }
        this.code = codeNumber++;
    }
    
    // Getters
    public int getCode() {
        return this.code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public double getBuyPrice() {
        return this.buyPrice;
    }
    
    public double getSellPrice() {
        return this.sellPrice;
    }
    
    public int getQuantity() {
        return this.quantity;
    }
    
    // Setters    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
    
    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    // Methods
    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s | %.2f€ | %d available", this.code, this.name, this.category, this.type, this.sellPrice, this.quantity);
    }
}
