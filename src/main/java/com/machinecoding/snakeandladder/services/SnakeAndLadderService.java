package com.machinecoding.snakeandladder.services;

import com.machinecoding.snakeandladder.entities.Ladder;
import com.machinecoding.snakeandladder.entities.Player;
import com.machinecoding.snakeandladder.entities.Snake;
import com.machinecoding.snakeandladder.entities.SnakeAndLadderBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SnakeAndLadderService {

    public static final String PLAYER = "Player ";
    private final SnakeAndLadderBoard snakeAndLadderBoard;
    private final Logger logger = LoggerFactory.getLogger(SnakeAndLadderService.class);

    public SnakeAndLadderService() {
        snakeAndLadderBoard = new SnakeAndLadderBoard();
    }

    public void setPlayers(List<String> names) {
        names.forEach(snakeAndLadderBoard::addPlayer);
    }

    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        snakeAndLadderBoard.setLadders(ladders);
    }

    private void movePlayer(Player player, int diceValue) {
        int currentPositionOfThePlayer = player.getPositionOnTheBoard();
        if (currentPositionOfThePlayer + diceValue <= 100) {
            int newPositionOfThePlayer = currentPositionOfThePlayer + diceValue;
            logger.info(PLAYER + player.getNameOfThePlayer() + " moved from position " +
                    player.getPositionOnTheBoard() + " to " + newPositionOfThePlayer);
            setPositionOfPlayerByGoingThroughSnakesAndLadders(player, newPositionOfThePlayer);
        }
    }

    private void setPositionOfPlayerByGoingThroughSnakesAndLadders(Player player, int currentPositionOfThePlayer) {
        int prevPositionOfThePlayer;

        do {
            prevPositionOfThePlayer = currentPositionOfThePlayer;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getHead() == currentPositionOfThePlayer) {
                    logger.info(PLAYER + player.getNameOfThePlayer() +
                            " bitten by snake and moved from position " + currentPositionOfThePlayer +
                            " to " + snake.getTail());
                    currentPositionOfThePlayer = snake.getTail();
                }
            }

            for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
                if (ladder.getStart() == currentPositionOfThePlayer) {
                    logger.info(PLAYER + player.getNameOfThePlayer() +
                            " climbed a ladder and moved from position " + currentPositionOfThePlayer +
                            " to " + ladder.getEnd());
                    currentPositionOfThePlayer = ladder.getEnd();
                }
            }
        } while (prevPositionOfThePlayer != currentPositionOfThePlayer);

        player.setPositionOnTheBoard(currentPositionOfThePlayer);

    }

    public void playGame() {
        int noOfPlayers = snakeAndLadderBoard.getPlayers().size();
        Player player;
        int i = 0;
        int diceValue;
        do {
            diceValue = DiceService.rollDice();
            player = snakeAndLadderBoard.getPlayers().get(i);
            logger.info(PLAYER + player.getNameOfThePlayer() + " rolled " + diceValue + " positions");
            movePlayer(player, diceValue);
            i = (i + 1) % noOfPlayers;
        } while (!isGameCompleted(player));
    }

    private boolean isGameCompleted(Player player) {
        if (player.getPositionOnTheBoard() == SnakeAndLadderBoard.SIZE_OF_THE_BOARD) {
            logger.info(player.getNameOfThePlayer() + " Won the game :)");
            return true;
        }
        return false;
    }

}
