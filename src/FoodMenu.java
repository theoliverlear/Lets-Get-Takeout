import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
// Create FoodMenu class here
public class FoodMenu {
    private List<Food> menu = new ArrayList<>();
    public FoodMenu() {
        menu.add(new Food("Cheeseburger", "Classic American Cheeseburger", 10));
        menu.add(new Food("Caesar Salad", "Authentic Caesar Salad", 12));
        menu.add(new Food("Tomato Soup", "Red Vineyard Tomato Soup", 8));
    }
    @Override
    public String toString() {
        String printMenu = null;
        int i = 1;
        for(Food food : menu) {
            if(printMenu == null) {
                printMenu = "\n" + i + ". " + food.toString();
            } else {
                printMenu = printMenu + "\n" + i + ". " + food.toString();
            }
            i++;
        }
        return printMenu;
    }
    public Food getFood(int index) {
        if(index <= menu.size()) {
            return menu.get(index - 1);
        } else {
            return null;
        }
    }
    public Food getLowestCostFood() {
        Food currentFood = menu.get(0);
        for(Food food : menu) {
            if(currentFood.getPrice() > food.getPrice()) {
                currentFood = food;
            }
        }
        return currentFood;
    }
}