package codecademy;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


    Scanner input = new Scanner(System.in);
        System.out.println("What's your name?");
    String customerName = input.nextLine();
    int money = 0;
    boolean validInput = true;

    while(validInput){
        System.out.println("Whats you starting money?");

        try{
            money = input.nextInt();
            if(money <= 0){
                throw new InputMismatchException();
            }
            validInput = false;
        }catch (InputMismatchException e){
            System.out.println("Invalid input! Please pass valid input");

            input.nextLine();
        }
    }


    Customer customer = new Customer(customerName,money);
    TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, input );
    takeOutSimulator.startTakeoutSimulator();

    }

}