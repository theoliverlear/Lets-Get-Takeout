import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

// Create TakeOutSimulator class here
public class TakeOutSimulator {
    private Customer customer;
    private FoodMenu menu = new FoodMenu();
    Scanner input = new Scanner(System.in);
    public TakeOutSimulator(Customer customer, Scanner input) {
        this.customer = customer;
        this.input = input;
    }
    private <T> T getOutputOnIntInput(String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever) {
        System.out.println(userInputPrompt);
        T temp_T = null;
        int userInt = input.nextInt();
        try {
            temp_T = intUserInputRetriever.produceOutputOnIntUserInput(userInt);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Illegal Argument Exception. " + userInt + " is unacceptable.");
        }
        return temp_T;
    }

    public boolean shouldSimulate() {
        String userPrompt = ("Please input 1 to proceed or 0 to terminate. ");
        boolean[] shouldSim = new boolean[1];
        IntUserInputRetriever<?> intUserInputRetriever = (int selection) -> {
            if(selection == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()){
                System.out.println(selection + ": All requirements met - let's continue.");
                shouldSim[0] = true;
            } else if (selection == 1 && customer.getMoney() < menu.getLowestCostFood().getPrice()) {
                System.out.println(selection + ": Requirement of sufficient funds not met - terminating.");
                shouldSim[0] = false;
            } else if(selection == 0) {
                System.out.println(selection + ": Terminating...");
                shouldSim[0] = false;
            } else {
                throw new IllegalArgumentException(selection + ": Requirements not met - terminating. Try again.");
            }
            return shouldSim[0];};
        getOutputOnIntInput(userPrompt, intUserInputRetriever);
        return shouldSim[0];
    }
    public Food getMenuSelection() {
        String userPrompt = "Please choose which number meal to order: " + menu.toString();
        Food[] tempFood = new Food[1];
        IntUserInputRetriever<?> intUserInputRetriever = (selection) -> {
            if(menu.getFood(selection) != null) {
                tempFood[0] = menu.getFood(selection);
            } else {
                System.err.println("Illegal Argument Exception.");
            }
            return tempFood[0];
        };
        getOutputOnIntInput(userPrompt,intUserInputRetriever);
        return tempFood[0];
    }
    public boolean isStillOrderingFood() throws IllegalArgumentException {
        String userPrompt = "Please enter 1 to continue shopping or 0 to checkout.";
        boolean[] orderingFood = new boolean[1];
        IntUserInputRetriever<?> intUserInputRetriever = (selection) -> {
            if(selection == 1) {
                orderingFood[0] = true;
            }else if (selection == 0) {
                orderingFood[0] = false;
            }
            return orderingFood[0];
        };
        getOutputOnIntInput(userPrompt, intUserInputRetriever);
        return orderingFood[0];
    }
    public void checkoutCustomer(ShoppingBag<Food> shoppingBag) {
        System.out.println("Your payment is processing...");
        customer.setMoney(customer.getMoney() - shoppingBag.getTotalPrice());
        //customer.setMoney(shoppingBag.getTotalPrice());
        System.out.println("You currently have " + customer.getMoney() + " dollars.");
        System.out.println("Thank you - enjoy your meal!");
    }
    void takeOutPrompt() {
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        while(isStillOrderingFood()) {
            System.out.println("You currently have " + customerMoneyLeft + " dollars.");
            Food tempFood = getMenuSelection();
            if(customerMoneyLeft - tempFood.getPrice().intValue() >= 0) {
                customerMoneyLeft = customerMoneyLeft - tempFood.getPrice().intValue();
                shoppingBag.addItem(tempFood);
                //isStillOrderingFood();
            } else {
                System.out.println("You do not have enough money. Please choose a different item or checkout with the item.");
                // isStillOrderingFood();
            }
        }
        checkoutCustomer(shoppingBag);
    }
    public void startTakeOutSimulator() {
        System.out.println("Welcome to your take-out food portal!");
        while(shouldSimulate()){
            System.out.println("Hello " + customer.getName() + "!");
            takeOutPrompt();
        }

    }
}