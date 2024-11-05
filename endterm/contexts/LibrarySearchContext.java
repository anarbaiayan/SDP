package contexts;

import models.Book;
import strategies.search.SearchStrategy;
import java.util.List;

public class LibrarySearchContext {
    private SearchStrategy strategy;

    public void setSearchStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Book> searchBooks(List<Book> books, String query) {
        return strategy.search(books, query);
    }
}