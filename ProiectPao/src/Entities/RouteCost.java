package Entities;

import java.util.Comparator;

public class RouteCost {
    private double time;
    private double distance;
    private double money;
    private int currIdx;

    public RouteCost(double time, double distance, double money,int currIdx) {
        this.time = time;
        this.distance = distance;
        this.money = money;
        this.currIdx=currIdx;
    }

    public int getCurrIdx() {
        return currIdx;
    }

    public void setCurrIdx(int currIdx) {
        this.currIdx = currIdx;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "RouteCost{" +
                "time=" + time +
                ", distance=" + distance +
                ", money=" + money +
                '}';
    }
}

class TimeCost implements Comparator<RouteCost>{
    @Override
    public int compare(RouteCost o1, RouteCost o2) {
        return Double.compare(o1.getTime(),o2.getTime());
    }
}

class DistanceCost implements Comparator<RouteCost>{
    @Override
    public int compare(RouteCost o1, RouteCost o2) {
        return Double.compare(o1.getDistance(),o2.getDistance());
    }
}

class MoneyCost implements Comparator<RouteCost>{
    @Override
    public int compare(RouteCost o1, RouteCost o2) {
        return Double.compare(o1.getMoney(),o2.getMoney());
    }
}