package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "userID")
    private Set<Borrow> borrows;

    @OneToOne
    @JoinColumn
    private UserDetails userDetails;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String borrowerID) {
        this.userName = borrowerID;
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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userDetails, user.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, firstName, lastName, userDetails);
    }

    @Override
    public String toString() {
        return "User{" +
                "borrowerID=" + userName +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}
