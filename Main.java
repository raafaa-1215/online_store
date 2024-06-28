/**
 * This class handles the main outputs of the code
 *
 * @author (Rafael Rodrigues e Nelson Santos)
 * @version (28/05/2024)
 */


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();
        Scanner scanner = new Scanner(System.in);

        store.getRegisteredUsers().add(new Admin("adm1@vestbem.com", "123", false));
        store.getRegisteredUsers().add(new Admin("adm2@vestbem.com", "123", true));

        int option;
        do {
            store.showMenu();
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    store.login();
                    User loggedInUser = store.getLoggedInUser();
                    if (loggedInUser instanceof Admin) {
                        adminMenu(store, scanner);
                    } else if (loggedInUser instanceof Client) {
                        clientMenu(store, scanner);
                    }
                    break;
                case 2:
                    store.registerClient();
                    break;
                case 0:
                    System.out.println("Exiting.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);

        scanner.close();
    }

    // Interação com Amin
    private static void adminMenu(OnlineStore store, Scanner scanner) {
        int option;
        Admin admin = (Admin) store.getLoggedInUser();
        if (admin == null) return; 

        do {
            admin.showMenu();
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    addItem(store, scanner);
                    break;
                case 2:
                    removeItem(store, scanner);
                    break;
                case 3:
                    store.changeItem();
                    break;
                case 4:
                    store.changeItemStock();
                    break;
                case 5:
                    System.out.print("Maximum amount of stock: ");
                    int quantity = scanner.nextInt();
                    store.displayItemsWithLessStock(quantity);
                    break;
                case 6:
                    store.displayItemsSold();
                    break;
                case 7:
                    if (admin.getIsGlobalAdmin()) {
                        store.exportItemsSold();
                    } else {
                        store.logout();
                    }
                    break;
                case 8:
                    if (admin.getIsGlobalAdmin()) {
                        store.logout();
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 7 && option != 8);
    }

    private static void addItem(OnlineStore store, Scanner scanner) {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Category (man/woman): ");
        String categoryStr = scanner.nextLine();
        Category category = Category.valueOf(categoryStr.toUpperCase());

        System.out.print("Item type (dress/hat/hoodie/jacket/jeans/pyjama/shoes/shorts/skirt/socks/trousers/tshirt/underwear): ");
        String typeStr = scanner.nextLine();
        Type type = Type.valueOf(typeStr.toUpperCase());

        System.out.print("Buy price: ");
        double buyPrice = scanner.nextDouble();

        System.out.print("Sale price: ");
        double sellPrice = scanner.nextDouble();

        System.out.print("Initial stock amount: ");
        int quantity = scanner.nextInt();

        Clothing item = new Clothing(name, category, type, buyPrice, sellPrice, quantity);
        store.addItem(item);
        System.out.println("Item added successfully.");
    }

    private static void removeItem(OnlineStore store, Scanner scanner) {
        System.out.print("Item code: ");
        int code = scanner.nextInt();

        Clothing item = store.getStock().getClothingByCode(code);
        if (item != null) {
            store.removeItem(item);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Interação com Client
    private static void clientMenu(OnlineStore store, Scanner scanner) {
        int option;
        Client client = (Client) store.getLoggedInUser();
        if (client == null) { 
            return; 
        }
    
        do {
            client.showMenu();
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (option) {
                case 1:
                    store.displayItemsWithStock();
                    break;
                case 2:
                    addToCart(store, scanner);
                    break;
                case 3:
                    removeFromCart(store, scanner);
                    break;
                case 4:
                    checkout(store);
                    break;
                case 5:
                    viewPurchaseHistory(store);
                    break;
                case 6: 
                    store.logout();
                    System.out.println("Logout completed successfully.");
                    break;
                case 0: 
                    store.logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6 && option != 0); 
    }

    private static void addToCart(OnlineStore store, Scanner scanner) {
        System.out.print("Item code: ");
        int code = scanner.nextInt();

        Clothing item = store.getStock().getClothingByCode(code);
        if (item != null && item.getQuantity() > 0) {
            Client client = (Client) store.getLoggedInUser();
            client.addToCart(item);
            System.out.println("Item added to cart successfully.");
        } else {
            System.out.println("Item not found or stock unavailable.");
        }
    }

    private static void removeFromCart(OnlineStore store, Scanner scanner) {
        System.out.print("Item code: ");
        int code = scanner.nextInt();

        Client client = (Client) store.getLoggedInUser();
        Clothing item = client.getCart().stream().filter(c -> c.getCode() == code).findFirst().orElse(null);
        if (item != null) {
            client.removeFromCart(item);
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    private static void checkout(OnlineStore store) {
        Client client = (Client) store.getLoggedInUser();
        if (client.getCart().isEmpty()) {
            System.out.println("The cart is empty. Please add items before moving forward.");
        } else {
            double totalAmount = client.getCart().stream().mapToDouble(Clothing::getSellPrice).sum();
            System.out.println("Transaction amount: " + totalAmount + "€");

            client.checkout();
            store.checkout(client);
            System.out.println("Transaction made successfully");
        }
    }

    private static void viewPurchaseHistory(OnlineStore store) {
        Client client = (Client) store.getLoggedInUser();
        System.out.println("Transaction history: ");
        for (Clothing item : client.getPurchaseHistory()) {
            System.out.println("- " + item.getName() + " | " + item.getSellPrice() + "€");
        }
    }
}