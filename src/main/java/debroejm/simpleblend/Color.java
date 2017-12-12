package debroejm.simpleblend;

import java.io.Serializable;

/**
 * Utility color class. For just in case you don't have access to java.awt
 * (looking at you Android).
 *
 * Color objects statically represent a certain color value. Once created,
 * the data of a Color object cannot change, so it is never necessary to
 * make a deep copy.
 */
public final class Color implements Comparable<Color>, Serializable {

    static float clamp(float value, float min, float max) {
        return value < min ? min : (value > max ? max : value);
    }

    private static final float EPSILON = 0.001f;



    // ***************
    //  Common Colors
    // ***************

    public static final Color CLEAR         = Color.ofRGB(0);

    public static final Color WHITE         = Color.ofRGB(0xFFFFFFFF);
    public static final Color LIGHT_GRAY    = Color.ofRGB(0xFFC0C0C0);
    public static final Color GRAY          = Color.ofRGB(0xFF808080);
    public static final Color DARK_GRAY     = Color.ofRGB(0xFF404040);
    public static final Color BLACK         = Color.ofRGB(0xFF000000);

    public static final Color LIGHT_GREY    = LIGHT_GRAY;
    public static final Color GREY          = GRAY;
    public static final Color DARK_GREY     = DARK_GRAY;

    public static final Color RED           = Color.ofRGB(0xFFFF0000);
    public static final Color GREEN         = Color.ofRGB(0xFF00FF00);
    public static final Color BLUE          = Color.ofRGB(0xFF0000FF);
    public static final Color YELLOW        = Color.ofRGB(0xFFFFFF00);
    public static final Color CYAN          = Color.ofRGB(0xFF00FFFF);
    public static final Color PURPLE        = Color.ofRGB(0xFFFF00FF);



    // ***************************
    //  Static Conversion Methods
    // ***************************

    /**
     * Converts float color components into an integer color.
     * Also functions for HSB.
     *
     * @param r Red/hue float component
     * @param g Green/saturation float component
     * @param b Blue/brightness float component
     * @param a Alpha float component
     * @return 32-bit ARGB or AHSB integer value
     */
    public static int convertColorIntFromFloat(float r, float g, float b, float a) {
        return  ((int)(clamp(a, 0.0f, 1.0f) * 255) & 0xFF) << 24 |
                ((int)(clamp(r, 0.0f, 1.0f) * 255) & 0xFF) << 16 |
                ((int)(clamp(g, 0.0f, 1.0f) * 255) & 0xFF) << 8 |
                ((int)(clamp(b, 0.0f, 1.0f) * 255) & 0xFF);
    }

    /**
     * Converts float color components into an integer color.
     * Also functions for HSB.
     * Alpha is assumed to be full opaque (1.0f).
     *
     * @param r Red/hue float component
     * @param g Green/saturation float component
     * @param b Blue/brightness float component
     * @return 32-bit ARGB or AHSB integer value
     */
    public static int convertColorIntFromFloat(float r, float g, float b) {
        return convertColorIntFromFloat(r,g,b,1.0f);
    }

    /**
     * Converts float color components into an integer color.
     * Also functions for HSB.
     *
     * @param color Float array of color components (assumed ARGB or AHSB format)
     * @return 32-bit ARGB or AHSB integer value
     */
    public static int convertColorIntFromFloat(float[] color) {
        return convertColorIntFromFloat(color[1], color[2], color[3], color[0]);
    }

    /**
     * Retrieves the individual color component from an integer color.
     * Also functions for HSB.
     *
     * @param color 32-bit ARGB integer color value
     * @return Float array of the format { alpha, red/hue, green/saturation, blue/brightness }
     */
    public static float[] convertColorArrayFromInt(int color) {
        return new float[]{
                (color >> 24 & 0xFF) / 255.0f,
                (color >> 16 & 0xFF) / 255.0f,
                (color >> 8 & 0xFF) / 255.0f,
                (color & 0xFF) / 255.0f
        };
    }

    /**
     * Converts RGB color components into HSB components. Alpha is assumed to be 1.0f.
     *
     * @param red Float red value
     * @param green Float green value
     * @param blue Float blue value
     * @return Float array of the format { alpha, hue, saturation, brightness }
     */
    public static float[] convertRGBtoHSB(float red, float green, float blue) {
        return convertRGBtoHSB(new float[]{ 1.0f, red, green, blue });
    }

