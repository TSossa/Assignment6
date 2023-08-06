package real6;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.Scanner;
import java.util.InputMismatchException;

public class BevShopDriverApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BevShop bevShop = new BevShop();

        System.out.println("Welcome to the Beverage Shop!");
        System.out.println("The current order in process can have at most 3 alcoholic beverages.");
        System.out.println("The minimum age to order alcohol drink is 21\n");

        double totalAmount = 0; // To keep track of the total amount for all orders

        while (true) {
            System.out.println("Start please a new order:");
            bevShop.startNewOrder(0, DAY.MONDAY, "", 0); // Placeholder values

            String name = getStringInput(scanner, "Would you please enter your name: ");
            int age = getIntInput(scanner, "Would you please enter your age: ");

            if (age >= 21) {
                System.out.println("Your age is above 20 and you are eligible to order alcohol");
                int alcoholCount = 0;
                while (alcoholCount < 3 && bevShop.eligibleForMore()) {
                    System.out.println("Would you please add an alcohol drink");
                    bevShop.processAlcoholOrder("Alcohol Drink", SIZE.MEDIUM);
                    alcoholCount++;
                    System.out.println("The current order of drinks is " + bevShop.getCurrentOrder().getTotalItems());
                    System.out.println("The Total price on the Order: " + bevShop.totalOrderPrice(bevShop.getCurrentOrder().getOrderNo()));
                }
            } else {
                System.out.println("Your Age is not appropriate for an alcohol drink!!");
                String smoothieChoice = getStringInput(scanner, "Would you like to add a smoothie? (yes/no): ");
                if (smoothieChoice.equalsIgnoreCase("yes")) {
                    String fruitChoice = getStringInput(scanner, "Enter the number of fruits for the smoothie: ");
                    int numFruits = Integer.parseInt(fruitChoice);
                    String proteinChoice = getStringInput(scanner, "Would you like to add protein? (yes/no): ");
                    boolean addProtein = proteinChoice.equalsIgnoreCase("yes");
                    bevShop.processSmoothieOrder("Smoothie", SIZE.MEDIUM, numFruits, addProtein);
                } else {
                    String coffeeChoice = getStringInput(scanner, "Would you like to add coffee? (yes/no): ");
                    if (coffeeChoice.equalsIgnoreCase("yes")) {
                        String extraShotChoice = getStringInput(scanner, "Would you like to add extra shot? (yes/no): ");
                        boolean addExtraShot = extraShotChoice.equalsIgnoreCase("yes");
                        String extraSyrupChoice = getStringInput(scanner, "Would you like to add extra syrup? (yes/no): ");
                        boolean addExtraSyrup = extraSyrupChoice.equalsIgnoreCase("yes");
                        bevShop.processCoffeeOrder("Coffee", SIZE.MEDIUM, addExtraShot, addExtraSyrup);
                    }
                }
                System.out.println("Total items on your order is " + bevShop.getCurrentOrder().getTotalItems());
                System.out.println("The Total Price on the Order: " + bevShop.totalOrderPrice(bevShop.getCurrentOrder().getOrderNo()));
            }

            System.out.println("#------------------------------------#");
            double orderTotal = bevShop.totalOrderPrice(bevShop.getCurrentOrder().getOrderNo());
            totalAmount += orderTotal; // Add the current order total to the total amount
            System.out.println("Total price on the current Order: " + orderTotal);
            
            // Check if the order is a second order and display the total price on the second order
            if (totalAmount > 0) {
                System.out.println("Total price on the second Order: " + totalAmount);
                System.out.println("Total amount for all Orders: " + totalAmount);
            }
        }
    }

    private static String getStringInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private static int getIntInput(Scanner scanner, String message) {
        int input = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(message);
                input = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            } finally {
                scanner.nextLine(); // Consume the newline character
            }
        }

        return input;
    }
}
