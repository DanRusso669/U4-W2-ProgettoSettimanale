package danielerusso;

import danielerusso.entities.Boardgame;
import danielerusso.entities.Collection;
import danielerusso.entities.Videogame;
import danielerusso.entities.enums.Genre;

import java.util.Scanner;


public class Application {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Boardgame azul = new Boardgame(230802, "Azul", 2017, 50, 4, 45);
        Boardgame brass = new Boardgame(224517, "Brass Birmingham", 2018, 70, 4, 120);
        Boardgame duel = new Boardgame(173346, "7 Wonders Duel", 2015, 30, 2, 30);

        Videogame tlou = new Videogame(125762, "The Last of Us - Part I", 2022, 79.99, "PS5", 100.5, Genre.SINGLEPLAYER);
        Videogame cod = new Videogame(987651, "Call of Duty: Black Ops 6", 2024, 99.99, "Xbox One", 247, Genre.FPS);
        Videogame itt = new Videogame(987651, "It Takes Two", 2021, 59.99, "PC", 15.5, Genre.COOP);


        // System.out.println("What do you want to do with your collection?\n1 - Add a game.\n2 - Search by ID.\n3 - Search by price.\n4 - Search by numbers of players.\n5 - Remove a game.\n6 - Update a game.\n7 - General Stats.");
        Collection.addGame(tlou);
        Collection.addGame(azul);
        Collection.addGame(duel);

        //Collection.searchById(2);

        //Collection.searchByPrice(10);

        // Collection.searchByNumOfPlayers(2);

        // System.out.println(Collection.myCollection);
        // Collection.deleteById(125762);
        //  System.out.println(Collection.myCollection);

        Collection.updateGameById(125762);

//        Collection.getStats();


    }
}
