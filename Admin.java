
/**
 * This class handles Admin actions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */
public class Admin extends User implements Menu
{
    private boolean isGlobalAdmin;

    public Admin(String email, String password, boolean isGlobalAdmin)
    {
        super(email, password);
        this.isGlobalAdmin = isGlobalAdmin;
    }
    
    public boolean getIsGlobalAdmin() {
        return this.isGlobalAdmin;
    }

    @Override
    public void showMenu() {
        System.out.println("---- Admin Menu ----");
        System.out.println("1 - Add item");
        System.out.println("2 - Remove item");
        System.out.println("3 - Change item");
        System.out.println("4 - Re-stock item");
        System.out.println("5 - Display items based on stock");
        System.out.println("6 - Display items sold");
        if (this.isGlobalAdmin) {
            System.out.println("7 - Export sold items");
            System.out.println("8 - Logout");
        } else {
            System.out.println("7 - Logout");
        }
    }
}
