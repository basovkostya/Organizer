package ru.organizer.orgdata.enums;

public enum EventType {
    ТУРНИР("Турнир"),
    РАЗОВАЯ_ВСТРЕЧА("Разовая встреча"),
    ПОВТОРЯЮЩЕЕСЯ_СОБЫТИЕ("Повторяющееся событие");

    private String name;

    EventType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
