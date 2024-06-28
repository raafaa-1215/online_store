
/**
 * This class handles stock actions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */

import java.util.*;

public class Stock
{
    // Atributos
    private ArrayList<Clothing> clothingList;

    // Construtores
    public Stock()
    {
        this.clothingList = new ArrayList<Clothing>();
    }
    
    // Getters
    public ArrayList<Clothing> getClothingList(){
        return this.clothingList;
    }

    // Métodos
    public void addClothing(Clothing clothing){
        if(clothing == null){
            throw new StockIllegalArgumentException();
        } else {
            this.clothingList.add(clothing);
        }
    }
    
    public void removeClothing(Clothing clothing){
        if(clothing == null){
            throw new StockIllegalArgumentException();
        } else {
            this.clothingList.remove(clothing);
        }
    }

    public Clothing getClothingByCode(int code){
        for(Clothing clothing : this.clothingList){
            if(clothing.getCode() == code){
                return clothing;
            }
        } 
        return null;
    }
}
