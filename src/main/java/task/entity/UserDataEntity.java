package task.entity;

import java.io.Serializable;

public class UserDataEntity implements Serializable {

    private String firstName;
    private String lastName;
    private String telephoneNumber;

    public UserDataEntity() {
    }

    public UserDataEntity(String firstName, String lastName, String telephoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "UserDataEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDataEntity)) return false;

        UserDataEntity that = (UserDataEntity) o;

        if (!getFirstName().equals(that.getFirstName())) return false;
        if (!getLastName().equals(that.getLastName())) return false;
        return getTelephoneNumber().equals(that.getTelephoneNumber());

    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getTelephoneNumber().hashCode();
        return result;
    }
}
