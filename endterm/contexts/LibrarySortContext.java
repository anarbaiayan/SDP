package contexts;

import models.Book;
import strategies.sort.SortStrategy;
import java.util.List;

public class LibrarySortContext {
    private SortStrategy strategy;

    public void setSortStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> sortBooks(List<Book> books) {
        return strategy.sort(books);
    }
}