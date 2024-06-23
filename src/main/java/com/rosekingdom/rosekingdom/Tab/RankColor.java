package com.rosekingdom.rosekingdom.Tab;

public enum RankColor {
    OWNER("#d73535"),
    ADMIN("#317fd8"),
    MOD("#fba833"),
    ARTIST("#6abd2c"),
    DEFAULT("#af7bd9");

    public final String color;
    RankColor(String color) {
        this.color = color;
    }
}
