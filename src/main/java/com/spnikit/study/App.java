package com.spnikit.study;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;


public class App {



    public static <E> ToIntFunction<E> compareWithThis(E target, Comparator<E> comp){

        return x -> comp.compare(target, x);

    }


    public static <E> Predicate<E> comparesGreater(ToIntFunction<E> func) {
        return x -> func.applyAsInt(x) < 0;
    }


    public static <T>void showAll(List<T> c){
        for (T elem: c){
            System.out.println(elem);
        }

        System.out.println("--------------------");
    }


    public static <T> List<T> getByCriterion(Iterable<T> in, Predicate<T> crit) {
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
        // showAll(getByCriterion(cars, Car.getGasLevelCarCriterion(5)));
//        cars.sort(((o1, o2) -> o1.getGasLevel() - o2.getGasLevel()));
//        showAll(cars);

        // showAll(getByCriterion(cars, c -> c.getPassengers().size() == 2));

        // showAll(getByCriterion(cars, Car.getColorCriterion("Purple", "Red")));

        Predicate<Car> level7 = Car.getGasLevelCarCriterion(7);

        showAll(getByCriterion(cars, level7));
        showAll(getByCriterion(cars, level7.negate()));


        Car bert = Car.withGasColorPassengers(5, "Blue");

        ToIntFunction<Car> compareWithBert = compareWithThis(bert, (c1, c2) -> c1.getGasLevel() - c2.getGasLevel());

        showAll(getByCriterion(cars, comparesGreater(compareWithBert)));



    }
}


