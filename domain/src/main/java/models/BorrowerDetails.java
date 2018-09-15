package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "borrower_details")
public class BorrowerDetails {

    @Id
    @Column(name = "id_borrower_details")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrowerDetailsID;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToOne(mappedBy = "borrowerDetails")
    @JoinColumn
    private Borrower borrower;

    public BorrowerDetails() {
    }

    public Integer getBorrowerDetailsID() {
        return borrowerDetailsID;
    }

    public void setBorrowerDetailsID(Integer borrowerDetailsID) {
        this.borrowerDetailsID = borrowerDetailsID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowerDetails that = (BorrowerDetails) o;
        return Objects.equals(borrowerDetailsID, that.borrowerDetailsID) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(borrower, that.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerDetailsID, address, email, phone, borrower);
    }

    @Override
    public String toString() {
        return "BorrowerDetails{" +
                "borrowerDetailsID=" + borrowerDetailsID +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", borrower=" + borrower +
                '}';
    }
}
