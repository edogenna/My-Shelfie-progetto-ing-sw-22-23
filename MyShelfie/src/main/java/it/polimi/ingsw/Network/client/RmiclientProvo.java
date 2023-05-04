import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class RmiclientProvo {
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static final String REMOTE_OBJ_NAME = "Store";

    @Override
    public String toString() {
        return "RmiclientProvo{}";
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            Store store = (Store) registry.lookup(REMOTE_OBJ_NAME);
            Scanner scanner = new Scanner(System.in);
            boolean quit = false;

            while (!quit) {
                System.out.println("Add item to cart");
                System.out.println("Remove item from cart");
                System.out.println("Checkout");
                System.out.println("Quit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter item name: ");
                        String itemName = scanner.next();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        store.addItemToCart(itemName, quantity);
                        System.out.println(itemName + " added to cart.");
                        break;

                    case 2:
                        System.out.print("Enter item name: ");
                        String itemToRemove = scanner.next();
                        store.removeItemFromCart(itemToRemove);
                        System.out.println(itemToRemove + " removed from cart.");
                        break;

                    case 3:
                        double totalPrice = store.checkout();
                        System.out.println("Total price: $" + totalPrice);
                        break;

                    case 4:
                        quit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                        break;
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
