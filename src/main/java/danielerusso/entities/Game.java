package danielerusso.entities;

public abstract class Game {

    protected long id;
    protected String title;
    protected int year;
    protected double price;

    public Game(long id, String title, int year, double price) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
    }
}
