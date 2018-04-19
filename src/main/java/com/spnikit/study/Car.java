package com.spnikit.study;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Car {

    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }


    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));

        Car self = new Car(gas, color, p, null);

        return self;
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));

        Car self = new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));

        return self;

    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Car{" +
                "gasLevel=" + gasLevel +
                ", color='" + color + '\'' +
                ", passengers=" + passengers +
                ", trunkContents=" + (trunkContents != null ? trunkContents : " no trunk") +
                '}';
    }

    // --------------------- Criterions--------------------------------------



    public static Criterion<Car> getFourPassengerCriterion(){
        return c -> c.passengers.size() == 4;
    }

    public static Criterion<Car> getRedCarCriterion() {
        return RED_CAR_CRITERION;
    }

    private static final Criterion<Car> RED_CAR_CRITERION = c -> c.color.equals("Red");




    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
        return new Criterion<Car>() {
            @Override
            public boolean test(Car c) {
                return c.gasLevel >= threshold;
            }
        };
    }


}
