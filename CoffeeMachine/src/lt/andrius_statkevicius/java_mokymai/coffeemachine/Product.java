package lt.andrius_statkevicius.java_mokymai.coffeemachine;

public class Product {

    private String name;
    private int sugarAmount;
    private int beansAmount;
    private int waterAmount;
    private int milkAmount;

    public Product(String name, int beansAmount, int sugarAmount, int waterAmount, int milkAmount) {
        this.name = name;
        this.sugarAmount = sugarAmount;
        this.beansAmount = beansAmount;
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