    /**
     * Converts RGB color components into HSB components.
     *
     * @param red Float red value
     * @param green Float green value
     * @param blue Float blue value
     * @param alpha Float alpha value
     * @return Float array of the format { alpha, hue, saturation, brightness }
     */
    public static float[] convertRGBtoHSB(float red, float green, float blue, float alpha) {
        return convertRGBtoHSB(new float[]{ alpha, red, green, blue });
    }

    /**
     * Converts RGB color components into HSB components.
     *
     * @param argb Float array of color components (assumed ARGB format)
     * @return Float array of the format { alpha, hue, saturation, brightness }
     * @throws IllegalArgumentException if given float array is smaller than four values
     */
    public static float[] convertRGBtoHSB(float[] argb) throws IllegalArgumentException {
        if(argb.length < 4)
            throw new IllegalArgumentException("RGB float array must have 4 components");

        float max = -0.1f, min = 1.1f;
        int mI = -1;
        for(int i = 1; i < 4; i++) {
            if(argb[i] < 0f || argb[i] > 1f)
                throw new IllegalArgumentException("RGB components must be between 0.0f and 1.0f, inclusive");

            if(argb[i] < min) min = argb[i];
            if(argb[i] > max) {
                max = argb[i];
                mI = i - 1;
            }
        }

        float ahsb[] = new float[4];

        float delta = max - min;
        ahsb[3] = max;
        if(Math.abs(delta) < EPSILON) {
            ahsb[1] = ahsb[2] = 0f;
        } else {
            ahsb[2] = delta / max;
            float first = argb[((mI+1)%3)+1];
            float second = argb[((mI+2)%3)+1];
            ahsb[1] = (first - second) / delta + ( (mI == 0 && first < second) ? 3 : mI) * 2f;
            ahsb[1] /= 6f;
        }
        ahsb[0] = argb[0];

        return ahsb;
    }

    /**
     * Converts HSB components into RGB color components. Alpha is assumed to be 1.0f.
     *
     * @param hue Float red value
     * @param saturation Float green value
     * @param brightness Float blue value
     * @return Float array of the format { alpha, red, green, blue }
     */
    public static float[] convertHSBtoRGB(float hue, float saturation, float brightness) {
        return convertHSBtoRGB(new float[]{ 1.0f, hue, saturation, brightness });
    }

    /**
     * Converts HSB components into RGB color components.
     *
     * @param hue Float red value
     * @param saturation Float green value
     * @param brightness Float blue value
     * @param alpha Float alpha value
     * @return Float array of the format { alpha, red, green, blue }
     */
    public static float[] convertHSBtoRGB(float hue, float saturation, float brightness, float alpha) {
        return convertHSBtoRGB(new float[]{ alpha, hue, saturation, brightness });
    }

    /**
     * Converts HSB components into RGB color components.
     *
     * @param ahsb Float array of HSB components (assumed AHSB format)
     * @return Float array of the format { alpha, red, green, blue }
     * @throws IllegalArgumentException if given float array is smaller than four values
     */
    public static float[] convertHSBtoRGB(float[] ahsb) throws IllegalArgumentException {
        if(ahsb.length < 4)
            throw new IllegalArgumentException("HSL float array must have 4 components");

        float argb[] = new float[4];

        if(ahsb[2] < EPSILON) {
            argb[1] = argb[2] = argb[3] = ahsb[3];
        } else {
            float f = ahsb[1] * 6.0f;
            if(f >= 6.0f) f -= 6.0f;
            int i = (int)f;
            f = f - i;
            float p = ahsb[3] * (1.0f - ahsb[2]);
            float q = ahsb[3] * (1.0f - ahsb[2] * f);
            float t = ahsb[3] * (1.0f - ahsb[2] * (1.0f - f));
            switch(i) {
                case 0:
                    argb[1] = ahsb[3];
                    argb[2] = t;
                    argb[3] = p;
                    break;
                case 1:
                    argb[1] = q;
                    argb[2] = ahsb[3];
                    argb[3] = p;
                    break;
                case 2:
                    argb[1] = p;
                    argb[2] = ahsb[3];
                    argb[3] = t;
                    break;
                case 3:
                    argb[1] = p;
                    argb[2] = q;
                    argb[3] = ahsb[3];
                    break;
                case 4:
                    argb[1] = t;
                    argb[2] = p;
                    argb[3] = ahsb[3];
                    break;
                case 5:
                default:
                    argb[1] = ahsb[3];
                    argb[2] = p;
                    argb[3] = q;
                    break;
            }
        }
        argb[0] = ahsb[0];

        return argb;
    }



