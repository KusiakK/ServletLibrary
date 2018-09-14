package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @Column(name = "id_borrow")
    private BigInteger borrowID;

    @Column(name = "book_id")
    @ManyToOne
    @JoinColumn
    private BigInteger bookID;

    @Column(name = "borrower_id")
    @ManyToOne
    @JoinColumn
    private BigInteger borrowerId;

    @Column(name = "rental_date")
    private LocalDate rentalDate;
}
