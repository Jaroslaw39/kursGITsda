package codecademy;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBag<T extends PricedItem<Integer>> {

    private Map<T, Integer> shoppingBag;

    public ShoppingBag() {
        this.shoppingBag = new HashMap<>();
    }

    public void addItem(T item) {

        if (shoppingBag.containsKey(item)) {
            Integer currentQuantity = shoppingBag.get(item);
            shoppingBag.put(item, currentQuantity + 1);
        } else {
            shoppingBag.put(item, 1);
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;

        for(T items : shoppingBag.keySet()) {

            int itemPrice = items.getPrice();
            int quantityOfItem = shoppingBag.get(items);
            int totalPriceOfItem = itemPrice * quantityOfItem;

            totalPrice = totalPrice + totalPriceOfItem;

        }

        return totalPrice;
    }
}
