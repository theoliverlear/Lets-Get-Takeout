import java.util.HashMap;
import java.util.Map;

// Create ShoppingBag class here
public class ShoppingBag <T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag = new HashMap<T, Integer>();

    public ShoppingBag() {
        Map<T, Integer> shoppingBag = new HashMap<T, Integer>();
    }
    //    public void addItem(T item) {
//        Integer i = 1;
//        shoppingBag.putIfAbsent(item, i);
//        i++;
//    }
    public void addItem(T item) {
        if(shoppingBag.get(item) == null) {
            shoppingBag.put(item, item.getPrice());
        } else if(shoppingBag.containsKey(item)) {
            shoppingBag.put(item, item.getPrice() * 2);
        }
    }
    public int getTotalPrice() {
        int sum = 0;
        for(Map.Entry<T, Integer> item : shoppingBag.entrySet()) {
            sum = sum + item.getValue().intValue();
        }
        return sum;
    }
}