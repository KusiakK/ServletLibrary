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
    private Integer borrowID;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Borrow borrower;

    @Column(name = "rental_date", nullable = false)
    private LocalDate rentalDate;
}
