package pages;

public class ProductDetails {
    private String title;
    private String price;
    private String message;

    public ProductDetails(String title, String price, String message) {
        this.title = title;
        this.price = price;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }
}

