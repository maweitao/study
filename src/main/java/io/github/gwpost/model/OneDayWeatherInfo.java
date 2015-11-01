package io.github.gwpost.model;

/**
 * 一天的天气信息
 * Created by guwen on 15-10-31.
 */
public class OneDayWeatherInfo {
    private String location;
    private String week;
    private String date;
    private String tempertureOfDay;
    private String tempertureNow;
    private String wind;
    private String weather;
    int pmTwoPointFive;
    private String dayPictureUrl;
    private String nightPictureUrl;

    public OneDayWeatherInfo() {

        location = "";
        date = "";
        week = "";
        tempertureOfDay = "";
        tempertureNow = "";
        wind = "";
        weather = "";
        pmTwoPointFive = 0;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempertureOfDay() {
        return tempertureOfDay;
    }

    public void setTempertureOfDay(String tempertureOfDay) {
        this.tempertureOfDay = tempertureOfDay;
    }

    public String getTempertureNow() {
        return tempertureNow;
    }

    public void setTempertureNow(String tempertureNow) {
        this.tempertureNow = tempertureNow;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getPmTwoPointFive() {
        return pmTwoPointFive;
    }

    public void setPmTwoPointFive(int pmTwoPointFive) {
        this.pmTwoPointFive = pmTwoPointFive;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }
}
