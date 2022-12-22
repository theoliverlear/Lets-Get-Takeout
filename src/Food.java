// Create Food class here
public class Food implements PricedItem <Integer> {
    private String name;
    private String description;
    private Integer price;
    public Food(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }
    @Override
    public Integer getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return "Enjoy " + name + ": " + description + "    Cost: $" + price;
    }
}