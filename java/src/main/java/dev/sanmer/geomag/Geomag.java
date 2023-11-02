package dev.sanmer.geomag;

import java.time.LocalDateTime;

public class Geomag {
    static {
        Library.load();
    }

    public static native double toDecimalYears(int year, int month, int day, int hour, int minute, int second);
    public static native MagneticField wmm(double longitude, double latitude, double altitude, double decimalYears);
    public static native MagneticField igrf(double longitude, double latitude, double altitude, double decimalYears);

    public static double toDecimalYears(LocalDateTime date) {
        return toDecimalYears(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), date.getHour(), date.getMinute(), date.getSecond());
    }

    public static MagneticField wmm(double longitude, double latitude, double altitude, LocalDateTime date) {
        return wmm(longitude, latitude, altitude, toDecimalYears(date));
    }

    public static MagneticField igrf(double longitude, double latitude, double altitude, LocalDateTime date) {
        return igrf(longitude, latitude, altitude, toDecimalYears(date));
    }
}
