package danielerusso;

import danielerusso.entities.Boardgame;
import danielerusso.entities.Collection;
import danielerusso.entities.Game;
import danielerusso.entities.Videogame;
import danielerusso.entities.enums.Genre;

import java.util.Random;
import java.util.Scanner;


public class Application {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        // GIOCHI GIÀ INSERITI NELLA COLLEZIONE
        Videogame tlou = new Videogame(125762, "The Last of Us - Part I", 2022, 79.99, "PS5", 100.5, Genre.SINGLEPLAYER);
        Boardgame azul = new Boardgame(230802, "Azul", 2017, 50, 4, 45);
        Boardgame duel = new Boardgame(173346, "7 Wonders Duel", 2015, 30, 2, 30);

        // GIOCHI DA INSERIRE NELLA COLLEZIONE
        Boardgame brass = new Boardgame(187342, "Brass Birmingham", 2018, 70, 4, 120);
        Videogame cod = new Videogame(987651, "Call of Duty: Black Ops 6", 2024, 99.99, "Xbox One", 247, Genre.FPS);
        Videogame itt = new Videogame(987651, "It Takes Two", 2021, 59.99, "PC", 15.5, Genre.COOP);

        Collection myCollection = new Collection();

        myCollection.addGame(tlou);
        myCollection.addGame(azul);
        myCollection.addGame(duel);


        boolean isDone = false;

        try {
            while (!isDone) {
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("What do you want to do with your collection?\n1 - Add a game.\n2 - Search by ID.\n3 - Search by price.\n4 - Search by numbers of players.\n5 - Remove a game.\n6 - Update a game.\n7 - General Stats.\n8 - Show your collection.\n0 - Exit.");


                try {
                    int input = Integer.parseInt(scan.nextLine());
                    switch (input) {
                        case 1: {
                            System.out.println("First of all, is the game a videogame or a boardgame ?");
                            String gameType = scan.nextLine().toLowerCase();
                            if (!gameType.equals("videogame") && !gameType.equals("boardgame")) {
                                System.out.println("Enter a valid type of game.");
                                break;
                            }
                            // long gameId = rand.nextLong(10000, 1000000);
                            System.out.println("Enter one unique id greater than 1000: ");
                            long gameId = Long.parseLong(scan.nextLine());
                            if (gameId < 1000) {
                                throw new NumberFormatException();
                            }
                            System.out.println("Enter the title: ");
                            String gameTitle = (scan.nextLine());
                            System.out.println("Enter the year: ");
                            int gameYear = Integer.parseInt(scan.nextLine());
                            System.out.println("Enter the price: ");
                            double gamePrice = Double.parseDouble(scan.nextLine());

                            Game newGame = null;
                            if (gameType.equals("videogame")) {
                                System.out.println("Enter the platform: ");
                                String gamePlatform = (scan.nextLine());
                                System.out.println("Enter the game hours: ");
                                double gameHours = Double.parseDouble((scan.nextLine()));
                                System.out.println("Enter the genre (COOP, FPS, SINGLEPLAYER): ");
                                Genre gameGenre = Genre.valueOf(scan.nextLine().toUpperCase());
                                if (!gameGenre.equals(Genre.COOP) && !gameGenre.equals(Genre.SINGLEPLAYER) && !gameGenre.equals(Genre.FPS)) {
                                    throw new NumberFormatException();
                                }
                                newGame = new Videogame(gameId, gameTitle, gameYear, gamePrice, gamePlatform, gameHours, gameGenre);
                            } else {
                                System.out.println("Enter number of players: ");
                                int gameNumOfPlayers = Integer.parseInt(scan.nextLine());
                                System.out.println("Enter playing time: ");
                                int gamePlayingTime = Integer.parseInt(scan.nextLine());
                                newGame = new Boardgame(gameId, gameTitle, gameYear, gamePrice, gameNumOfPlayers, gamePlayingTime);
                            }


                            myCollection.addGame(newGame);
                            myCollection.searchById(gameId);


                        }
                        break;
                        case 2: {
                            System.out.println("Enter the id of the game you want search for: ");
                            long id = Long.parseLong(scan.nextLine());
                            myCollection.searchById(id);
                        }
                        break;
                        case 3: {
                            System.out.println("Enter a price: ");
                            double price = Double.parseDouble(scan.nextLine());
                            myCollection.searchByPrice(price);
                        }
                        break;
                        case 4: {
                            System.out.println("Enter a number of players: ");
                            int num = Integer.parseInt(scan.nextLine());
                            myCollection.searchByNumOfPlayers(num);
                        }
                        break;
                        case 5: {
                            System.out.println("Enter the id of the game you want to delete: ");
                            long id = Long.parseLong(scan.nextLine());
                            myCollection.deleteById(id);
                        }
                        break;
                        case 6: {
                            System.out.println("Enter the id of the game you want to update: ");
                            long id = Long.parseLong(scan.nextLine());
                            myCollection.updateGameById(id);
                        }
                        break;
                        case 7: {
                            myCollection.getStats();
                        }
                        break;
                        case 8: {
                            myCollection.getCollection();
                        }
                        break;
                        case 0:
                            isDone = true;
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Format.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("You must enter a valid number.");
        }

        scan.close();


    }
}
