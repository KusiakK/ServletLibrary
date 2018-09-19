package models;

import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "is_borrowed", nullable = false)
    private Boolean isBorrowed = false;

    @Column(name = "category")
    private String category;

    @NotBlank
    @ISBN
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "summary", length = 360)
    private String summary;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "bookID")
    private Set<Borrow> borrows;

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, isBorrowed, category, isbn, pages, releaseDate, summary, title, author);
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
                '}';
    }
}
