package lt.andrius_statkevicius.java_mokymai.coffeemachine;

import javafx.application.ConditionalFeature;

public class Service {

    public String fillMachineByUserInput(CoffeeMachine coffeeMachine, int selection, int amountOfInsertedMaterial) {
        StringBuilder sb = new StringBuilder();
        switch (selection) {
            case 1:
                if ((coffeeMachine.getSugarAmount() + amountOfInsertedMaterial) > CoffeeMachine.SUGAR_AMOUNT_LIMIT) {
                    coffeeMachine.setSugarAmount( CoffeeMachine.SUGAR_AMOUNT_LIMIT );
                    sb.append( "Sugar amount exceeded - sugar amount set to limit: " + CoffeeMachine.SUGAR_AMOUNT_LIMIT );
                } else {
                    coffeeMachine.setSugarAmount( coffeeMachine.getSugarAmount() + amountOfInsertedMaterial );
                    sb.append( "New sugar amount is: " + coffeeMachine.getSugarAmount() );
                }
                break;
            case 2:
                if ((coffeeMachine.getBeansAmount() + amountOfInsertedMaterial) > CoffeeMachine.BEANS_AMOUNT_LIMIT) {
                    coffeeMachine.setBeansAmount( CoffeeMachine.BEANS_AMOUNT_LIMIT );
                    sb.append( "Beans amount exceeded - beans amount set to the limit: " + CoffeeMachine.BEANS_AMOUNT_LIMIT );
                } else {
                    coffeeMachine.setBeansAmount( coffeeMachine.getBeansAmount() + amountOfInsertedMaterial );
                    sb.append( "New beans amount is: " + coffeeMachine.getBeansAmount() );
                }

                break;
            case 3:
                if ((coffeeMachine.getWaterAmount() + amountOfInsertedMaterial) > CoffeeMachine.WATER_AMOUNT_LIMIT) {
                    coffeeMachine.setWaterAmount( CoffeeMachine.WATER_AMOUNT_LIMIT );
                    sb.append( "Water amount exceeded - water amount set to the limit: " + CoffeeMachine.WATER_AMOUNT_LIMIT );
                } else {
                    coffeeMachine.setWaterAmount( coffeeMachine.getWaterAmount() + amountOfInsertedMaterial );
                    sb.append( "New water amount is: " + coffeeMachine.getWaterAmount() );
                }
                break;
            case 4:
                if ((coffeeMachine.getMilkAmount() + amountOfInsertedMaterial) > CoffeeMachine.MILK_AMOUNT_LIMIT) {
                    coffeeMachine.setMilkAmount( CoffeeMachine.MILK_AMOUNT_LIMIT );
                    sb.append( "Milk amount exceeded - milk amount set to the limit: " + CoffeeMachine.MILK_AMOUNT_LIMIT );
                } else {
                    coffeeMachine.setMilkAmount( coffeeMachine.getMilkAmount() + amountOfInsertedMaterial );
                    sb.append( "New milk amount is: " + coffeeMachine.getMilkAmount() );
                }
                break;
        }
        return sb.toString();
    }

    public String cleanCoffeeMachine(CoffeeMachine coffeeMachine) {
        StringBuilder sb = new StringBuilder();
        coffeeMachine.setNumberOfUsages( 0 );
        sb.append( "Your coffee machine is now clean and ready! Number of usages left until next clean: " + (CoffeeMachine.NUMBER_OF_USAGES_LIMIT - coffeeMachine.getNumberOfUsages()) );
        return sb.toString();
    }

    public String printDrinksMenu() {
        StringBuilder sb = new StringBuilder();
        sb = sb.append( "Drinks menu:\n" );

        for (int i = 0; i < CoffeeMachine.PRODUCTS.length; i++) {
            sb.append( i + 1 + ". " + CoffeeMachine.PRODUCTS[i].getName() + "\n" );
        }
        sb.append( "0. Main menu" );
        return sb.toString();
    }

    public String selectProductByUserInput(CoffeeMachine coffeeMachine, int drinkMenuIndex) {
        StringBuilder sb = new StringBuilder();
        try {
            isStatusOk( coffeeMachine, drinkMenuIndex );
            makeProductBySelection( coffeeMachine, drinkMenuIndex );
            sb.append( "Your " ).append( CoffeeMachine.PRODUCTS[drinkMenuIndex - 1].getName() )
                    .append( " is ready. Have a good day!\n" );
            coffeeMachine.setNumberOfUsages( coffeeMachine.getNumberOfUsages() + 1 );
        } catch (Exception e) {
            sb.append( "Error: " ).append( e.getMessage() );
        }

        return sb.toString();
    }


