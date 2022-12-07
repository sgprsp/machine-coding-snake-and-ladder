package com.machinecoding.snakeandladder.entities;

import lombok.*;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @NonNull
    private String nameOfThePlayer;

    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Builder.Default
    private int positionOnTheBoard = 0;

}
