package codecademy;

import java.util.Scanner;

public class TakeOutSimulator {

    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, Scanner input) {
        this.customer = customer;
        this.menu = new FoodMenu();
        this.input = input;
    }

    private <T> T getOutputOnIntInput (String userInputPrompt, IntUserInputRetriever<T> intUserInputRetriever){

        while(true){
            System.out.println(userInputPrompt);

            if(input.hasNextInt()){
            int userInput = input.nextInt();

            try {
                return intUserInputRetriever.produceOutputOnIntUserInput(userInput);
            } catch (IllegalArgumentException e){

                System.out.println(userInput + " is not valid input! Try again!");
            }
            }else {
                System.out.println("Input must be an integer!");

            }

            }
        }

    public boolean shouldSimulate() {
        String userPrompt = "Enter \"1\" for continue program or pass \"0\" for quit";

        //klasa anonimowa - sprawdzić!
        IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {
            if (selection == 1 && customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                return true;
            } else if (selection == 0) {
                System.out.println("Thank you for ordering!");
                return false;
            } else
                throw new IllegalArgumentException();


        };

            return this.getOutputOnIntInput(userPrompt, intUserInputRetriever);

        }

        public Food getMenuSelection(){
        String userPrompt = "Choose number of menu: ";
            System.out.println(menu);

            IntUserInputRetriever<Food> intUserInputRetriever = selection -> {
                if(menu.getFood(selection) != null){
                    return menu.getFood(selection);
                }else {
                    throw new IllegalArgumentException();
                }
            };
            return getOutputOnIntInput(userPrompt, intUserInputRetriever);
        }

        public boolean isStillOrderingFood(){

        String userPrompt = "Select \"1\" for continue shopping or \"0\" for end shopping and checkout";

        IntUserInputRetriever<Boolean> intUserInputRetriever = selection -> {

            if(selection == 1){
                return true;
            } else if (selection == 0) {
                return false;
            }else {
                throw new IllegalArgumentException();
            }
        };
            return getOutputOnIntInput(userPrompt,intUserInputRetriever);
        }

        public void checkoutCustomer(ShoppingBag<Food> shoppingBag){
            System.out.println("Payment is processing");

            int moneyLeft = customer.getMoney() - shoppingBag.getTotalPrice();
            customer.setMoney(moneyLeft);

            System.out.println("Your remaining money is: " + customer.getMoney() +" $");
            System.out.println("Thank you and enjoy your food!");

        }

        public void takeoutPrompt(){

        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        boolean stillOrdering = true;

        while(stillOrdering){
            System.out.println("You have " + customerMoneyLeft + " money left to spend");
            Food food = this.getMenuSelection();

            if(customerMoneyLeft >= food.getPrice()){
                shoppingBag.addItem(food);
                customerMoneyLeft -= food.getPrice();

            } else {
                System.out.println("Sorry you don't have enough money!");
            }


            stillOrdering = this.isStillOrderingFood();
            if(stillOrdering == false){
                checkoutCustomer(shoppingBag);
            }
        }
        }

        public void startTakeoutSimulator(){

        boolean continueSimulation = true;

        while(continueSimulation){

            System.out.println("Welcome in our restaurant!");
            System.out.println("Welcome " + customer.getName());
            this.takeoutPrompt();
            continueSimulation = this.shouldSimulate();
        }
        }

    }



