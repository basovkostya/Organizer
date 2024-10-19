package ru.organizer.orgdata.enums;

public enum SportType {
    MARATHON("Марафон"),
    SPRINT("Спринт"),
    SKIS("Лыжи"),
    BIATHLON("Биатлон"),
    BOX("Бокс"),
    MMA("MMA"),
    JUDO("Дзюдо"),
    THAI_BOX("Тайский бокс"),
    FOOTBALL("Футбол"),
    VOLLEYBALL("Воллейбол"),
    BASKETBALL("Баскетбол"),
    HOCKEY("Хоккей");

    private final String name;

    SportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
