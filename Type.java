/**
 * This enum handles types of clothing
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (12/06/2024)
 */
public enum Type
{
    DRESS, SHOES, TSHIRT, SKIRT, TROUSERS, JEANS, PYJAMA, SOCKS, UNDERWEAR, HOODIE, SHIRT, SHORTS, JACKET, HAT;
    
    @Override
    public String toString(){
        if(this == DRESS){
            return "Dress";
        } else if(this == SHOES){
            return "Shoes";
        } else if(this == TSHIRT){
            return "T-shirt";
        } else if(this == SKIRT){
            return "Skirt";
        } else if(this == TROUSERS){
            return "Trousers";
        } else if(this == JEANS){
            return "Jeans";
        } else if(this == PYJAMA){
            return "Pyjama";
        } else if(this == SOCKS){
            return "Socks";
        } else if(this == UNDERWEAR){
            return "Underwear";
        } else if(this == HOODIE){
            return "Hoodie";
        } else if(this == SHIRT){
            return "Shirt";
        } else if(this == SHORTS){
            return "Shorts";
        } else if(this == JACKET){
            return "Jacket";
        } else if(this == HAT){
            return "Hat";
        }
        return "";
    }
}
