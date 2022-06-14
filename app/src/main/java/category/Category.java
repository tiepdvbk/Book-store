package category;

import com.example.bookstoreapp.Book.Book;
import com.example.bookstoreapp.Book.MyBook;

import java.util.List;

public class Category {
    private String nameCtgr;
    private List<MyBook> books;

    public Category() {
    }

    public Category(String nameCtgr, List<MyBook> books) {
        this.nameCtgr = nameCtgr;
        this.books = books;
    }

    public String getNameCtgr() {
        return nameCtgr;
    }

    public void setNameCtgr(String nameCtgr) {
        this.nameCtgr = nameCtgr;
    }

    public List<MyBook> getBooks() {
        return books;
    }

    public void setBooks(List<MyBook> books) {
        this.books = books;
    }
}
