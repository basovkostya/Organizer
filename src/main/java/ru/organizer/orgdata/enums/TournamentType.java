package ru.organizer.orgdata.enums;

public enum TournamentType {
    КРУГОВАЯ_СИСТЕМА("Круговая система"),
    КУБКОВАЯ_СИСТЕМА("Кубковая система");

    private final String name;

    TournamentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
