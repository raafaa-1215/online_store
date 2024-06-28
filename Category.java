
/**
 * This enum handles gender
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (12/06/2024)
 */
public enum Category
{
    MAN, WOMAN;
    
    @Override
    public String toString() {
        return (this == MAN)? "Man":"Woman";    
    }
}
