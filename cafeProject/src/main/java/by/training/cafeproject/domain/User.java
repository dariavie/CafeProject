package by.training.cafeproject.domain;

import java.util.Objects;

public class User extends Entity {
    private String login;
    private String password;
    private Role role;

    public User() {}

    public User(Integer id) {
        setId(id);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        if (role==0) {
            this.role = Role.ADMINISTRATOR;
        } else if (role==1) {
            this.role = Role.WORKER;
        } else if (role==2) {
            this.role = Role.CLIENT;
        }
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public int getRoleNumber() {
        int number = -1;
        if (role.equals(Role.ADMINISTRATOR)) {
            number = 0;
        } else if (role.equals(Role.WORKER)) {
            number = 1;
        } else if (role.equals(Role.CLIENT)) {
            number = 2;
        }
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}

