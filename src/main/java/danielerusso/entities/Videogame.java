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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public double getHoursInGame() {
        return hoursInGame;
    }

    public void setHoursInGame(double hoursInGame) {
        this.hoursInGame = hoursInGame;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
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
