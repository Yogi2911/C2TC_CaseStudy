package com.tnsif.fooddelivery;

import java.util.*;

public class FoodDeliveryApp {
    static Scanner sc = new Scanner(System.in);
    static FoodService foodService = new FoodService();
    static CustomerService customerService = new CustomerService();
    static OrderService orderService = new OrderService();
    static Map<Integer, DeliveryPerson> deliveryPeople = new HashMap<>();
    static Customer currentCustomer = null;

    public static void main(String[] args) {
        ConsoleDesign.printLogo();
        initializeData();

        while (true) {
            ConsoleDesign.printHeader("MAIN MENU");
            ConsoleDesign.printMenuItem(1, "Admin Panel");
            ConsoleDesign.printMenuItem(2, "Customer Panel");
            ConsoleDesign.printMenuItem(3, "Exit");
            System.out.print("\nChoice: ");
            
            int choice = getInt();
            if (choice == 1) adminMenu();
            else if (choice == 2) customerMenu();
            else if (choice == 3) { ConsoleDesign.printProgressLogo(); break; }
        }
    }

    static void initializeData() {
        Restaurant r1 = new Restaurant(101, "HariOm Dhaba");
        r1.addFoodItem(new FoodItem(1, "Punjabi Thali", 340, true));
        r1.addFoodItem(new FoodItem(2, "Pav Bhaji", 140, true));
        r1.addFoodItem(new FoodItem(3, "Butter Chicken", 280, false));
        
        Restaurant r2 = new Restaurant(102, "Express Inn");
        r2.addFoodItem(new FoodItem(4, "Biryani", 250, false));
        r2.addFoodItem(new FoodItem(5, "Veg Biryani", 200, true));
        
        foodService.addRestaurant(r1);
        foodService.addRestaurant(r2);
        
        deliveryPeople.put(1, new DeliveryPerson(1, "Manoj", 7087990078L));
        deliveryPeople.put(2, new DeliveryPerson(2, "Rajesh", 9876543210L));
    }

    static void adminMenu() {
        while (true) {
            ConsoleDesign.printHeader("ADMIN");
            ConsoleDesign.printMenuItem(1, "Add Restaurant");
            ConsoleDesign.printMenuItem(2, "Add Food Item");
            ConsoleDesign.printMenuItem(3, "Remove Food Item");
            ConsoleDesign.printMenuItem(4, "View Menus");
            ConsoleDesign.printMenuItem(5, "View Orders");
            ConsoleDesign.printMenuItem(6, "Add Delivery Person");
            ConsoleDesign.printMenuItem(7, "Assign Delivery");
            ConsoleDesign.printMenuItem(8, "Back");
            
            int ch = getInt();
            if (ch == 1) addRestaurant();
            else if (ch == 2) addFoodItem();
            else if (ch == 3) removeFoodItem();
            else if (ch == 4) foodService.printMenus();
            else if (ch == 5) orderService.printOrders();
            else if (ch == 6) addDeliveryPerson();
            else if (ch == 7) assignDelivery();
            else if (ch == 8) return;
        }
    }

    static void addRestaurant() {
        System.out.print("Restaurant ID: "); int id = getInt();
        System.out.print("Name: "); String name = sc.nextLine();
        foodService.addRestaurant(new Restaurant(id, name));
        ConsoleDesign.printSuccess("Added!");
    }

    static void addFoodItem() {
        System.out.print("Restaurant ID: "); int rid = getInt();
        Restaurant r = foodService.getRestaurant(rid);
        if (r == null) { ConsoleDesign.printError("Not found!"); return; }
        
        System.out.print("Food ID: "); int fid = getInt();
        System.out.print("Name: "); String fname = sc.nextLine();
        System.out.print("Price: "); double price = getDouble();
        System.out.print("Veg (true/false): "); boolean veg = getBoolean();
        
        r.addFoodItem(new FoodItem(fid, fname, price, veg));
        ConsoleDesign.printSuccess("Food added!");
    }

    static void removeFoodItem() {
        System.out.print("Restaurant ID: "); int rid = getInt();
        Restaurant r = foodService.getRestaurant(rid);
        if (r == null) { ConsoleDesign.printError("Not found!"); return; }
        
        System.out.print("Food ID: "); int fid = getInt();
        r.removeFoodItem(fid);
        ConsoleDesign.printSuccess("Removed!");
    }

    static void addDeliveryPerson() {
        System.out.print("ID: "); int id = getInt();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Contact: "); long contact = getLong();
        deliveryPeople.put(id, new DeliveryPerson(id, name, contact));
        ConsoleDesign.printSuccess("Added!");
    }

