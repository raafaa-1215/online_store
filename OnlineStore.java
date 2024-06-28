
/**
 * This class handles store interactions
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (29/05/2024)
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStore implements Menu {
    
    // Atributes
    private Stock stock;
    private ArrayList<Clothing> soldItemsList;
    private ArrayList<User> registeredUsers;
    private ArrayList<Clothing> soldItems;
    private User loggedInUser;

    // Constructor
    public OnlineStore() {
        this.stock = new Stock();
        this.soldItemsList = new ArrayList<Clothing>();
        this.registeredUsers = new ArrayList<User>();
        this.soldItems = new ArrayList<Clothing>();
        this.loggedInUser = null;
    }

    // Client methods
    public void displayItemsWithStock() {
        if (stock.getClothingList().size() == 0) {
            System.out.println("No items in stock");
        } else {
            for (Clothing item : stock.getClothingList()) {
                System.out.println(item.toString());
            }
        }
    }

    public void checkout(Client user) {
        for (Clothing itemSold : user.getPurchaseHistory()) {
            this.soldItemsList.add(itemSold);
        }
    }

    // Admin methods
    public void addItem(Clothing item) {
        if (item == null) {
            throw new StockIllegalArgumentException();
        } else {
            this.stock.addClothing(item);
        }
    }

    public void removeItem(Clothing item) {
        if (item == null) {
            throw new StockIllegalArgumentException();
        } else {
            this.stock.removeClothing(item);
        }
    }

    public void changeItem() {
        System.out.println("---- Change Item ----");
        System.out.println("1 - Change name");
        System.out.println("2 - Change category");
        System.out.println("3 - Change type");
        System.out.println("4 - Change price");
        System.out.println("0 - Back");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 0) {
            return;
        }

        int code;
        do {
            System.out.println("Insert item code:");
            code = scanner.nextInt();
            if (this.stock.getClothingByCode(code) != null) {
                break;
            } else {
                System.out.println("Clothing with inserted code doesn't exist, try again");
            }
        } while (true);

        switch (option) {
            case 1:
                String name;
                do {
                    System.out.println("Insert item new name:");
                    name = scanner.next();
                } while (name == null);
                this.stock.getClothingByCode(code).setName(name);
                break;
            case 2:
                String category;
                do {
                    System.out.println("Insert item new category:");
                    category = scanner.next();
                } while (stringToCategory(category) == null);
                this.stock.getClothingByCode(code).setCategory(stringToCategory(category));
                break;
            case 3:
                String type;
                do {
                    System.out.println("Insert item new type:");
                    type = scanner.next();
                } while (stringToType(type) == null);
                this.stock.getClothingByCode(code).setType(stringToType(type));
                break;
            case 4:
                double buyPrice = 0, sellPrice = 0;
                do {
                    System.out.println("Insert item new buy price:");
                    buyPrice = scanner.nextDouble();
                    System.out.println("Insert item new sell price:");
                    sellPrice = scanner.nextDouble();
                } while (buyPrice <= 0 && sellPrice <= 0);
                this.stock.getClothingByCode(code).setBuyPrice(buyPrice);
                this.stock.getClothingByCode(code).setSellPrice(sellPrice);
                break;
            default:
                System.out.println("Invalid option, try again");
                return;
        }
    }

    public void changeItemStock() {
        Scanner scanner = new Scanner(System.in);
        int code;
        do {
            System.out.println("Insert item code:");
            code = scanner.nextInt();
            if (this.stock.getClothingByCode(code) != null) {
                break;
            } else {
                System.out.println("Clothing with inserted code doesn't exist, try again");
            }
        } while (true);

        int quantity = 0;
        do {
            System.out.println("Insert item new quantity (>0):");
            quantity = scanner.nextInt();
        } while (quantity <= 0);
        this.stock.getClothingByCode(code).setQuantity(quantity);
    }
    
    public void exportItemsSold() {
        if (loggedInUser instanceof Admin && ((Admin) loggedInUser).getIsGlobalAdmin()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("sold_items_report.txt"))) {
                for (Clothing item : soldItems) {
                    writer.write(item.toString());
                    writer.newLine();
                }
                System.out.println("Sold items exported successfully.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Only global admins can export sold items.");
        }
    }

    public void displayItemsWithLessStock(int quantity) {
        for (Clothing clothing : this.stock.getClothingList()) {
            if (clothing.getQuantity() < quantity) {
                System.out.println(clothing.toString());
            }
        }
    }

    public void displayItemsSold() {
        double totalSales = 0.0;
        for (Clothing item : this.soldItemsList) {
            System.out.println(item.toString());
            totalSales += item.getSellPrice();
        }
        System.out.println("Total sales amount: " + totalSales + "€");
    }

    // Métodos
    @Override
    public void showMenu() {
        System.out.println("---- Store Menu ----");
        System.out.println("1 - Login");
        System.out.println("2 - Register Client");
        System.out.println("0 - Quit");
    }

    public void registerClient() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Insert new user email: ");
        String email = scanner.nextLine();
    
        System.out.print("Insert new user password: ");
        String password = scanner.nextLine();
    
        System.out.print("Insert new user name: ");
        String name = scanner.nextLine();
    
        System.out.print("Insert new user category (MAN/WOMAN): ");
        String categoryStr = scanner.nextLine().toUpperCase();
    
        Category category;
        try {
            category = Category.valueOf(categoryStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category entered. Please enter 'MAN' or 'WOMAN'.");
            return; 
        }
    
        try {
            Client client = new Client(email, password, name, category);
            this.registeredUsers.add(client);
            System.out.println("Client registered successfully.");
        } catch (UserIllegalArgumentException | UserIllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        String email, password;
        do {
            System.out.println("Insert user email (type 'cancel' to go back):");
            email = scanner.nextLine();
            System.out.println("Insert user password:");
            password = scanner.nextLine();
            for (User user : this.registeredUsers) {
                if (user.getEmail().equals(email)) {

                    if (user.getPassword().equals(password)) {
                        this.loggedInUser = user;
                        return;
                    } else {
                        System.out.println("Password is incorrect");
                    }
                }
            }
            System.out.println("No registered user has the entered email");
        } while (this.loggedInUser == null || email == "cancel");
    }

    public void logout() {
        this.loggedInUser = null;
        System.out.println("Logged out");
    }

    // Other methods
    private Category stringToCategory(String category) {
        if (category.equalsIgnoreCase("WOMAN")) {
            return Category.WOMAN;
        }
        if (category.equalsIgnoreCase("MAN")) {
            return Category.MAN;
        }
        return null;
    }

    private Type stringToType(String type) {
        if (type.equalsIgnoreCase("dress")) {
            return Type.DRESS;
        }
        if (type.equalsIgnoreCase("shoes")) {
            return Type.SHOES;
        }
        if (type.equalsIgnoreCase("tshirt")) {
            return Type.TSHIRT;
        }
        if (type.equalsIgnoreCase("skirt")) {
            return Type.SKIRT;
        }
        if (type.equalsIgnoreCase("trousers")) {
            return Type.TROUSERS;
        }
        if (type.equalsIgnoreCase("jeans")) {
            return Type.JEANS;
        }
        if (type.equalsIgnoreCase("pyjama")) {
            return Type.PYJAMA;
        }
        if (type.equalsIgnoreCase("socks")) {
            return Type.SOCKS;
        }
        if (type.equalsIgnoreCase("underwear")) {
            return Type.UNDERWEAR;
        }
        if (type.equalsIgnoreCase("hoodie")) {
            return Type.HOODIE;
        }
        if (type.equalsIgnoreCase("shirt")) {
            return Type.SHIRT;
        }
        if (type.equalsIgnoreCase("shorts")) {
            return Type.SHORTS;
        }
        if (type.equalsIgnoreCase("jacket")) {
            return Type.JACKET;
        }
        if (type.equalsIgnoreCase("hat")) {
            return Type.HAT;
        }
        return null;
    }

    public boolean isLoggedInUserAdmin() {
        return loggedInUser instanceof Admin;
    }
    
    public Admin getLoggedInAdmin() {
        return (loggedInUser instanceof Admin) ? (Admin) loggedInUser : null;
    }

    public Client getLoggedInClient() {
        return (loggedInUser instanceof Client) ? (Client) loggedInUser : null;
    }
    
    public ArrayList<User> getRegisteredUsers() {
        return this.registeredUsers;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public Stock getStock() {
        return this.stock;
    }
}
