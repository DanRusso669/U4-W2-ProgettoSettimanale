package danielerusso.entities;

import danielerusso.entities.enums.Genre;

public class Videogame extends Game {

    private String platform;
    private double hoursInGame;
    private Genre genre;

    public Videogame(long id, String title, int year, double price, String platform, double hoursInGame, Genre genre) {
        super(id, title, year, price);
        this.platform = platform;
        this.hoursInGame = hoursInGame;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Videogame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", platform='" + platform + '\'' +
                ", hoursInGame=" + hoursInGame +
                ", genre=" + genre +
                '}';
    }
}
