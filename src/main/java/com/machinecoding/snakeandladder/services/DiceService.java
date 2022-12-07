package com.machinecoding.snakeandladder.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiceService {

    private static final Random random = new Random();
    public static int rollDice() {
        return random.nextInt(6) + 1;
    }

}
