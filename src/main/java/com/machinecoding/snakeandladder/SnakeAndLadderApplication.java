package com.machinecoding.snakeandladder;

import com.machinecoding.snakeandladder.entities.Ladder;
import com.machinecoding.snakeandladder.entities.Snake;
import com.machinecoding.snakeandladder.services.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnakeAndLadderApplication {

    public static void main(String[] args) {
        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of snakes on the board: ");
        int noOfSnakes = scanner.nextInt();
        System.out.println("Enter the head and tail of the snakes: ");
        List<Snake> snakes = new ArrayList<>();
        for (int i = 0; i < noOfSnakes; i++) {
            snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
        }
        snakeAndLadderService.setSnakes(snakes);

        System.out.println("Enter the number of ladders on the board: ");
        int noOfLadders = scanner.nextInt();
        System.out.println("Enter the start and end position of the ladders: ");
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < noOfLadders; i++) {
            ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
        }
        snakeAndLadderService.setLadders(ladders);

        System.out.println("Enter the number of players on the board: ");
        int noOfPlayers = scanner.nextInt();
        List<String> players = new ArrayList<>();
        System.out.println("Enter the name of the players: ");
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(scanner.next());
        }
        snakeAndLadderService.setPlayers(players);

        snakeAndLadderService.playGame();
    }

}
