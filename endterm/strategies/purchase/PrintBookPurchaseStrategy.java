package strategies.purchase;

import models.Book;

public class PrintBookPurchaseStrategy implements PurchaseStrategy {
    @Override
    public void purchase(Book book) {
        System.out.println("Purchasing print book: " + book.getTitle());
    }
}