    // **********************
    //  Class Implementation
    // **********************

    private final int rgb;
    private final int hsb;

    private Color(int rgb, int hsb) {
        this.rgb = rgb;
        this.hsb = hsb;
    }

    /**
     * Creates a Color representing the given integer color.
     *
     * @param rgb 32-bit ARGB integer color.
     * @return Color object
     */
    public static Color ofRGB(int rgb) {
        float[] argb = convertColorArrayFromInt(rgb);
        float[] ahsb = convertRGBtoHSB(argb);
        int hsb = convertColorIntFromFloat(ahsb);
        return new Color(rgb, hsb);
    }

    /**
     * Creates a Color representing the given color components.
     *
     * @param r Float red value
     * @param g Float green value
     * @param b Float blue value
     * @return Color object
     */
    public static Color ofRGB(float r, float g, float b) {
        return ofRGB(r, g, b, 1.0f);
    }

    /**
     * Creates a Color representing the given color components.
     *
     * @param r Float red value
     * @param g Float green value
     * @param b Float blue value
     * @param a Float alpha value
     * @return Color object
     */
    public static Color ofRGB(float r, float g, float b, float a) {
        int rgb = convertColorIntFromFloat(r, g, b, a);
        float[] ahsb = convertRGBtoHSB(r, g, b, a);
        int hsb = convertColorIntFromFloat(ahsb);
        return new Color(rgb, hsb);
    }

    /**
     * Creates a Color representing the given integer color.
     *
     * @param hsb 32-bit AHSB integer color.
     * @return Color object
     */
    public static Color ofHSB(int hsb) {
        float[] ahsb = convertColorArrayFromInt(hsb);
        float[] argb = convertHSBtoRGB(ahsb);
        int rgb = convertColorIntFromFloat(argb);
        return new Color(rgb, hsb);
    }

    /**
     * Creates a Color representing the given color components.
     *
     * @param h Float hue value
     * @param s Float saturation value
     * @param b Float brightness value
     * @return Color object
     */
    public static Color ofHSB(float h, float s, float b) {
        return ofHSB(h, s, b, 1.0f);
    }

    /**
     * Creates a Color representing the given color components.
     *
     * @param h Float hue value
     * @param s Float saturation value
     * @param b Float brightness value
     * @param a Float alpha value
     * @return Color object
     */
    public static Color ofHSB(float h, float s, float b, float a) {
        int hsb = convertColorIntFromFloat(h, s, b, a);
        float[] argb = convertHSBtoRGB(h, s, b, a);
        int rgb = convertColorIntFromFloat(argb);
        return new Color(rgb, hsb);
    }

    public int getRGB() { return rgb; }
    public int getHSB() { return hsb; }

    public float getAlpha()      { return (rgb >> 24 & 0xFF) / 255.0f; }
    public float getRed()        { return (rgb >> 16 & 0xFF) / 255.0f; }
    public float getGreen()      { return (rgb >>  8 & 0xFF) / 255.0f; }
    public float getBlue()       { return (rgb       & 0xFF) / 255.0f; }
    public float getHue()        { return (hsb >> 16 & 0xFF) / 255.0f; }
    public float getSaturation() { return (hsb >>  8 & 0xFF) / 255.0f; }
    public float getBrightness() { return (hsb       & 0xFF) / 255.0f; }

    public float[] getRGBComponents() { return convertColorArrayFromInt(rgb); }
    public float[] getHSBComponents() { return convertColorArrayFromInt(hsb); }

    /**
     * Hashcode is based on the ARGB integer representation of this color.
     *
     * @return Integer hashcode
     */
    @Override
    public int hashCode() { return rgb; }

    /**
     * Compares two Colors to each other by their hashcode (ARGB integer representation).
     *
     * @param other Color to compare with
     * @return True if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof Color && hashCode() == other.hashCode();
    }

    /**
     * Compares two Colors to each other by their hashcode (ARGB integer representation).
     * @param other Color to compare with
     * @return Integer difference between hashcodes
     */
    @Override
    public int compareTo(Color other) {
        return hashCode() - other.hashCode();
    }

    /**
     * Retrieves a String representation of this Color.
     *
     * @return String representation of format "(r, g, b, a)"
     */
    @Override
    public String toString() {
        final float[] colors = convertColorArrayFromInt(rgb);
        return String.format("(%.3f, %.3f, %.3f, %.3f)", colors[1], colors[2], colors[3], colors[0]);
    }
}
