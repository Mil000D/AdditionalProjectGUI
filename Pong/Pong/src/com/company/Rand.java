package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Rand extends Random {
    public int randomVXVY() {
        List<Integer> list = Arrays.asList(-1, 1);
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public int randomY(int height) {
        List<Integer> list = new ArrayList<>();
        for (int i = 50; i < height - 50; i += 20) {
            list.add(i);
        }
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}