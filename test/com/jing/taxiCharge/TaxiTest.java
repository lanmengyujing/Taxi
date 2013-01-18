package com.jing.taxiCharge;

import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-18
 * StringTime: 上午10:20
 * To change this template use File | Settings | File Templates.
 */
public class TaxiTest {
    Taxi taxi;

    @Before
    public void setup(){
        taxi = new Taxi();
    }

    @Test
    public void should_charge_be_3_distance_within_2(){
        taxi.updateCharge(new StringTime("8:00"), new StringTime("8:01"), 2);
        assertThat(taxi.totalCharge(), is(3));
    }

    @Test
    public void test_start_charge_distance_within_2(){
        taxi.updateCharge(new StringTime("8:00"), new StringTime("8:01"), 1);
        assertThat(taxi.totalCharge(), is(3));
        taxi.updateCharge(new StringTime("8:01"), new StringTime("8:02"), 0);
        assertThat(taxi.totalCharge(), is(3));
        taxi.updateCharge(new StringTime("8:02"), new StringTime("8:03"), 1);
        assertThat(taxi.totalCharge(), is(3));
    }

    @Test
    public void should_charge_be_5_distance_is_3(){
        taxi.updateCharge(new StringTime("8:00"), new StringTime("8:01"), 3);
        assertThat(taxi.totalCharge(), is(5));
    }

    @Test
    public void test_charge_with_fuel_charge(){
        taxi.updateCharge(new StringTime("8:00"), new StringTime("8:01"), 3);
        assertThat(taxi.totalCharge(), is(5));
        taxi.updateCharge(new StringTime("8:02"), new StringTime("8:03"), 4);
        assertThat(taxi.totalCharge(), is(9));
    }

    @Test
    public void should_charge_be_7_when_speed_is_60_distance_is_4(){
        taxi.updateCharge(new StringTime("8:00"), new StringTime("8:01"), 3);
        taxi.updateCharge(new StringTime("8:02"), new StringTime("8:03"), 1);
        assertThat(taxi.totalCharge(), is(7));
    }

    @Test
    public void should_evening_charge_be_10_when_speed_is_240_distance_is_4(){
        taxi.updateCharge(new StringTime("00:00"), new StringTime("00:01"), 4);
        assertThat(taxi.totalCharge(), is(10));
    }

    @Test
    public void should_evening_charge_be_11_when_speed_is_60_total_distance_is_4(){
        taxi.updateCharge(new StringTime("00:00"), new StringTime("00:01"), 3);
        taxi.updateCharge(new StringTime("00:02"), new StringTime("00:03"), 1);
        assertThat(taxi.totalCharge(), is(11));
    }

    @Test
    public void should_evening_charge_be_10_when_speed_is_60_distance_is_4(){
        taxi.updateCharge(new StringTime("23:59"), new StringTime("00:00"), 2);
        assertThat(taxi.totalCharge(), is(5));
        taxi.updateCharge(new StringTime("00:00"), new StringTime("00:01"), 1);
        assertThat(taxi.totalCharge(), is(9));
    }

}
