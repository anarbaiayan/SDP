package strategies.sort;

import models.Book;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorSortStrategy implements SortStrategy {
    @Override
    public List<Book> sort(List<Book> books) {
        return books.stream()
                .sorted((b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor()))
                .collect(Collectors.toList());
    }
}