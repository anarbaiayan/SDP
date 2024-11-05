package strategies.purchase;

import models.Book;

public class EBookPurchaseStrategy implements PurchaseStrategy {
    @Override
    public void purchase(Book book) {
        System.out.println("Purchasing ebook: " + book.getTitle());
    }
}