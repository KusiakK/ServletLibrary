package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @Column(name = "id_borrow")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger borrowID;

    @Column(name = "book_id")
    @ManyToOne
    @JoinColumn
    private List<Book> books;

    @Column(name = "borrower_id")
    @ManyToOne
    @JoinColumn
    private List<Borrower> borrowers;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;
}
