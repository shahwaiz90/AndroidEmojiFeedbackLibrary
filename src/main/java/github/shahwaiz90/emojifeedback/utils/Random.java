package github.shahwaiz90.emojifeedback.utils;

/**
 * Created by Ahmad Shahwaiz on 11/9/2017.
 * Github: https://www.github.com/shahwaiz90
 * LinkedIn: https://www.linkedin.com/in/ahmadshahwaiz/
 */

public class Random {
    private static final java.util.Random RANDOM = new java.util.Random();

    public float getRandom(float lower, float upper) {
        float min = Math.min(lower, upper);
        float max = Math.max(lower, upper);
        return getRandom(max - min) + min;
    }

    public float getRandom(float upper) {
        return RANDOM.nextFloat() * upper;
    }

    public int getRandom(int upper) {
        return RANDOM.nextInt(upper);
    }

}