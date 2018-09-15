package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "borrower")
public class Borrower {

    @Id
    @Column(name = "id_borrower")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrowerID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "borrower")
    private Set<Borrow> borrows;

    @OneToOne
    @JoinColumn
    private BorrowerDetails borrowerDetails;

    public Borrower() {
    }

    public Integer getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(Integer borrowerID) {
        this.borrowerID = borrowerID;
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

    public BorrowerDetails getBorrowerDetails() {
        return borrowerDetails;
    }

    public void setBorrowerDetails(BorrowerDetails borrowerDetails) {
        this.borrowerDetails = borrowerDetails;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return Objects.equals(borrowerID, borrower.borrowerID) &&
                Objects.equals(firstName, borrower.firstName) &&
                Objects.equals(lastName, borrower.lastName) &&
                Objects.equals(borrowerDetails, borrower.borrowerDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerID, firstName, lastName, borrowerDetails);
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "borrowerID=" + borrowerID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", borrowerDetails=" + borrowerDetails +
                '}';
    }
}
