package booking;

public class BookEntry  {
    public enum Kind {
        PREMIUM,
        ECONOMY
    }

    private double price;
    private Kind kind;

    public BookEntry(double price, Kind kind) {
        this.price = price;
        this.kind = kind;
    }

    public double getPrice() {
        return price;
    }

    public Kind getKind() {
        return kind;
    }
}
