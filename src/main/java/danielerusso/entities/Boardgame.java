package danielerusso.entities;

public class Boardgame extends Game {

    private int numOfPlayers;
    private int playingTime;

    public Boardgame(long id, String title, int year, double price, int numOfPlayers, int playingTime) {
        super(id, title, year, price);
        this.numOfPlayers = numOfPlayers;
        this.playingTime = playingTime;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    @Override
    public String toString() {
        return "Boardgame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", numOfPlayers=" + numOfPlayers +
                ", playingTime=" + playingTime +
                '}';
    }
}
