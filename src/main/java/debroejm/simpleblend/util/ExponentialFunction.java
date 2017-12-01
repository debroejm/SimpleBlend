package debroejm.simpleblend.util;

import java.util.function.Function;

/**
 * Utility class representing an exponential function. Used for Color Curves.
 */
public class ExponentialFunction implements Function<Float, Float> {

    private final float exponential;
    private final float slope;
    private final float intercept;

    /**
     * Creates a new function using two points and an exponential modifier.
     *
     * @param x1 x coordinate of first point
     * @param y1 y coordinate of first point
     * @param x2 x coordinate of second point
     * @param y2 y coordinate of second point
     * @param exponential exponential modifier; 1.0f is linear
     */
    public ExponentialFunction(
            final float x1,
            final float y1,
            final float x2,
            final float y2,
            final float exponential
    ) {
        final float xe1 = (float) Math.pow(x1, exponential);
        final float xe2 = (float) Math.pow(x2, exponential);
        this.exponential = exponential;
        this.slope = (y2 - y1) / (xe2 - xe1);
        this.intercept = y1 - this.slope * xe1;
    }

    /**
     * Retrieves the y-value of this function from a given x-value.
     *
     * @param x x coordinate to evaluate at
     * @return calculated y coordinate
     */
    public Float apply(Float x) {
        return (float) (slope * Math.pow(x, exponential) + intercept);
    }
}
