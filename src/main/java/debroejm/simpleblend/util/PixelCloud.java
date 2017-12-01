package debroejm.simpleblend.util;

import debroejm.simpleblend.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility class representing a cloud of 'similar' points. A PixelCloud is created with
 * a specified dimensionality, and all points it stores must live in that dimension.
 */
public class PixelCloud implements Comparable<PixelCloud> {

    public static final int COLOR_DIMS = 3;
    public static final int POS_DIMS = 2;
    public static final int TOTAL_DIMS = COLOR_DIMS + POS_DIMS;

    private final List<Color> points = new LinkedList<>();

    private float[] cachedSum_color;
    private float[] average_color;
    private float[] min_color;
    private float[] max_color;

    /**
     * Creates an empty PixelCloud.
     */
    public PixelCloud() {
        clear();
    }

    /**
     * Clears all points from this PixelCloud, resetting it to empty.
     */
    public void clear() {
        points.clear();
        cachedSum_color = new float[COLOR_DIMS];
        average_color = new float[COLOR_DIMS];
        min_color = new float[COLOR_DIMS];
        max_color = new float[COLOR_DIMS];
        for(int i = 0; i < COLOR_DIMS; i++) {
            cachedSum_color[i] = 0.0f;
            average_color[i]   = 0.0f;
            min_color[i]       = Float.MAX_VALUE;
            max_color[i]       = Float.MIN_VALUE;
        }
    }

    /**
     * Determines whether or not this PixelCloud has any points stored.
     *
     * @return True if the PixelCloud is empty, false otherwise
     */
    public boolean isEmpty() {
        return points.isEmpty();
    }

    /**
     * Retrieves the amount of points stored in this PixelCloud.
     *
     * @return Integral number of points stored
     */
    public int size() {
        return points.size();
    }

    /**
     * Adds a pixel to this PixelCloud. Does not check if the color is similar before adding.
     * Minimum, maximum, and average values are recalculated from cached intermediary values.
     *
     * @param color Color value to add
     */
    public void add(final Color color) {

        points.add(color);

        final float[] colorValues = color.getRGBComponents();
        for(int i = 0; i < COLOR_DIMS; i++) {
            cachedSum_color[i] += colorValues[i+1];
            average_color[i] = cachedSum_color[i] / points.size();
            if(colorValues[i+1] < min_color[i])
                min_color[i] = colorValues[i+1];
            if(colorValues[i+1] > max_color[i])
                max_color[i] = colorValues[i+1];
        }
    }

    /**
     * Checks if a color is 'similar' enough to be added to this PixelCloud. Similarity is defined
     * as being within a certain epsilon of this PixelCloud's average and being within half of that
     * epsilon beyond the PixelCloud's current minimum and maximum values.
     *
     * @param r Float red value
     * @param g Float green value
     * @param b Float blue value
     * @param epsilon Float epsilon value to determine scale of similarity
     * @return True if the given point is 'similar' to the PixelCloud, false otherwise
     */
    public boolean isSimilar(final float r, final float g, final float b, final float epsilon) {

        if(isEmpty())
            return true;

        if(!isSimilarSingle(r, 0, epsilon))
            return false;
        if(!isSimilarSingle(g, 1, epsilon))
            return false;
        if(!isSimilarSingle(b, 2, epsilon))
            return false;

        return true;
    }

    private boolean isSimilarSingle(final float value, final int index, final float epsilon) {
        return ( (Math.abs(average_color[index] - value) < epsilon)
                && ((min_color[index] - value) < (epsilon*0.5f))
                && ((value - max_color[index]) < (epsilon*0.5f)));
    }

    /**
     * Checks if a color is 'similar' enough to be added to this PixelCloud. Similarity is defined
     * as being within a certain epsilon of this PixelCloud's average and being within half of that
     * epsilon beyond the PixelCloud's current minimum and maximum values.
     *
     * @param color Color value
     * @param epsilon Float epsilon value to determine scale of similarity
     * @return True if the given point is 'similar' to the PixelCloud, false otherwise
     */
    public boolean isSimilar(final Color color, final float epsilon) {
        return isSimilar(color.getRed(), color.getGreen(), color.getBlue(), epsilon);
    }

    /**
     * Retrieves the current minimum of the PixelCloud, determined per element (not per point).
     *
     * @return Float array representing minimum point values
     */
    public Color getMinimumColor() {
        return new Color(min_color[0], min_color[1], min_color[2]);
    }

    /**
     * Retrieves the current maximum of the PixelCloud, determined per element (not per point).
     *
     * @return Float array representing maximum point values
     */
    public Color getMaximumColor() {
        return new Color(max_color[0], max_color[1], max_color[2]);
    }

    /**
     * Retrieves the current average of the PixelCloud, determined per element (not per point).
     *
     * @return Float array representing average point values
     */
    public Color getAverageColor() {
        return new Color(average_color[0], average_color[1], average_color[2]);
    }

    public int getSize() {
        return points.size();
    }

    @Override
    public int compareTo(PixelCloud other) {
        return points.size() - other.points.size();
    }
}
