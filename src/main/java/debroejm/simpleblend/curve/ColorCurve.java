package debroejm.simpleblend.curve;

import debroejm.simpleblend.util.ExponentialFunction;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * An individual color curve used for a single channel.
 */
public class ColorCurve {

    private static float clamp(final float value, final float lower, final float upper) {
        return value < lower ? lower : (value > upper ? upper : value);
    }

    /**
     * Data class for curve points. Provides public access for y values and weights.
     */
    public final class Point implements Cloneable {
        public final float value, weight;
        ExponentialFunction function = null;
        Point(final float value, final float weight) {
            this.value = value; this.weight = weight;
        }
        @Override public Point clone() {
            return new Point(value, weight);
        }
    }

    private final TreeMap<Float, Point> points;
    private ExponentialFunction baseFunction = null;

    public ColorCurve() {
        this.points = new TreeMap<>();
    }

    private ColorCurve(final TreeMap<Float, Point> points) {
        this.points = points;
    }

    /**
     * Adds a point on the color curve, with a default weight of 1.0f.
     *
     * @param x Float x position
     * @param y Float y position
     */
    public void addPoint(float x, float y) {
        addPoint(x, y, 1.0f);
    }

    /**
     * Adds a weighted point on the color curve. Points with higher weights will
     * have values gravitate towards them when calculated.
     *
     * @param x Float x position
     * @param y Float y position
     * @param weight Float weight value
     */
    public void addPoint(float x, float y, float weight) {
        float clampedX = clamp(x, 0.0f, 1.0f);
        float clampedY = clamp(y, 0.0f, 1.0f);

        // Mark the lower function dirty, so it updates
        final Map.Entry<Float, Point> lower = points.lowerEntry(clampedX);
        if(lower != null && lower.getValue().function != null) {
            lower.getValue().function = null;
        } else if(baseFunction != null) {
            baseFunction = null;
        }

        final Point point = new Point(clampedY, weight);
        points.put(clampedX, point);
    }

    /**
     * Clears all points from the color curve, and resets it
     * to an identity state.
     */
    public void clear() {
        points.clear();
    }

    /**
     * Retrieves an unmodifiable map of Points, where the keys are x values, and the
     * Point data objects contain the y values and weights.
     *
     * @return Unmodifiable map of point data
     */
    public Map<Float, Point> getPoints() {
        return Collections.unmodifiableMap(points);
    }

    /**
     * Clears a range of points from the color curve.
     *
     * @param start Float starting x position
     * @param end Float ending x position
     */
    public void clearRange(float start, float end) {
        points.keySet().removeIf((val) -> val >= start && val <= end);

        // Mark the lower function dirty, so it updates
        final Map.Entry<Float, Point> lower = points.lowerEntry(start);
        if(lower != null && lower.getValue().function != null) {
            lower.getValue().function = null;
        } else if(baseFunction != null) {
            baseFunction = null;
        }
    }

    /**
     * Blends a color using the color curve.
     *
     * @param strength Strength of the blend, between 0.0f and 1.0f inclusive
     * @param value Color color to blend
     * @return Blended color
     */
    public float blend(float strength, float value) {
        final float y = at(value);
        return value + (y-value) * strength;
    }

    /**
     * Retrieves the curves y value at the given x position.
     *
     * @param x Float x position, clamped between 0.0f and 1.0f
     * @return Float y position
     */
    public float at(float x) {
        float clampedX = x < 0.0f ? 0.0f : (x > 1.0f ? 1.0f : x);
        final Map.Entry<Float, Point> lower = points.lowerEntry(clampedX);
        ExponentialFunction function;
        if(lower == null) {
            if(baseFunction == null) {
                final Map.Entry<Float, Point> min = points.firstEntry();
                final float x2 = min == null ? 1.0f : min.getKey();
                final float y2 = min == null ? 1.0f : min.getValue().value;
                final float w2 = min == null ? 1.0f : min.getValue().weight;
                baseFunction = new ExponentialFunction(
                        0.0f, 0.0f,
                        x2, y2,
                        1.0f / w2
                );
            }
            function = baseFunction;
        } else {
            final Point point = lower.getValue();
            if(point.function == null) {
                final Map.Entry<Float, Point> next = points.higherEntry(clampedX);
                final float x2 = next == null ? 1.0f : next.getKey();
                final float y2 = next == null ? 1.0f : next.getValue().value;
                final float w2 = next == null ? 1.0f : next.getValue().weight;
                point.function = new ExponentialFunction(
                        lower.getKey(), point.value,
                        x2, y2,
                        point.weight / w2
                );
            }
            function = point.function;
        }
        return clamp(function.apply(clampedX), 0.0f, 1.0f);
    }

    public ColorCurve normalize() {

        if(points.isEmpty())
            return new ColorCurve();

        if(points.size() == 1) {
            final TreeMap<Float, Point> singleMap = new TreeMap<>();
            final Map.Entry<Float, Point> entry = points.firstEntry();
            singleMap.put(entry.getKey(), entry.getValue().clone());
            return new ColorCurve(singleMap);
        }

        final Map.Entry<Float, Point> minEntry = points.firstEntry();
        final Map.Entry<Float, Point> maxEntry = points.lastEntry();

        final float minX = minEntry.getKey();
        final float maxX = maxEntry.getKey();
        final float distanceX = maxX - minX;

        final TreeMap<Float, Point> normalizedPoints = new TreeMap<>();
        for(final Map.Entry<Float, Point> entry : points.entrySet()) {
            final float entryX = entry.getKey();
            final float normalizedX = (entryX - minX) / distanceX;
            normalizedPoints.put(normalizedX, entry.getValue().clone());
        }

        return new ColorCurve(normalizedPoints);
    }
}
