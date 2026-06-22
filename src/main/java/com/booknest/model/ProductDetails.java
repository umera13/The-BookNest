package com.booknest.model;

public class ProductDetails {

    private int detailId;
    private int productId;

    private String publisher;
    private String language;

    private int pages;

    private String isbn;

    private int publicationYear;

    // =========================
    // GETTERS AND SETTERS
    // =========================

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {

        return "ProductDetails{" +
                "detailId=" + detailId +
                ", productId=" + productId +
                ", publisher='" + publisher + '\'' +
                ", language='" + language + '\'' +
                ", pages=" + pages +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}