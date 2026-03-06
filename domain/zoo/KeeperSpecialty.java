package domain.zoo;


public enum KeeperSpecialty{
    BIRD("조류 전문"),
    LARGE_ANIMAL("대형동물 전문"),
    REPTILE("파충류 전문");

    private final String displayName;

    KeeperSpecialty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


