package domain.zoo;

public enum Food {
    MEAT("육류"),
    FISH("어류"),
    PLANT("식물"),
    NUT("견과류");

    private final String displayName;

    Food(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}