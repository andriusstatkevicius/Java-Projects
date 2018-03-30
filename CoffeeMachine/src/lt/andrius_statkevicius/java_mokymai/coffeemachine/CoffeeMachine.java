package lt.andrius_statkevicius.java_mokymai.coffeemachine;

public class CoffeeMachine {

    public static final int NUMBER_OF_USAGES_LIMIT = 20;
    public static final int SUGAR_AMOUNT_LIMIT = 100;
    public static final int BEANS_AMOUNT_LIMIT = 500;
    public static final int WATER_AMOUNT_LIMIT = 2000;
    public static final int MILK_AMOUNT_LIMIT = 1000;

    static final Product[] PRODUCTS = {new Product( "Black coffee", 5, 2, 100, 0 ),
            new Product( "Espresso", 10, 1, 50, 0 ),
            new Product( "Latte", 5, 4, 50, 20 ),
            new Product( "Hot water", 0, 0, 100, 0 )};


    private int sugarAmount;
    private int beansAmount;


    private int waterAmount;
    private int milkAmount;
    private int numberOfUsages;


    //constructor for setting up coffee machine which checks if something is negative or over the limit
    public CoffeeMachine(int sugarmount, int beansAmount, int waterAmount, int milkAmount) {
        if (sugarmount < 0) {
            this.sugarAmount = 0;
            System.out.println( "There is zero sugar in the container!" );
        } else if (sugarmount > SUGAR_AMOUNT_LIMIT) {
            this.sugarAmount = SUGAR_AMOUNT_LIMIT;
            System.out.println( "Sugar exceeded, container is full, set to: " + SUGAR_AMOUNT_LIMIT );
        } else {
            this.sugarAmount = sugarmount;
        }
        if (beansAmount < 0) {
            this.beansAmount = 0;
            System.out.println( "There is zero beans in the container!" );

        } else if (beansAmount > BEANS_AMOUNT_LIMIT) {
            this.beansAmount = BEANS_AMOUNT_LIMIT;
            System.out.println( "Beans exceeded, container is full, set to: " + BEANS_AMOUNT_LIMIT );
            ;
        } else {
            this.beansAmount = beansAmount;
        }
        if (waterAmount < 0) {
            this.waterAmount = 0;
            System.out.println( "There is zero water in the container!" );

        } else if (waterAmount > WATER_AMOUNT_LIMIT) {
            this.waterAmount = WATER_AMOUNT_LIMIT;
            System.out.println( "Water exceeded, container is full, set to: " + WATER_AMOUNT_LIMIT );
            ;
        } else {
            this.waterAmount = waterAmount;
        }
        if (milkAmount < 0) {
            this.milkAmount = 0;
            System.out.println( "There is zero milk in the container!" );

        } else if (milkAmount > MILK_AMOUNT_LIMIT) {
            this.milkAmount = MILK_AMOUNT_LIMIT;
            System.out.println( "Milk exceeded, container is full, set to: " + MILK_AMOUNT_LIMIT );
            ;
        } else {
            this.milkAmount = milkAmount;
        }
        this.numberOfUsages = 0;
    }


    public int getSugarAmount() {
        return sugarAmount;
    }

    public void setSugarAmount(int sugarAmount) {
        this.sugarAmount = sugarAmount;
    }

    public int getBeansAmount() {
        return beansAmount;
    }

    public void setBeansAmount(int beansAmount) {
        this.beansAmount = beansAmount;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }

    public int getNumberOfUsages() {
        return numberOfUsages;
    }

    public void setNumberOfUsages(int numberOfUsages) {
        this.numberOfUsages = numberOfUsages;
    }


}


