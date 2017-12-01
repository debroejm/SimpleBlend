package debroejm.simpleblend.curve;

import debroejm.simpleblend.BlendAlgorithm;
import debroejm.simpleblend.Channel;

/**
 * Implementation of a BlendAlgorithm that uses color curves
 * to modify color by shifting it according to an equation.
 *
 * New color curves are created in an identity state, where
 * blending them with a color does not affect the color.
 */
public class ColorGraph implements BlendAlgorithm {

    private final ColorCurve[] curves;

    /**
     * Creates a new color curve in an identity state.
     */
    public ColorGraph() {
        int n = Channel.values().length;
        curves = new ColorCurve[n];
        for(int i = 0; i < n; i++) {
            curves[i] = new ColorCurve();
        }
    }

    public ColorGraph(final ColorCurve[] curves) {
        if(curves.length != Channel.values().length)
            throw new IllegalArgumentException("ColorCurve array does not match Channel count");
        for(final ColorCurve curve : curves) {
            if(curve == null)
                throw new NullPointerException();
        }
        this.curves = curves;
    }

    /**
     * Takes a color <code>color</code> and applies a color curve to it based on the
     * <code>strength</code> given, with a <code>strength</code> of <code>1.0f</code>
     * applying the full curve, and <code>0.0f</code> applying no curve.
     *
     * @param channel Channel of the color to blend
     * @param strength How strongly to blend
     * @param value The color component to blend
     * @return Blended result
     */
    @Override
    public float blend(Channel channel, float strength, float value) {
        return curves[channel.ordinal()].blend(strength, value);
    }

    /**
     * Retrieves all the Curves in this ColorCurve.
     *
     * @return Array of Curves
     */
    public ColorCurve[] getAllCurves() { return curves; }

    /**
     * Retrieves a Curve for a specific <code>channel</code>.
     *
     * @param channel Channel to get Curve for
     * @return Curve for specific channel
     */
    public ColorCurve getCurve(Channel channel) { return curves[channel.ordinal()]; }

    /**
     * Sets the Curve for a specific <code>channel</code>
     *
     * @param channel Channel to set Curve for
     * @param curve Curve to set
     */
    public void setCurve(Channel channel, ColorCurve curve) {
        if(curve == null)
            throw new NullPointerException();
        curves[channel.ordinal()] = curve;
    }

    /**
     * Resets all Curves to a linear line that does not modify
     * colors when applied. Equivalent to an Identity matrix in
     * linear algebra.
     */
    public void reset() {
        for(ColorCurve curve : curves)
            curve.clear();
    }

    public ColorGraph normalize() {
        final ColorCurve[] curves = new ColorCurve[Channel.values().length];
        for(final Channel channel : Channel.values()) {
            curves[channel.ordinal()] = this.curves[channel.ordinal()].normalize();
        }
        return new ColorGraph(curves);
    }



    // ****************
    //  Static Methods
    // ****************

    public static ColorGraph scanFromData(int[] data) {

        return null;
    }
}
