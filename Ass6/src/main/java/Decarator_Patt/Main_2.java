package Decarator_Patt;


interface Pizza {
    double getPrice();
}


class BasicPizza implements Pizza {
    @Override
    public double getPrice() {
        return 5.0;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class PepperoniTopping extends PizzaDecorator {
    public PepperoniTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 2.0;
    }
}

class MushroomTopping extends PizzaDecorator {
    public MushroomTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 1.5;
    }
}

public class Main_2 {
    public static void main(String[] args) {
        Pizza basicPizza = new BasicPizza();

        Pizza pepperoniPizza = new PepperoniTopping(basicPizza);

        Pizza mushroomPepperoniPizza = new MushroomTopping(pepperoniPizza);

        System.out.println("Price of the pizza with toppings: $" + mushroomPepperoniPizza.getPrice());
    }
}

