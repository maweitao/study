package io.github.gwpost.util;

import io.github.gwpost.model.WeatherInfo;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by guwen on 15-11-1.
 */
public class WeatherHelperTest {

    @Test
    public void testGetWeatherInform() throws Exception {
        String weatherInfo=WeatherHelper.getWeatherInform("武汉");
        System.out.println(weatherInfo);
    }

    @Test
    public void testResolveWeatherInfo() throws Exception {
        String weatherInfoStr=WeatherHelper.getWeatherInform("武汉");
        WeatherInfo weatherInfo=WeatherHelper.resolveWeatherInfo(weatherInfoStr);
        System.out.println(weatherInfo);
    }

    @Test
    public void testIndexOf(){
        String weather="小雨";
        Assert.assertTrue(weather.indexOf("雨")>-1);
    }
}