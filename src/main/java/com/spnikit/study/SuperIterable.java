package com.spnikit.study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private Iterable<E> self;


    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }



//    ---------------Methods-------------------------

    public SuperIterable<E> filter(Predicate<E> pred){
        List<E> results = new ArrayList<>();

        for(E e : self) {
            if (pred.test(e)){
                results.add(e);
            }
        }

        return new SuperIterable<>(results);
    }



    public void forEvey(Consumer<E> cons){
        for (E e: self){
            cons.accept(e);
        }
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

        strings.forEvey(c -> System.out.println("> " + c));

        System.out.println("-------------------------------------");

        SuperIterable<String> upperCase = strings.filter( c -> Character.isUpperCase(c.charAt(0)));

        upperCase.forEvey(c -> System.out.println("> " + c));
    }


}
