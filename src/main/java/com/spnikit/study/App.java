package com.spnikit.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;





interface CarCriterion {

    boolean test(Car c);

}
public class App
{
    public static void showAll(List<Car> c){
        for (Car elem: c){
            System.out.println(elem);
        }

        System.out.println("--------------------");
    }


    public static List<Car> getCarsByCriterion(Iterable<Car> in, CarCriterion crit) {
        List<Car> output = new ArrayList<>();

        for(Car elem: in){
            if(crit.test(elem)) {
                output.add(elem);

            }
        }

        return output;
    }


    public static void main( String[] args )
    {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "RinceWind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );

        showAll(cars);

        showAll(getCarsByCriterion(cars, c -> c.getColor().equals("Black")));
        showAll(getCarsByCriterion(cars, c -> c.getGasLevel() >= 6));


    }
}


// TODO: 18.04.18 video #9 a question of ownership