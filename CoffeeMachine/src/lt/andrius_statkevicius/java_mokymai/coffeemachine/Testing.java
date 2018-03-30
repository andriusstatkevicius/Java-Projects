package lt.andrius_statkevicius.java_mokymai.coffeemachine;

public class Testing {

    public static void main(String[] args) {

        if (checkIfTrue()==true) {
            System.out.println("Maziau");
        }
        else {
            System.out.println("Daugiau");
        }
    }

    public static boolean checkIfTrue () {
        return CoffeeMachine.MILK_AMOUNT_LIMIT<100;
    }
}
