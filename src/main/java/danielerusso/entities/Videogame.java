package danielerusso.entities;

import danielerusso.entities.enums.Genre;

public class Videogame extends Game {

    private String platform;
    private double hoursInGame;
    private Genre genre;

    public Videogame(long id, String title, int year, double price) {
        super(id, title, year, price);
    }
}
