public class Article {
    private final int id;
    private final String description;
    private final double price;

    public Article(int id, String description, double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }
}
