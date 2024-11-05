package strategies.sort;

import models.Book;
import java.util.List;

public interface SortStrategy {
    List<Book> sort(List<Book> books);
}