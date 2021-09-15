package by.training.cafeproject.domain;

import java.util.Objects;

public class UserInfo extends Entity {
    private User userId;
    private String surname;
    private String name;
    private String phone;
    private String email;

    public void setUserId(User id) {
        this.userId = id;
        setId(id.getId());
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(userId, userInfo.userId) && Objects.equals(surname, userInfo.surname) && Objects.equals(name, userInfo.name) && Objects.equals(phone, userInfo.phone) && Objects.equals(email, userInfo.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, surname, name, phone, email);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                "} " + super.toString();
    }
}
