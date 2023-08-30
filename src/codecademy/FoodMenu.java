package codecademy;

import java.util.ArrayList;
import java.util.List;

public class FoodMenu {

    private List<Food> menu;

    public FoodMenu(){

        this.menu = new ArrayList<>();

        menu.add(new Food("Burger",
                "Sandwich with beef cutlet, 2 slices of tomatoes, onion, ketchup and mustard", 15 ));
        menu.add(new Food("Pizza margherita",
                " Pizza with tomato sauce and cheese", 22 ));
        menu.add(new Food("Steak",
                " Pork steak served with french fries and grilled vegetables", 105 ));
        menu.add(new Food("Greek salad",
                " Salad with feta cheese and olives ", 10 ));


    }

    @Override
    public String toString() {

        int listPosition = 1;
        String returnString = "";

        for(Food food : menu){
            returnString += listPosition + ". " + food.toString() + "\n";
            listPosition++;
        }

        return returnString;
    }

    public Food getFood(int index){
        try{
        int adjustIndex = index - 1;

        if (adjustIndex >= 0 && adjustIndex < menu.size()){
            return menu.get(adjustIndex);
        } else {
            return null;
        }
            } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Food getLowestCostFood() {

        Food cheapestFood = menu.get(0);
        for( Food food : menu){
            if ( cheapestFood.getPrice() > food.getPrice() ){

                cheapestFood = food;
            }
        }
        return cheapestFood;
    }
}