    static void assignDelivery() {
        orderService.printOrders();
        System.out.print("Order ID: "); int oid = getInt();
        System.out.print("Delivery Person ID: "); int dpid = getInt();
        
        Order o = orderService.getOrder(oid);
        DeliveryPerson dp = deliveryPeople.get(dpid);
        if (o != null && dp != null) {
            o.assignDelivery(dp);
            ConsoleDesign.printSuccess("Assigned!");
        } else ConsoleDesign.printError("Invalid IDs!");
    }

    static void customerMenu() {
        while (true) {
            ConsoleDesign.printHeader("CUSTOMER");
            ConsoleDesign.printMenuItem(1, "Login/Register");
            ConsoleDesign.printMenuItem(2, "View Menus");
            ConsoleDesign.printMenuItem(3, "Add to Cart");
            ConsoleDesign.printMenuItem(4, "View Cart");
            ConsoleDesign.printMenuItem(5, "Place Order");
            ConsoleDesign.printMenuItem(6, "My Orders");
            ConsoleDesign.printMenuItem(7, "Recommendations");
            ConsoleDesign.printMenuItem(8, "Back");
            
            int ch = getInt();
            if (ch == 1) addCustomer();
            else if (ch == 2) foodService.printMenus();
            else if (ch == 3) addToCart();
            else if (ch == 4) viewCart();
            else if (ch == 5) placeOrder();
            else if (ch == 6) viewOrders();
            else if (ch == 7) getRecommendations();
            else if (ch == 8) { currentCustomer = null; return; }
        }
    }

    static void addCustomer() {
        System.out.print("User ID: "); int uid = getInt();
        Customer c = customerService.getCustomer(uid);
        if (c != null) { currentCustomer = c; ConsoleDesign.printSuccess("Welcome " + c.getUsername()); return; }
        
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Contact: "); long contact = getLong();
        currentCustomer = new Customer(uid, name, contact);
        customerService.addCustomer(currentCustomer);
        ConsoleDesign.printSuccess("Registered!");
    }

    static void addToCart() {
        if (currentCustomer == null) { ConsoleDesign.printError("Login first!"); return; }
        
        foodService.printMenus();
        System.out.print("Restaurant ID: "); int rid = getInt();
        System.out.print("Food ID: "); int fid = getInt();
        System.out.print("Quantity: "); int qty = getInt();
        
        Restaurant r = foodService.getRestaurant(rid);
        if (r == null) { ConsoleDesign.printError("Restaurant not found!"); return; }
        
        for (FoodItem f : r.getMenu()) {
            if (f.getId() == fid) {
                currentCustomer.getCart().addItem(f, qty);
                ConsoleDesign.printSuccess("Added to cart!");
                return;
            }
        }
        ConsoleDesign.printError("Food not found!");
    }

    static void viewCart() {
        if (currentCustomer == null) { ConsoleDesign.printError("Login first!"); return; }
        System.out.println(currentCustomer.getCart());
    }

    static void placeOrder() {
        if (currentCustomer == null) { ConsoleDesign.printError("Login first!"); return; }
        if (currentCustomer.getCart().getItems().isEmpty()) { 
            ConsoleDesign.printError("Cart empty!"); return; 
        }
        
        System.out.print("Delivery Address: "); String addr = sc.nextLine();
        Order o = orderService.placeOrder(currentCustomer, addr);
        ConsoleDesign.printSuccess("Order placed! ID: " + o.getOrderId());
    }

    static void viewOrders() {
        if (currentCustomer == null) { ConsoleDesign.printError("Login first!"); return; }
        List<Order> orders = orderService.getOrdersByCustomer(currentCustomer);
        if (orders.isEmpty()) System.out.println("No orders yet!");
        else orders.forEach(System.out::println);
    }

    static void getRecommendations() {
        System.out.print("Budget: "); double budget = getDouble();
        System.out.println("1.Veg 2.Non-Veg 3.All");
        int pref = getInt();
        Boolean veg = pref == 1 ? true : pref == 2 ? false : null;
        RecommendationService.recommend(foodService.getAllRestaurants(), budget, veg);
    }

    static int getInt() {
        while (true) {
            try { int v = sc.nextInt(); sc.nextLine(); return v; }
            catch (Exception e) { sc.nextLine(); System.out.print("Invalid! Try: "); }
        }
    }

    static double getDouble() {
        while (true) {
            try { double v = sc.nextDouble(); sc.nextLine(); return v; }
            catch (Exception e) { sc.nextLine(); System.out.print("Invalid! Try: "); }
        }
    }

    static long getLong() {
        while (true) {
            try { long v = sc.nextLong(); sc.nextLine(); return v; }
            catch (Exception e) { sc.nextLine(); System.out.print("Invalid! Try: "); }
        }
    }

    static boolean getBoolean() {
        while (true) {
            try { boolean v = sc.nextBoolean(); sc.nextLine(); return v; }
            catch (Exception e) { sc.nextLine(); System.out.print("Invalid! Try: "); }
        }
    }
}