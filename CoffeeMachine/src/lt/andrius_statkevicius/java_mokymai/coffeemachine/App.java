package lt.andrius_statkevicius.java_mokymai.coffeemachine;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) {

        CoffeeMachine coffeeMachine = new CoffeeMachine( 5, 20, 100, 50 );

        Service service = new Service();

        boolean run = true;

        int amountOfInsertedMaterial;
        int menuIndex;

        while (run) {

            System.out.println(service.printMainMenu());
            menuIndex = validateAndReturnUserInput( 0, 4 );

            switch (menuIndex) {
                case 0:
                    run = false;
                    break;
                case 1:
                    System.out.println(service.printDrinksMenu());
                    menuIndex=validateAndReturnUserInput(0,4);
                    if (menuIndex==0) {
                        continue;
                    }
                    System.out.println(service.selectProductByUserInput(coffeeMachine, menuIndex));
                    continue;
                case 2:
                    System.out.println(service.returnStatus(coffeeMachine));
                    System.out.println( "Press 0 to return to the main menu: ");
                    validateUserInput( 0 );
                    continue;
                case 3:
                    System.out.println(service.printRefillOptions());
                    menuIndex=validateAndReturnUserInput(0,4);
                    if (menuIndex != 0) {
                        while (true) {
                            try {
                                System.out.println( "Please insert amount:" );
                                amountOfInsertedMaterial = scanner.nextInt();
                                if (amountOfInsertedMaterial > 0) {
                                    break;
                                }
                                System.out.println( "Invalid options!" );
                            } catch (InputMismatchException e) {
                                System.out.println( "Invalid options!" );
                                scanner.nextLine();
                            }
                        }
                    } else {
                        continue;
                    }
                    System.out.println(service.fillMachineByUserInput(coffeeMachine, menuIndex, amountOfInsertedMaterial ));
                    continue;
                case 4:
                    System.out.println(service.cleanCoffeeMachine(coffeeMachine));
                    continue;
            }
        }
    }

    public static int validateAndReturnUserInput(int startIndex, int endIndex) {

        int menuIndex;

        while (true) {
            try {
                menuIndex = scanner.nextInt();
                if (menuIndex >= startIndex && menuIndex <= endIndex) {
                    break;
                }
                System.out.println( "Enter options from " + startIndex + " to " + endIndex );
            } catch (InputMismatchException e) {
                System.out.println( "Enter options from " + startIndex + " to " + endIndex );
                scanner.nextLine();
            }
        }
        return menuIndex;
    }

    public static void validateUserInput (int allowedValue) {
        while (true) {
            try {
                if (scanner.nextInt() == 0) {
                    break;
                }
                System.out.println( "Please press 0 to return to the main menu" );
            } catch (InputMismatchException e) {
                System.out.println( "Please press 0 to return to the main menu" );
                scanner.nextLine();
            }
        }
    }
}

