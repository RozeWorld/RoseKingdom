package com.rosekingdom.rosekingdom.Core.Utils;

public class MillisToTime {
    public static String withWord(long millis){
        int rawTime = (int) millis;
        int days = rawTime/86400;
        int hours = (rawTime%86400) / 3600;
        int minutes = ((rawTime%86400) % 3600) / 60;
        int seconds = rawTime%60;
        if(days > 0){
            return String.format("%d days, %d hours, %d minutes", days, hours, minutes);
        }
        if(hours > 0){
            return String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
        }
        if(minutes > 0){
            return String.format("%d minutes, %d seconds", minutes, seconds);
        }
        return String.format("%d seconds", seconds);
    }
    public static String withSymbol(long millis){
        int rawTime = (int) millis;
        int days = rawTime/86400;
        int hours = (rawTime%86400) / 3600;
        int minutes = ((rawTime%86400) % 3600) / 60;
        int seconds = rawTime%60;
        if(days > 0){
            return String.format("%dd %dh %dm", days, hours, minutes);
        }
        if(hours > 0){
            return String.format("%dh %dm %ds", hours, minutes, seconds);
        }
        if(minutes > 0){
            return String.format("%dm %ds", minutes, seconds);
        }
        return String.format("%ds", seconds);
    }
}
