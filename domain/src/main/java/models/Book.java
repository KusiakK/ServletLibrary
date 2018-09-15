package models;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id_books")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookID;

    @Column(name = "borrow")
    private Boolean isBorrowed;

    @Column(name = "category")
    private String category;

    @Column(name = "isbn", nullable = false)
    private Long isbn;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "summary")
    private String summary;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn
    private Author author;

    @Column(name = "borrows")
    @OneToMany(mappedBy = "book")
    private Set<Borrow> borrows;

    @ManyToMany
    @JoinColumn
    private List<Borrower> borrowers;

    public Book() {
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Borrower> getBorrowers() {
        return borrowers;
    }

    public void setBorrowers(List<Borrower> borrowers) {
        this.borrowers = borrowers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookID, book.bookID) &&
                Objects.equals(isBorrowed, book.isBorrowed) &&
                Objects.equals(category, book.category) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(pages, book.pages) &&
                Objects.equals(releaseDate, book.releaseDate) &&
                Objects.equals(summary, book.summary) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(borrowers, book.borrowers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, isBorrowed, category, isbn, pages, releaseDate, summary, title, author, borrowers);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", isBorrowed=" + isBorrowed +
                ", category='" + category + '\'' +
                ", isbn=" + isbn +
                ", pages=" + pages +
                ", releaseDate=" + releaseDate +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", borrowers=" + borrowers +
                '}';
    }
}
