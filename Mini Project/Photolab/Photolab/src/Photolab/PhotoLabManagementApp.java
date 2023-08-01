package Photolab;

import java.util.List;
import java.util.Scanner;

abstract class Operations {
    abstract User handleLogin(Scanner scanner);
    abstract User handleRegistration(Scanner scanner);
}

class Registration extends Operations {
    private UserDao userDao = new UserDaoImpl();

    @Override
    User handleLogin(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        return userDao.getUserByUsernameAndPassword(username, password);
    }

    @Override
    User handleRegistration(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(UserRole.USER);

        userDao.addUser(newUser);
        return newUser;
    }
}

class Crud extends Registration {
    private InventoryDao inventoryDao = new InventoryDaoImpl();

    protected void viewInventory() {
        List<InventoryItem> inventoryItems = inventoryDao.getAllItems();
        System.out.println("Inventory:");
        for (InventoryItem item : inventoryItems) {
            System.out.println(item.getId() + ". " + item.getItemName() + " - Quantity: " + item.getQuantity() + ", Price: " + item.getPrice());
        }
    }

    protected void addItemToInventory(Scanner scanner) {
        System.out.println("Enter item name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter item quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter item price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline left by nextDouble()

        InventoryItem newItem = new InventoryItem();
        newItem.setItemName(itemName);
        newItem.setQuantity(quantity);
        newItem.setPrice(price);

        inventoryDao.addItem(newItem);
        System.out.println("Item added to inventory.");
    }

    protected void updateItemInInventory(Scanner scanner) {
        System.out.println("Enter item ID to update:");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        InventoryItem itemToUpdate = inventoryDao.getItemById(itemId);
        if (itemToUpdate == null) {
            System.out.println("Item not found.");
            return;
        }

        System.out.println("Enter new item name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter new item quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter new item price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline left by nextDouble()

        itemToUpdate.setItemName(itemName);
        itemToUpdate.setQuantity(quantity);
        itemToUpdate.setPrice(price);

        inventoryDao.updateItem(itemToUpdate);
        System.out.println("Item updated in inventory.");
    }

    protected void deleteItemFromInventory(Scanner scanner) {
        System.out.println("Enter item ID to delete:");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left by nextInt()

        InventoryItem itemToDelete = inventoryDao.getItemById(itemId);
        if (itemToDelete == null) {
            System.out.println("Item not found.");
            return;
        }

        inventoryDao.deleteItem(itemId);
        System.out.println("Item deleted from inventory.");
    }
}

public class PhotoLabManagementApp extends Crud {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to Photo Lab Management System!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            User currentUser;
			switch (choice) {
                case 1:
                    currentUser = handleLogin(scanner);
                    if (currentUser != null) {
                        System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                        displayMainMenu(scanner);
                    } else {
                        System.out.println("Login failed. Please try again.");
                    }
                    break;
                case 2:
                    currentUser = handleRegistration(scanner);
                    if (currentUser != null) {
                        System.out.println("Registration successful. Welcome, " + currentUser.getUsername() + "!");
                        displayMainMenu(scanner);
                    } else {
                        System.out.println("Registration failed. Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 3);

        scanner.close();
    }

    private void displayMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Item to Inventory");
            System.out.println("3. Update Item in Inventory");
            System.out.println("4. Delete Item from Inventory");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            switch (choice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    addItemToInventory(scanner);
                    break;
                case 3:
                    updateItemInInventory(scanner);
                    break;
                case 4:
                    deleteItemFromInventory(scanner);
                    break;
                case 5:
                    System.out.println("Logged out. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        PhotoLabManagementApp app = new PhotoLabManagementApp();
        app.run();
    }
}




//package Photolab;
//import java.util.List;
//import java.util.Scanner;
//
//abstract class Operations
//{
//	Scanner scanner = new Scanner(System.in);
//	
//	abstract User handleLogin(Scanner scanner);
//	abstract User handleRegistration(Scanner scanner);
//}
//class Registration extends Operations{
//	Scanner scanner = new Scanner(System.in);
//	
//        UserDaoImpl userDao = new UserDaoImpl();
//        InventoryDaoImpl inventoryDao = new InventoryDaoImpl();
//    
//@Override
//User handleLogin(Scanner scanner) {
//        System.out.println("Enter username:");
//        String username = scanner.nextLine();
//        System.out.println("Enter password:");
//        String password = scanner.nextLine();
//
//        
//		User user = userDao.getUserByUsernameAndPassword(username, password);
//        return user;
//    }
//@Override User handleRegistration(Scanner scanner) {
//        System.out.println("Enter username:");
//        String username = scanner.nextLine();
//        System.out.println("Enter password:");
//        String password = scanner.nextLine();
//
//        User newUser = new User();
//        newUser.setUsername(username);
//        newUser.setPassword(password);
//        newUser.setRole(UserRole.USER);
//
//        UserDaoImpl userDao;
//		userDao.addUser(newUser);
//        return newUser;
//    }
//
//	
//
//   
//}
// class Crud extends Registration
// {
//	 Scanner scanner = new Scanner(System.in);
//    protected void viewInventory() {
//        List<InventoryItem> inventoryItems = inventoryDao.getAllItems();
//        System.out.println("Inventory:");
//        for (InventoryItem item : inventoryItems) {
//            System.out.println(item.getId() + ". " + item.getItemName() + " - Quantity: " + item.getQuantity() + ", Price: " + item.getPrice());
//        }
//    }
//
//    protected void addItemToInventory(Scanner scanner) {
//        System.out.println("Enter item name:");
//        String itemName = scanner.nextLine();
//        System.out.println("Enter item quantity:");
//        int quantity = scanner.nextInt();
//        System.out.println("Enter item price:");
//        double price = scanner.nextDouble();
//        scanner.nextLine(); // Consume the newline left by nextDouble()
//
//        InventoryItem newItem = new InventoryItem();
//        newItem.setItemName(itemName);
//        newItem.setQuantity(quantity);
//        newItem.setPrice(price);
//
//        inventoryDao.addItem(newItem);
//        System.out.println("Item added to inventory.");
//    }
//
//
//    protected void updateItemInInventory(Scanner scanner) {
//        System.out.println("Enter item ID to update:");
//        int itemId = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline left by nextInt()
//
//        InventoryItem itemToUpdate = inventoryDao.getItemById(itemId);
//        if (itemToUpdate == null) {
//            System.out.println("Item not found.");
//            return;
//        }
//
//        System.out.println("Enter new item name:");
//        String itemName = scanner.nextLine();
//        System.out.println("Enter new item quantity:");
//        int quantity = scanner.nextInt();
//        System.out.println("Enter new item price:");
//        double price = scanner.nextDouble();
//        scanner.nextLine(); // Consume the newline left by nextDouble()
//
//        itemToUpdate.setItemName(itemName);
//        itemToUpdate.setQuantity(quantity);
//        itemToUpdate.setPrice(price);
//
//        inventoryDao.updateItem(itemToUpdate);
//        System.out.println("Item updated in inventory.");
//    }
//
//
//    protected void deleteItemFromInventory(Scanner scanner) {
//        System.out.println("Enter item ID to delete:");
//        int itemId = scanner.nextInt();
//        scanner.nextLine(); // Consume the newline left by nextInt()
//
//        InventoryItem itemToDelete = inventoryDao.getItemById(itemId);
//        if (itemToDelete == null) {
//            System.out.println("Item not found.");
//            return;
//        }
//
//        inventoryDao.deleteItem(itemId);
//        System.out.println("Item deleted from inventory.");
//    }
//
// }
// class PhotoLabManagementApp extends Crud
// {
//	 Scanner scanner = new Scanner(System.in);
//	 private UserDao userDao;
//	    private InventoryDao inventoryDao;
//	    private User currentUser;
//Crud c=new Crud();
//	    public PhotoLabManagementApp() {
//	        userDao = new UserDaoImpl();
//	        inventoryDao = new InventoryDaoImpl();
//	    }
//
//	    public void run() {
//	        Scanner scanner = new Scanner(System.in);
//	        int choice;
//
//	        do {
//	            System.out.println("Welcome to Photo Lab Management System!");
//	            System.out.println("1. Login");
//	            System.out.println("2. Register");
//	            System.out.println("3. Exit");
//
//	            choice = scanner.nextInt();
//	            scanner.nextLine(); // Consume the newline left by nextInt()
//
//	            switch (choice) {
//	                case 1:
//	                    currentUser = handleLogin(scanner);
//	                    if (currentUser != null) {
//	                        System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
//	                        displayMainMenu(scanner);
//	                    } else {
//	                        System.out.println("Login failed. Please try again.");
//	                    }
//	                    break;
//	                case 2:
//	                    currentUser = handleRegistration(scanner);
//	                    if (currentUser != null) {
//	                        System.out.println("Registration successful. Welcome, " + currentUser.getUsername() + "!");
//	                        displayMainMenu(scanner);
//	                    } else {
//	                        System.out.println("Registration failed. Please try again.");
//	                    }
//	                    break;
//	                case 3:
//	                    System.out.println("Goodbye!");
//	                    break;
//	                default:
//	                    System.out.println("Invalid choice!");
//	            }
//	        } while (choice != 3);
//	    }
//    public static void main(String[] args) {
//        PhotoLabManagementApp app = new PhotoLabManagementApp();
//        app.run();
//    }
//    private void displayMainMenu(Scanner scanner) {
//        while (true) {
//            System.out.println("Main Menu:");
//            System.out.println("1. View Inventory");
//            System.out.println("2. Add Item to Inventory");
//            System.out.println("3. Update Item in Inventory");
//            System.out.println("4. Delete Item from Inventory");
//            System.out.println("5. Logout");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume the newline left by nextInt()
//
//            switch (choice) {
//                case 1:
//                    c.viewInventory();
//                    break;
//                case 2:
//                    c.addItemToInventory(scanner);
//                    break;
//                case 3:
//                    c.updateItemInInventory(scanner);
//                    break;
//                case 4:
//                    c.deleteItemFromInventory(scanner);
//                    break;
//                case 5:
//                    System.out.println("Logged out. Goodbye!");
//                    return;
//                default:
//                    System.out.println("Invalid choice!");
//            }
//        }
//    }
//}
