package by.training.cafeproject.domain;

public enum Role {
    ADMINISTRATOR("администратор"),
    WORKER("работник"),
    CLIENT("посетитель");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static Role getByIdentity(Integer identity) {
        return Role.values()[identity];
    }
}
