package danielerusso.entities;

import danielerusso.entities.enums.Genre;
import danielerusso.exceptions.AlreadyInCollection;
import danielerusso.exceptions.NotFoundException;

import java.util.*;

/*
 Ho inizialmente creato la classe astratta pensando di non istanziarla nel main, così facendo però ho lasciato tutti i metodi statici e quindi accessibili dall'esterno della classe,
 non seguendo il principio dell'incapsulamento.
*/

public class Collection {
    private List<Game> myCollection;

    public Collection() {
        this.myCollection = new ArrayList<>();
    }

    public Game getGameById(long id) throws NotFoundException {

        // https://www.baeldung.com/java-optional-throw-exception#bd-throw-exception-when-value-is-missing

        Optional<Game> foundGame = this.myCollection.stream().filter(game -> game.getId() == id).findFirst();
        if (foundGame.isPresent()) {
            return foundGame.get();
        }
        throw new NotFoundException(id);
    }

    public void searchById(long id) {
        try {
            if (this.myCollection.contains(getGameById(id))) {

                System.out.println(getGameById(id));
            } else {
                throw new NotFoundException(id);
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchByPrice(double price) {
        List<Game> searchedGames = this.myCollection.stream().filter(game -> game.getPrice() < price).sorted(Comparator.comparing(Game::getPrice)).toList().reversed();

        if (searchedGames.isEmpty()) {
            System.out.println("There is no game in your collection with a price lower than " + price);
        } else {
            searchedGames.forEach(game -> System.out.println(game));
        }
    }

    public void searchByNumOfPlayers(int numOfPlayers) {
        List<Game> searchedGames = this.myCollection.stream().filter(game -> game instanceof Boardgame).filter(game -> ((Boardgame) game).getNumOfPlayers() == numOfPlayers).toList();
        if (searchedGames.isEmpty()) {
            System.out.println("No game found with this number of players: " + numOfPlayers);
        } else {
            searchedGames.forEach(game -> System.out.println(game));
        }
    }

    public void getCollection() {
        for (Game game : this.myCollection) {
            System.out.println(game);
        }
    }

    public void deleteById(long id) {
        try {
            if (this.myCollection.contains(getGameById(id))) {
                this.myCollection.remove(getGameById(id));
                System.out.println("Game removed successfully.");
            } else {
                throw new NotFoundException(id);
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateGameById(long id) {
        try {
            Scanner scan = new Scanner(System.in);
            Game selectedGame = getGameById(id);
            if (this.myCollection.contains(selectedGame)) {
                System.out.println("Game found. Leaves blank space to keep the current value.");

                System.out.println("Old title: " + selectedGame.getTitle() + ", New title: ");
                String input = scan.nextLine();
                if (!input.isEmpty()) selectedGame.setTitle(input);

                System.out.println("Old year: " + selectedGame.getYear() + ", New year: ");
                input = scan.nextLine();
                if (!input.isEmpty()) selectedGame.setYear(Integer.parseInt(input));

                System.out.println("Old price: " + selectedGame.getPrice() + ", New price: ");
                input = scan.nextLine();
                if (!input.isEmpty()) selectedGame.setPrice(Double.parseDouble(input));

                if (selectedGame instanceof Boardgame) {
                    System.out.println("Old number of players: " + ((Boardgame) selectedGame).getNumOfPlayers() + ", New number of players: ");
                    input = scan.nextLine();
                    if (!input.isEmpty()) ((Boardgame) selectedGame).setNumOfPlayers(Integer.parseInt(input));


                    System.out.println("Old playing time: " + ((Boardgame) selectedGame).getPlayingTime() + ", New playing time: ");
                    input = scan.nextLine();
                    if (!input.isEmpty()) ((Boardgame) selectedGame).setPlayingTime(Integer.parseInt(input));


                } else if (selectedGame instanceof Videogame) {

                    System.out.println("Old platform: " + ((Videogame) selectedGame).getPlatform() + ", New platform: ");
                    input = scan.nextLine();
                    if (!input.isEmpty()) ((Videogame) selectedGame).setPlatform(input);


                    System.out.println("Old hours in game: " + ((Videogame) selectedGame).getHoursInGame() + ", New hours in game: ");
                    input = scan.nextLine();
                    if (!input.isEmpty()) ((Videogame) selectedGame).setHoursInGame(Double.parseDouble(input));

                    System.out.println("Old genre: " + ((Videogame) selectedGame).getGenre() + ", New genre (COOP, SINGLEPLAYER, FPS): ");
                    input = scan.nextLine();
                    if (!input.isEmpty()) ((Videogame) selectedGame).setGenre(Genre.valueOf(input.toUpperCase()));

                }
            }

            System.out.println("Game modified successfully.");
            System.out.println(selectedGame);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Enter a valid value.");
        }
    }

    public void getStats() {
        List<Game> boardgames = this.myCollection.stream().filter(game -> game instanceof Boardgame).toList();
        List<Game> videogames = this.myCollection.stream().filter(game -> game instanceof Videogame).toList();
        OptionalDouble averagePrice = this.myCollection.stream().mapToDouble(game -> game.getPrice()).average();
        Game mostExpensiveGame = this.myCollection.stream().sorted(Comparator.comparing(Game::getPrice).reversed()).limit(1).toList().get(0);

        if (averagePrice.isPresent()) {
            System.out.println("Your collection contains " + boardgames.size() + " boardgames and " + videogames.size() + " videogames, for a total of " + myCollection.size() + " games.");
            System.out.println("The most expensive game is " + mostExpensiveGame.getTitle() + " with a price of " + mostExpensiveGame.getPrice());
            System.out.println("The average price of your collection is: " + averagePrice.getAsDouble());
        } else System.out.println("Your collection is empty.");
    }

    // METHODS
    public void addGame(Game game) {
        try {
            for (Game gameInCollection : this.myCollection) {
                if (gameInCollection.getId() == game.getId()) {
                    throw new AlreadyInCollection();
                }
            }
            this.myCollection.add(game);
            System.out.println("Game successfully added to your collection.");

        } catch (AlreadyInCollection e) {
            System.out.println(e.getMessage());
        }
    }

}
