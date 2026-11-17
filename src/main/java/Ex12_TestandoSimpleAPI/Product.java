package Ex12_TestandoSimpleAPI;

public class Product {
    private long id;
    private String type;
    private String isbn13;
    private double price;
    private long numberinstock;

    public void setId(long id)
        { this.id = id; }

    //////////////////////////////////////////

    public void setType(String type)
        { this.type = type; }

    //////////////////////////////////////////

    public void setIsbn(String isbn13)
        { this.isbn13 = isbn13; }

    //////////////////////////////////////////

    public void setPrice(double price)
        { this.price = price; }

    //////////////////////////////////////////

    public void setStock(long numberinstock)
    {    this.numberinstock = numberinstock; }

    //////////////////////////////////////////

    @Override
    public String toString() {
        return "Post {" +
                "id: " + id + ", " +
                "type: " + type + ", " +
                "isbn13: " + isbn13 + ", " +
                "price: " + price + ", " +
                "numberinstock: " + numberinstock +
                '}';
    }
}
