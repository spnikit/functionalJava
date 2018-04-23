package com.spnikit.study;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NullChecks {

    public static void main(String[] args) {
        Map<String, Car> owners = new HashMap<>();

        owners.put("Sheila", Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"));
        owners.put("Librarian", Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Rindcully"));
        owners.put("Ogg", Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"));
        owners.put("Suck", Car.withGasColorPassengersAndTrunk(9, "Black", "Weatherwax", "Magrat"));

        String owner = "Ogg";

        Car c = owners.get(owner);

        List<String> trunkItems = c.getTrunkContents();

        System.out.println(owner + " has " + trunkItems + " in the car.");


        System.out.println("----------------------------");

        Optional<Map<String, Car>> ownersOpt = Optional.of(owners);

        ownersOpt.map(m -> m.get(owner))
                 .map(car -> car.getTrunkContentsOpt()
                            .map(x -> x.toString())
                            .orElse("nothing"))
                 .map(e -> owner + " has " + e + " in the car")
                 .ifPresent(System.out::println);
    }
}
