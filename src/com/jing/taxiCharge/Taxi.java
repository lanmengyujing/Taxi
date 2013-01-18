package com.jing.taxiCharge;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-18
 * StringTime: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
public class Taxi {
    private int totalCharge;
    private int totalDistance;
    private static final int PRICE_PER_MILE = 1;
    private static final int START_DISTANCE = 2;
    private static final int START_CHARGE = 3;
    private static final int FUEL_CHARGE = 1;
    private static final int WAITING_MINUTE_CHARGE = 1;
    private static final int MIN_SPEED = 120;
    private static final int EVENING_CHARGE = 1 ;


    public int totalCharge() {
        if (totalDistance > START_DISTANCE) {
            return totalCharge + START_CHARGE + FUEL_CHARGE;
        }
        return totalCharge + START_CHARGE;
    }

    public void updateCharge(StringTime start_at, StringTime end_at, int distance) {
        int preTotalDistance = totalDistance;
        totalDistance += distance;
        int addtionalWaitCharge = 0;
        int eveningCharge = eveningCharge(start_at, end_at, distance);

        if (totalDistance > START_DISTANCE) {
            if (preTotalDistance < START_DISTANCE) { // previous distance less than START_DISTANCE, no waitCharge
                distance = totalDistance - START_DISTANCE;
            } else {
                addtionalWaitCharge = waitingCharge(start_at, end_at, distance);
            }
            totalCharge += PRICE_PER_MILE * distance + addtionalWaitCharge ;
        }
        totalCharge += eveningCharge;
    }

    private int eveningCharge(StringTime start_at, StringTime end_at, int distance) {
        int eveningCharge = 0;
        if (start_at.isEveningTime() && end_at.isEveningTime()){
            eveningCharge = EVENING_CHARGE * distance;
        }
        return eveningCharge;
    }

    private int waitingCharge(StringTime start_at, StringTime end_at, int distance) {
        int waitingCharge = 0;
        if (speed(start_at, end_at, distance) < MIN_SPEED) {
            waitingCharge = WAITING_MINUTE_CHARGE * end_at.minus(start_at);
        }
        return waitingCharge;
    }

    private int speed(StringTime start_at, StringTime end_at, int distance) {
        int minutes = end_at.minus(start_at);
        return 60 * distance / minutes;
    }

}
