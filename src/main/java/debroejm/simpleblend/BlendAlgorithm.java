package debroejm.simpleblend;

/**
 * Interface representing a color blending algorithm.
 * A blending algorithm takes two float inputs representing
 * top and bottom color components to blend with, and returns
 * the resulting color component.
 *
 * In addition, the BlendAlgorithm interface specifies a
 * default method for blending whole 32-bit integer represented
 * colors.
 */
@FunctionalInterface
public interface BlendAlgorithm {

    /**
     * Utility method that ensures a given <code>color</code>
     * is between two bounds.
     *
     * @param value Float color to bounds-check
     * @param bound1 First float bounds; can be upper or lower
     * @param bound2 Second float bounds; can be upper or lower
     * @return Clamped float color
     */
    static float clamp(float value, float bound1, float bound2) {
        float lower = Math.min(bound1,bound2);
        float upper = Math.max(bound1,bound2);
        return Math.max(lower, Math.min(upper,value));
    }

    /**
     * Takes two color components for a specific color Channel
     * and blends them together, returning the result.
     *
     * @param channel Channel of the color to blend
     * @param top Float top component
     * @param bottom Float bottom component
     * @return Blended result
     */
    float blend(Channel channel, float top, float bottom);

    /**
     * Takes two full color representations and
     * blends them together, returning the result.
     *
     * @param top Color top instance
     * @param bottom Color bottom instance
     * @return Blended result
     */
    default Color blend(Color top, Color bottom) {
        float[] topArray = top.getRGBComponents();
        float[] bottomArray = bottom.getRGBComponents();
        float[] resultArray = new float[4];
        for (int i = 1; i < 4; i++) {
            float blendVal = blend(Channel.values()[i], topArray[i], bottomArray[i]);
            blendVal = bottomArray[i] + (blendVal-bottomArray[i]) * topArray[0];
            resultArray[i] = clamp(blendVal, 0.0f, 1.0f);
        }
        resultArray[0] = bottomArray[0];
        return new Color(
                resultArray[1],
                resultArray[2],
                resultArray[3],
                resultArray[0]
        );
    }
}
