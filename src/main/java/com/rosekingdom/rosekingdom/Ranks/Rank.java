package com.rosekingdom.rosekingdom.Ranks;

public enum Rank {
    DEFAULT("\uDB00\uDC02\uEa05\uDB00\uDC03"),
    OWNER("\uDB00\uDC02\uEa01\uDB00\uDC03"),
    ADMIN("\uDB00\uDC02\uEa02\uDB00\uDC03"),
    MOD("\uDB00\uDC02\uEa03\uDB00\uDC03"),
    ARTIST("\uDB00\uDC02\uEa04\uDB00\uDC03");

    public final String prefix;
    Rank(String prefix) {
        this.prefix = prefix;
    }
}
