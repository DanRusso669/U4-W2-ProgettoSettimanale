package danielerusso.entities;

import danielerusso.exceptions.AlreadyInCollection;
import danielerusso.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

    //public static void updateGameById()

}
