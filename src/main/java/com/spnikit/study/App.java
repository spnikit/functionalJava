package com.spnikit.study;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;




@FunctionalInterface
  interface Criterion<T> {

     boolean test(T c);

}




public class App
{
    public static <T>void showAll(List<T> c){
        for (T elem: c){
            System.out.println(elem);
        }

        System.out.println("--------------------");
    }


    public static <T> List<T> getByCriterion(Iterable<T> in, Criterion<T> crit) {
        List<T> output = new ArrayList<>();

        for(T elem: in){
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

//        showAll(getByCriterion(cars, Car.getRedCarCriterion()));
//        showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(5)));
//        cars.sort(((o1, o2) -> o1.getGasLevel() - o2.getGasLevel()));
//        showAll(cars);

        showAll(getByCriterion(cars, c -> c.getPassengers().size() == 2));


    }
}


// TODO: 18.04.18 video #9 a question of ownership