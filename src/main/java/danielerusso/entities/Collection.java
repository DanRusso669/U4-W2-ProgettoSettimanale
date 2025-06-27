package danielerusso.entities;

import danielerusso.entities.enums.Genre;
import danielerusso.exceptions.AlreadyInCollection;
import danielerusso.exceptions.NotFoundException;

import java.util.*;

public abstract class Collection {
    public static List<Game> myCollection = new ArrayList<>();

    // METHODS
    public static void addGame(Game game) {
        try {
            if (myCollection.contains(game)) {
                throw new AlreadyInCollection();
            } else {
                myCollection.add(game);
                System.out.println("Game successfully added to your collection.");
            }
        } catch (AlreadyInCollection e) {
            System.out.println(e.getMessage());
        }
    }

    public static Game getGameById(long id) throws NotFoundException {

        // https://www.baeldung.com/java-optional-throw-exception#bd-throw-exception-when-value-is-missing

        Optional<Game> foundGame = myCollection.stream().filter(game -> game.getId() == id).findFirst();
        if (foundGame.isPresent()) {
            return foundGame.get();
        }
        throw new NotFoundException(id);
    }

    public static void searchById(long id) {
        try {
            if (myCollection.contains(getGameById(id))) {

                System.out.println(getGameById(id));
            } else {
                throw new NotFoundException(id);
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchByPrice(double price) {
        List<Game> searchedGames = myCollection.stream().filter(game -> game.getPrice() < price).sorted(Comparator.comparing(Game::getPrice)).toList().reversed();
        searchedGames.forEach(game -> System.out.println(game));
    }

    public static void searchByNumOfPlayers(int numOfPlayers) {
        List<Game> searchedGames = myCollection.stream().filter(game -> game instanceof Boardgame).filter(game -> ((Boardgame) game).getNumOfPlayers() == numOfPlayers).toList();
        if (searchedGames.isEmpty()) {
            System.out.println("No game found with this number of players: " + numOfPlayers);
        } else {
            searchedGames.forEach(game -> System.out.println(game));
        }
    }

    public static void deleteById(long id) {
        // Optional<Game> gameToDelete = myCollection.stream().filter(game -> game.getId() == id).findFirst();

        try {
            if (myCollection.contains(getGameById(id))) {
                myCollection.remove(getGameById(id));
                System.out.println("Game removed successfully.");
            } else {
                throw new NotFoundException(id);
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateGameById(long id) {
        try {
            Scanner scan = new Scanner(System.in);
            Game selectedGame = getGameById(id);
            if (myCollection.contains(selectedGame)) {
                System.out.println("Game found. Leaves blank space to keep the current value.");
                System.out.println("Old title: " + selectedGame.getTitle() + ", New title: ");
                selectedGame.setTitle(scan.nextLine());
                System.out.println("Old year: " + selectedGame.getYear() + ", New year: ");
                selectedGame.setYear(Integer.parseInt(scan.nextLine()));
                System.out.println("Old price: " + selectedGame.getPrice() + ", New price: ");
                selectedGame.setPrice(Double.parseDouble(scan.nextLine()));
                if (selectedGame instanceof Boardgame) {
                    System.out.println("Old number of players: " + ((Boardgame) selectedGame).getNumOfPlayers() + ", New number of players: ");
                    ((Boardgame) selectedGame).setNumOfPlayers(Integer.parseInt(scan.nextLine()));
                    System.out.println("Old playing time: " + ((Boardgame) selectedGame).getPlayingTime() + ", New playing time: ");
                    ((Boardgame) selectedGame).setPlayingTime(Integer.parseInt(scan.nextLine()));
                } else if (selectedGame instanceof Videogame) {
                    System.out.println("Old genre: " + ((Videogame) selectedGame).getGenre() + ", New genre (COOP, SINGLEPLAYER, FPS): ");
                    ((Videogame) selectedGame).setGenre(Genre.valueOf(scan.nextLine()));
                    System.out.println("Old platform: " + ((Videogame) selectedGame).getPlatform() + ", New platform: ");
                    ((Videogame) selectedGame).setPlatform(scan.nextLine());
                    System.out.println("Old hours in game: " + ((Videogame) selectedGame).getHoursInGame() + ", New hours in game: ");
                    ((Videogame) selectedGame).setHoursInGame(Double.parseDouble(scan.nextLine()));
                }
            }

            System.out.println("Game modified successfully.");
            System.out.println(selectedGame);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getStats() {
        List<Game> boardgames = myCollection.stream().filter(game -> game instanceof Boardgame).toList();
        List<Game> videogames = myCollection.stream().filter(game -> game instanceof Videogame).toList();
        OptionalDouble averagePrice = myCollection.stream().mapToDouble(game -> game.getPrice()).average();
        Game mostExpensiveGame = myCollection.stream().sorted(Comparator.comparing(Game::getPrice).reversed()).limit(1).toList().get(0);

        if (averagePrice.isPresent()) {
            System.out.println("Your collection contains " + boardgames.size() + " boardgames and " + videogames.size() + " videogames, for a total of " + myCollection.size() + " games.");
            System.out.println("The most expensive game is " + mostExpensiveGame.getTitle() + " with a price of " + mostExpensiveGame.getPrice());
            System.out.println("The average price of your collection is: " + averagePrice.getAsDouble());
        } else System.out.println("Your collection is empty.");
    }

}