    private void isStatusOk(CoffeeMachine coffeeMachine, int selectedProduct) throws Exception {

        switch (selectedProduct) {
            case (1):
            case (2):
                checkBeansAmount( coffeeMachine, selectedProduct );
                checkNumberOfUsages( coffeeMachine );
                checkSugarAmount( coffeeMachine, selectedProduct );
                checkWaterAmount( coffeeMachine, selectedProduct );
                break;
            case (4):
                checkNumberOfUsages( coffeeMachine );
                checkWaterAmount( coffeeMachine, selectedProduct );
                break;
            default:
                checkNumberOfUsages( coffeeMachine );
                checkBeansAmount( coffeeMachine, selectedProduct );
                checkSugarAmount( coffeeMachine, selectedProduct );
                checkMilkAmount( coffeeMachine, selectedProduct );
                checkWaterAmount( coffeeMachine, selectedProduct );
                break;
        }
    }

    private void checkMilkAmount(CoffeeMachine coffeeMachine, int selectedProduct) throws Exception {
        if ((coffeeMachine.getMilkAmount() - CoffeeMachine.PRODUCTS[selectedProduct - 1].getMilkAmount()) < 0) {
            throw new Exception( "You need to fill milk!" );
        }
    }

    private void checkWaterAmount(CoffeeMachine coffeeMachine, int selectedProduct) throws Exception {
        if ((coffeeMachine.getWaterAmount() - CoffeeMachine.PRODUCTS[selectedProduct - 1].getWaterAmount()) < 0) {
            throw new Exception( "You need to fill water!" );
        }
    }

    private void checkSugarAmount(CoffeeMachine coffeeMachine, int selectedProduct) throws Exception {
        if ((coffeeMachine.getSugarAmount() - CoffeeMachine.PRODUCTS[selectedProduct - 1].getSugarAmount()) < 0) {
            throw new Exception( "You need to fill sugar!" );
        }
    }

    private void checkBeansAmount(CoffeeMachine coffeeMachine, int selectedProduct) throws Exception {
        if ((coffeeMachine.getBeansAmount() - CoffeeMachine.PRODUCTS[selectedProduct - 1].getBeansAmount()) < 0) {
            throw new Exception( "You need to fill beans!" );
        }
    }

    private void checkNumberOfUsages(CoffeeMachine coffeeMachine) throws Exception {
        if (coffeeMachine.getNumberOfUsages() == coffeeMachine.NUMBER_OF_USAGES_LIMIT) {
            throw new Exception( "Coffee Machine needs to be cleaned" );
        }
    }

    public String returnStatus(CoffeeMachine coffeeMachine) {
        StringBuilder sb = new StringBuilder();
        sb.append( "Status:\n1. Beans amount:" + coffeeMachine.getBeansAmount() + "\n2. Sugar amount: " + coffeeMachine.getSugarAmount() );
        sb.append( "\n3. Water amount: " + coffeeMachine.getWaterAmount() +
                "\n4. Milk amount: " + coffeeMachine.getMilkAmount() + "\n5. Number of usages left until cleaning: " +
                (CoffeeMachine.NUMBER_OF_USAGES_LIMIT - coffeeMachine.getNumberOfUsages()) );

        return sb.toString();
    }

    public String printMainMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append( "0. Exit\n1. Drinks\n2. Status\n3. Re-fill\n4. Clean" );

        return sb.toString();
    }

    public static String printRefillOptions() {
        StringBuilder sb = new StringBuilder();
        sb.append( "0. Main menu\n1. Fill sugar\n2. Fill beans\n3. Fill water\n4. Fill milk" );
        return sb.toString();
    }

    public void makeProductBySelection(CoffeeMachine coffeeMachine, int drinkMenuIndex) {
        coffeeMachine.setBeansAmount( coffeeMachine.getBeansAmount() - CoffeeMachine.PRODUCTS[drinkMenuIndex - 1].getBeansAmount() );
        coffeeMachine.setSugarAmount( coffeeMachine.getSugarAmount() - CoffeeMachine.PRODUCTS[drinkMenuIndex - 1].getSugarAmount() );
        coffeeMachine.setWaterAmount( coffeeMachine.getWaterAmount() - CoffeeMachine.PRODUCTS[drinkMenuIndex - 1].getWaterAmount() );
        coffeeMachine.setMilkAmount( coffeeMachine.getMilkAmount() - CoffeeMachine.PRODUCTS[drinkMenuIndex - 1].getMilkAmount() );
    }


}








