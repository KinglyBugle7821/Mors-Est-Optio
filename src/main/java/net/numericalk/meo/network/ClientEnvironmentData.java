package net.numericalk.meo.network;

public class ClientEnvironmentData {

    private static int windDirection = 0;
    private static float windStrength = 0.05F;

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
    }
}