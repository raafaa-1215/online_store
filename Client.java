
/**
 * This class handles the client interactions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */

import java.util.ArrayList;

public class Client extends User implements Menu {
    // Attributes
    private String name;
    private Category category;
    private ArrayList<Clothing> cart;
    private ArrayList<Clothing> purchaseHistory;

    // Constructor
    public Client(String email, String password, String name, Category category) {
        super(email, password);
        if (name == null) {
            throw new UserIllegalArgumentException(UserErrorCode.NULL_NAME);
        } else {
            this.name = name;
        }
        if (category == null) {
            throw new UserIllegalArgumentException(UserErrorCode.NULL_CATEGORY);
        } else {
            this.category = category;
        }
        this.cart = new ArrayList<>();
        this.purchaseHistory = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public ArrayList<Clothing> getCart() {
        return this.cart;
    }

    public ArrayList<Clothing> getPurchaseHistory() {
        return this.purchaseHistory;
    }

    // Methods
    public void addToCart(Clothing item) {
        if (item == null) {
            throw new StockIllegalArgumentException();
        } else {
            if (item.getQuantity() > 0) {
                this.cart.add(item);
                item.setQuantity(item.getQuantity() - 1);
            } else {
                System.out.println("Item out of stock");
            }
        }
    }

    public void removeFromCart(Clothing item) {
        if (item == null) {
            throw new StockIllegalArgumentException();
        } else {
            if (this.cart.contains(item)) {
                this.cart.remove(item);
                item.setQuantity(item.getQuantity() + 1);
            } else {
                System.out.println("Item not found in cart");
            }
        }
    }

    public void checkout() {
        for (Clothing item : this.cart) {
            this.purchaseHistory.add(item);
        }
        this.cart.clear();
    }

    public void displayItemsBought() {
        if (this.purchaseHistory.isEmpty()) {
            System.out.println("No items bought");
        } else {
            for (Clothing item : this.purchaseHistory) {
                System.out.println(item.toString());
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("---- Client Menu ----");
        System.out.println("1 - Display available items");
        System.out.println("2 - Add item to cart");
        System.out.println("3 - Remove item from cart");
        System.out.println("4 - Checkout");
        System.out.println("5 - Display buying history");
        System.out.println("6 - Logout");
    }
}
