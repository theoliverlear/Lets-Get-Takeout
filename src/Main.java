import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String customerName = input.nextLine();
        System.out.println("Hello " + customerName + ". How much do you have to spend today?");
        int money = 0;
        try {
            money = input.nextInt();
        } catch (IllegalArgumentException e) {
            {
                System.out.println("Please enter a valid whole number.");
            }
        }
        Customer customer = new Customer(customerName, money);
        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, input);
        takeOutSimulator.startTakeOutSimulator();
    }
}
