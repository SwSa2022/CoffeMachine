package de.msgdavid.coffeemachinenew;

import java.util.Scanner;

import static de.msgdavid.coffeemachinenew.CoffeeMachineFunction.WriteAction;
import static de.msgdavid.coffeemachinenew.Main.scan;

class CoffeeMachine {
    static int waterAmount = 400;
    static int coffeeAmount = 120;
    static int milkAmount = 540;
    static int cupsAmount = 9;
    static int money = 550;

    public CoffeeMachine() {
    }

    public static String choose(String str) {
        str = scan.nextLine();
        return str;
    }
}

interface Getraenke {
    static int getWater(int water) {
        return water;
    }

    static int getMilk(int milk) {
        return milk;
    }

    static int getCoffee(int coffee) {
        return coffee;
    }
}

class Espresso implements Getraenke {
    int water = 250;
    int coffee = 16;
}

class Latte implements Getraenke {

    int water = 350;
    int milk = 75;
    int coffee = 20;
}

class Cappuccino implements Getraenke {

    int water = 200;
    int milk = 100;
    int coffee = 12;
}

class CoffeeMachineFunction extends CoffeeMachine {

    public static void BuyCoffee() {
        Cappuccino cap = new Cappuccino();
        Latte lat = new Latte();
        Espresso esp = new Espresso();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - Main menu:");
        String select = "";
        select = choose(select);
        scan.useDelimiter("\n");

        switch (select) {
            case "1":
                IngredientCompare();
                if (waterAmount > esp.water && coffeeAmount > esp.coffee && cupsAmount > 0) {
                    waterAmount -= esp.water;
                    coffeeAmount -= esp.coffee;
                    money += 4;
                    cupsAmount -= 1;
                }
                break;

            case "2":
                IngredientCompare();
                if (waterAmount > lat.water && coffeeAmount > lat.coffee && milkAmount > lat.milk && cupsAmount > 0) {
                    waterAmount -= lat.water;
                    coffeeAmount -= lat.coffee;
                    money += 7;
                    milkAmount -= lat.milk;
                    cupsAmount -= 1;
                }
                break;

            case "3":
                IngredientCompare();
                if (waterAmount > cap.water && coffeeAmount > cap.coffee
                        && milkAmount > cap.milk && cupsAmount > 0) {
                    waterAmount -= cap.water;
                    coffeeAmount -= cap.coffee;
                    money += 6;
                    milkAmount -= cap.milk;
                    cupsAmount -= 1;
                } else {
                    break;
                }

            case "back":
                break;
        }

    }

    private static void IngredientCompare() {

        Cappuccino cap = new Cappuccino();
        Latte lat = new Latte();
        Espresso esp = new Espresso();

        if (waterAmount / cap.water < 1 || waterAmount / esp.water < 1
                || waterAmount / lat.water < 1) {
            System.out.println("Sorry, not enough water!");
        } else if (milkAmount / cap.milk < 1 || milkAmount / lat.milk < 1) {
            System.out.println("Sorry, not enough milk!");
        } else if (coffeeAmount / cap.coffee < 1 || coffeeAmount / esp.coffee < 1
                || coffeeAmount / lat.coffee < 1) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cupsAmount == 0) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    public static void FillIngredients() {
        System.out.println("Write how many ml of water you want to add: ");
        int w = scan.nextInt();
        waterAmount += w;
        System.out.println("Write how many ml of milk you want to add: ");
        int m = scan.nextInt();
        milkAmount += m;
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int c = scan.nextInt();
        coffeeAmount += c;
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int addCup = scan.nextInt();
        cupsAmount += addCup;
        scan.useDelimiter("\n");
        scan.nextLine();
    }

    public static void TakeMoney() {
        System.out.println("I gave you $" + money + "\n");
        money -= money;
    }

    public static void Remaining() {
        System.out.println("The coffee machine has:\n" + waterAmount + " ml of water\n" + milkAmount + " ml of milk\n"
                + coffeeAmount + " g of coffee beans\n" + cupsAmount + " disposable cups\n$" + money + " of money");
    }


    public static void WriteAction() {
        boolean run = true;

        while (run) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit): ");
            String str = "";

            switch (ChooseFunction.valueOf(choose(str.toUpperCase()))) {
                // BUY COFFEE
                case BUY:
                    BuyCoffee();
                    break;

                // FILL INGREDIENTS
                case FILL:
                    FillIngredients();
                    break;

                // TAKE MONEY
                case TAKE:
                    TakeMoney();
                    break;

                // REMAINING
                case REMAINING:
                    Remaining();
                    break;
                // EXIT
                case EXIT:
                    run = false;
                    break;
            }
        }
    }

    enum ChooseFunction {
        BUY, FILL, TAKE, REMAINING, EXIT

    }

}

public class Main {
    final static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        WriteAction();
    }
}

