package net.numericalk.meo.network;

public class ClientEnvironmentData {

    private static int windDirection = 0;
    private static float windStrength = 0.05F;

    private static int seasonId = 0;


    public static void updateSeason(int id){
        seasonId = id;
    }
    public static int getSeasonId(){
        return seasonId;
    }

    public static void updateWind(int direction, float strength) {
        windDirection = direction;
        windStrength = strength;
    }

    public static int getWindDirection() {
        return windDirection;
    }

    public static float getWindStrength() {
        return windStrength;
    }

    public static void reset() {
        windDirection = 0;
        windStrength = 0.05F;

        seasonId = 0;
    }
}