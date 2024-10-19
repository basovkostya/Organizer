package ru.organizer.orgdata.enums;

public enum Sex {
    МУЖСКОЙ ("Мужской"),
    ЖЕНСКИЙ ("Женский");

    private final String name;

    Sex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
