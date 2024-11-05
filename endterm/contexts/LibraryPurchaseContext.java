package contexts;

import models.Book;
import strategies.purchase.PurchaseStrategy;

public class LibraryPurchaseContext {
    private PurchaseStrategy strategy;

    public void setPurchaseStrategy(PurchaseStrategy strategy) {
        this.strategy = strategy;
    }

    public void purchaseBook(Book book) {
        strategy.purchase(book);
    }
}
//