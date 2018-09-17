package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @Column(name = "id_borrow")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrowID;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Borrow borrower;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;

    public Borrow() {
    }

    public Integer getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Integer borrowID) {
        this.borrowID = borrowID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrow getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrow borrower) {
        this.borrower = borrower;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(borrowID, borrow.borrowID) &&
                Objects.equals(book, borrow.book) &&
                Objects.equals(borrower, borrow.borrower) &&
                Objects.equals(rentalDate, borrow.rentalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowID, book, borrower, rentalDate);
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "borrowID=" + borrowID +
                ", book=" + book +
                ", borrower=" + borrower +
                ", rentalDate=" + rentalDate +
                '}';
    }
}
