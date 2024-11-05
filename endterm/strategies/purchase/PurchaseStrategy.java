package strategies.purchase;

import models.Book;

public interface PurchaseStrategy {
    void purchase(Book book);
}