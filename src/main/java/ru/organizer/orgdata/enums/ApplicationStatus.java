package ru.organizer.orgdata.enums;

public enum ApplicationStatus {
    НОВЫЙ("Новый"),
    ПОДТВЕРЖДЕНО("Подтверждено"),
    ОТКЛОНЕНО("Отклонено");

    private final String name;

    ApplicationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
