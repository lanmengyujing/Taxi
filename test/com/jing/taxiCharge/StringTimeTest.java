package com.jing.taxiCharge;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-18
 * Time: 下午7:10
 * To change this template use File | Settings | File Templates.
 */
public class StringTimeTest {

    @Test
    public void should_diff_be_4_when_start_at_8_00_end_at_8_04(){
        StringTime start_at = new StringTime("08:00");
        StringTime end_at = new StringTime("08:04");
        assertThat(end_at.minus(start_at), is(4));
    }

    @Test
    public void should_diff_be_1_when_start_at_23_59_end_at_00_00(){
        StringTime start_at = new StringTime("23:59");
        StringTime end_at = new StringTime("00:00");
        assertThat(end_at.minus(start_at), is(1));
    }

    @Test
    public void test_evening_time(){
        StringTime start_at = new StringTime("23:59");
        assertThat(start_at.isEveningTime(), is(true));
    }

}
