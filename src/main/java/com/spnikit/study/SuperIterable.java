package com.spnikit.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private Iterable<E> self;


    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }



//    ---------------Methods-------------------------

    public SuperIterable<E> filter(Predicate<E> pred){
        List<E> results = new ArrayList<>();

        self.forEach(c -> {
            if (pred.test(c)) results.add(c);
        });

        return new SuperIterable<>(results);
    }


    public <F> SuperIterable<F> map(Function<E, F> op) {
        List<F> results = new ArrayList<>();

        self.forEach(c -> results.add(op.apply(c)));

        return new SuperIterable<>(results);
    }


    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op){
        List<F> results = new ArrayList<>();

        self.forEach(e -> op.apply(e).forEach(results::add));

        return new SuperIterable<>(results);
    }






//    ---------------------Iterator------------------------------


    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

//    ---------------------------------MAIN------------------------------------------

    public static void main(String[] args) {
        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("FUCK", "sUCK", "MOCKAK", "lflffl", "Mig", "smalll", "littel")
        );

        strings.forEach(c -> System.out.println("> " + c));

        System.out.println("-------------------------------------");

        SuperIterable<String> upperCase = strings.filter( c -> Character.isUpperCase(c.charAt(0)));

        upperCase.forEach(c -> System.out.println("> " + c));

        System.out.println("----------------------------------------");

        strings
                .map(String::toUpperCase)
                .forEach(System.out::println);


        SuperIterable<Car>carIter = new SuperIterable<>(
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "RinceWind", "Ridcully"),
                        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                        Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
                )
        );

        carIter.forEach(System.out::println);

        System.out.println("------------------------------------");

        carIter
                .map(c -> c.addGas(4))
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        carIter
                .flatMap(e -> new SuperIterable<>(e.getPassengers()))
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("------------------------------------");

        carIter.flatMap(c -> new SuperIterable<>(c.getPassengers())
                .map(e -> e + " is riding in a " + c.getColor() + " car"))
                .forEach(s -> System.out.println(s));

    }


}
