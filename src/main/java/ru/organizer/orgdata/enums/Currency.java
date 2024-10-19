package ru.organizer.orgdata.enums;

public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");

    private String name;

    Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
