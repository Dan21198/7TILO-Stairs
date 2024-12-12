package osu;

import java.math.BigInteger;

public class NPCClimbing {

    private static final int STEPS_PER_FLOOR = 5;

    public BigInteger countWays(int targetFloor, int targetStep) {
        if (targetFloor < 0 || targetStep < 0 || targetStep > STEPS_PER_FLOOR) {
            return BigInteger.ZERO;
        }

        BigInteger[] previous = new BigInteger[STEPS_PER_FLOOR + 1];
        BigInteger[] current = new BigInteger[STEPS_PER_FLOOR + 1];

        // Base case: there's exactly one way to be at (0, 0)
        previous[0] = BigInteger.ONE;
        for (int step = 1; step <= STEPS_PER_FLOOR; step++) {
            previous[step] = BigInteger.ZERO;
        }

        for (int floor = 0; floor <= targetFloor; floor++) {
            for (int step = 0; step <= STEPS_PER_FLOOR; step++) {
                if (floor == 0 && step == 0) {
                    current[step] = BigInteger.ONE;
                } else {
                    BigInteger ways = BigInteger.ZERO;

                    // Climb 1 step from the same floor
                    if (step - 1 >= 0) {
                        ways = ways.add(current[step - 1]);
                    }

                    // Climb 2 steps from the same floor
                    if (step - 2 >= 0) {
                        ways = ways.add(current[step - 2]);
                    }

                    // Jump 3 steps from the same floor
                    if (step - 3 >= 0) {
                        ways = ways.add(current[step - 3]);
                    }

                    // Use an elevator from the previous floor
                    if (step == 0 && floor > 0) {
                        ways = ways.add(previous[STEPS_PER_FLOOR]);
                    }

                    current[step] = ways;
                }
            }

            BigInteger[] temp = previous;
            previous = current;
            current = temp;
        }

        return previous[targetStep];
    }
}
