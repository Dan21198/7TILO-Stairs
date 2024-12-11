package osu;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        NPCClimbing npcClimbing = new NPCClimbing();

        int targetFloor = 25;
        int targetStep = 4;

        BigInteger ways = npcClimbing.countWays(targetFloor, targetStep);

        System.out.println("Number of ways to reach floor " + targetFloor + ", step " + targetStep + ": " + ways);
    }
}