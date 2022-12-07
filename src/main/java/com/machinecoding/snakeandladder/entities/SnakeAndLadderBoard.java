package com.machinecoding.snakeandladder.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SnakeAndLadderBoard {

    private List<Snake> snakes;

    private List<Ladder> ladders;

    public static final int SIZE_OF_THE_BOARD = 100;

    @Builder.Default
    private List<Player> players = new ArrayList<>(2);

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }
}
