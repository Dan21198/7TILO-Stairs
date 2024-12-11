package osu;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class NPCClimbing {

    private final Map<String, BigInteger> memo = new HashMap<>();
    private static final int STEPS_PER_FLOOR = 5;


    public BigInteger countWays(int targetFloor, int targetStep) {
        return countWaysRecursive(targetFloor, targetStep);
    }


    private BigInteger countWaysRecursive(int floor, int step) {
        if (floor == 0 && step == 0) {
            return BigInteger.ONE;
        }

        if (floor < 0 || step < 0 || step > STEPS_PER_FLOOR) {
            return BigInteger.ZERO;
        }

        // Memoization key for the current state
        String key = floor + "," + step;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        BigInteger ways = BigInteger.ZERO;

        // Case 1: Climb 1 step from the same floor
        ways = ways.add(countWaysRecursive(floor, step - 1));

        // Case 2: Climb 2 steps from the same floor
        ways = ways.add(countWaysRecursive(floor, step - 2));

        // Case 3: Jump 3 steps from the same floor
        ways = ways.add(countWaysRecursive(floor, step - 3));

        // Case 4: Use an elevator
        if (step == 0) {
            ways = ways.add(countWaysRecursive(floor - 1, STEPS_PER_FLOOR));
        }

        memo.put(key, ways);

        return ways;
    }

}
