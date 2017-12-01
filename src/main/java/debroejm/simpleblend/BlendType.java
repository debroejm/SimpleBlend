package debroejm.simpleblend;

/**
 * Enumeration representing common algorithms used
 * for BlendAlgorithm.
 */
public enum BlendType implements BlendAlgorithm {

    /**
     * Multiply Blending Algorithm. Multiplies two values and returns the result.
     */
    MULTIPLY     ((c,a,b) -> a*b),

    /**
     * Screen Blending Algorithm. Multiplies the inverse of two values, and returns
     * the inverse of the result.
     */
    SCREEN       ((c,a,b) -> 1.0f-(1.0f-a)*(1.0f-b)),

    /**
     * Overlay Blending Algorithm. Performs multiplication if the base color is less
     * than 0.5f, and screens if the base layer is more than 0.5f.
     */
    OVERLAY      ((c,a,b) -> b < 0.5f ? (2*a*b) : (1.0f-2.0f*(1.0f-a)*(1.0f-b))),

    /**
     * Darken Blending Algorithm. Returns the minimum of two values.
     */
    DARKEN       ((c,a,b) -> Math.min(a,b)),

    /**
     * Lighten Blending Algorithm. Returns the maximum of two values.
     */
    LIGHTEN      ((c,a,b) -> Math.max(a,b)),

    /**
     * Difference Blending Algorithm. Returns the difference between two values.
     */
    DIFFERENCE   ((c,a,b) -> Math.max(a,b) - Math.min(a,b)),

    /**
     * Plus Lighter Blending Algorithm. Adds two values together and returns the result.
     */
    PLUS_LIGHTER ((c,a,b) -> a+b),

    /**
     * Plus Darker Blending Algorithm. Adds two values together, subtracts one, and
     * returns the result.
     */
    PLUS_DARKER  ((c,a,b) -> a+b-1),

    /**
     * Divide Blending Algorithm. Divides the base color by the top color, and returns
     * the result.
     */
    DIVIDE       ((c,a,b) -> b/a)
    ;

    /**
     * The BlendAlgorithm lambda of this BlendType.
     */
    private final BlendAlgorithm algorithm;

    BlendType(BlendAlgorithm algorithm) {
        this.algorithm = algorithm;
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
    @Override
    public float blend(Channel channel, float top, float bottom) {
        return channel == Channel.ALPHA ? bottom : algorithm.blend(channel, top, bottom);
    }
}