package org.example.city.other;

public class CityRecord {
    private String city;
    private String region;
    private String country;
    private double airQuality;
    private double waterPollution;

    public CityRecord(String city, String region, String country,
                      double airQuality, double waterPollution) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.airQuality = airQuality;
        this.waterPollution = waterPollution;
    }

    public String getCity() { return city; }
    public String getRegion() { return region; }
    public String getCountry() { return country; }
    public double getAirQuality() { return airQuality; }
    public double getWaterPollution() { return waterPollution; }
}
