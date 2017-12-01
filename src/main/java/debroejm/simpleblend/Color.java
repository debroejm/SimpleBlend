package debroejm.simpleblend;

import java.io.Serializable;

/**
 * Utility color class. For just in case you don't
 * have access to java.awt (looking at you Android).
 */
public class Color implements Comparable<Color>, Serializable {



    // ***************************
    //  Static Conversion Methods
    // ***************************

    public static int convertColorIntFromFloat(float r, float g, float b, float a) {
        return ((int)(a * 255) & 0xFF) << 24 |
                ((int)(r * 255) & 0xFF) << 16 |
                ((int)(g * 255) & 0xFF) << 8 |
                ((int)(b * 255) & 0xFF);
    }

    public static int convertColorIntFromFloat(float r, float g, float b) {
        return convertColorIntFromFloat(r,g,b,1.0f);
    }

    public static float convertAlphaFloatFromInt(int color) {
        return (color >> 24 & 0xFF) / 255.0f;
    }

    public static float convertRedFloatFromInt(int color) {
        return (color >> 16 & 0xFF) / 255.0f;
    }

    public static float convertGreenFloatFromInt(int color) {
        return (color >> 8 & 0xFF) / 255.0f;
    }

    public static float convertBlueFloatFromInt(int color) {
        return (color & 0xFF) / 255.0f;
    }

    public static float[] convertColorArrayFromInt(int color) {
        return new float[]{
                (color >> 24 & 0xFF) / 255.0f,
                (color >> 16 & 0xFF) / 255.0f,
                (color >> 8 & 0xFF) / 255.0f,
                (color & 0xFF) / 255.0f
        };
    }

    static final float EPSILON = 0.001f;

    public static float[] convertRGBtoHSL(float red, float green, float blue) {
        return convertRGBtoHSL(new float[]{ red, green, blue });
    }

    public static float[] convertRGBtoHSL(float[] rgb) {
        if(rgb.length < 3)
            throw new IllegalArgumentException("RGB float array must have 3 components");

        float max = -0.1f, min = 1.1f;
        int mI = -1;
        for(int i = 0; i < 3; i++) {
            if(rgb[i] < 0f || rgb[i] > 1f)
                throw new IllegalArgumentException("RGB components must be between 0.0f and 1.0f, inclusive");

            if(rgb[i] < min) min = rgb[i];
            if(rgb[i] > max) {
                max = rgb[i];
                mI = i;
            }
        }

        float hsl[] = new float[3];

        float delta = max - min;
        float nabla = max + min;
        hsl[2] = nabla / 2f;
        if(Math.abs(delta) < EPSILON) {
            hsl[0] = hsl[1] = 0f;
        } else {
            hsl[1] = hsl[2] > 0.5f ? (delta / (2f - nabla)) : (delta / nabla);
            float first = hsl[(mI+1)%3];
            float second = hsl[(mI+2)%3];
            hsl[0] = (first - second) / delta + ( (mI == 0 && first < second) ? 3 : mI) * 2f;
        }
        hsl[0] /= 6f;

        return hsl;
    }

    public static float[] convertHSLtoRGB(float hue, float saturation, float lightness) {
        return convertHSLtoRGB(new float[]{ hue, saturation, lightness });
    }

    public static float[] convertHSLtoRGB(float[] hsl) {
        if(hsl.length < 3)
            throw new IllegalArgumentException("HSL float array must have 3 components");

        float rgb[] = new float[3];

        if(hsl[1] < EPSILON) {
            rgb[0] = rgb[1] = rgb[2] = hsl[2];
        } else {
            float q = ( hsl[2] < 0.5f ) ? ( hsl[2] * (1f + hsl[1]) ) : ( hsl[2] + hsl[1] - hsl[2] * hsl[1] );
            float p = 2f * hsl[2] - q;
            rgb[0] = hueToRGB(p, q, hsl[0] + 1f/3f);
            rgb[1] = hueToRGB(p, q, hsl[0]);
            rgb[2] = hueToRGB(p, q, hsl[0] - 1f/3f);
        }

        return rgb;
    }

    private static float hueToRGB(float p, float q, float t) {
        if(t < 0f) t += 1f;
        if(t > 1f) t -= 1f;
        if(t < 1f/6f) return p + (q - p) * 6f * t;
        if(t < 1f/2f) return q;
        if(t < 2f/3f) return p + (q - p) * (2f/3f - t) * 6f;
        return p;
    }



    // **********************
    //  Class Implementation
    // **********************

    public final int rgb;
    public final int hsl;

    public Color(int rgb) {
        this.rgb = rgb;
        float[] argb = convertColorArrayFromInt(rgb);
        float[] hsl = convertRGBtoHSL(argb[1], argb[2], argb[3]);
        this.hsl = convertColorIntFromFloat(hsl[0], hsl[1], hsl[2], argb[0]);
    }

    public Color(float r, float g, float b) {
        this.rgb = convertColorIntFromFloat(r, g, b);
        float[] hsl = convertRGBtoHSL(r, g, b);
        this.hsl = convertColorIntFromFloat(hsl[0], hsl[1], hsl[2]);
    }

    public Color(float r, float g, float b, float a) {
        this.rgb = convertColorIntFromFloat(r, g, b, a);
        float[] hsl = convertRGBtoHSL(r, g, b);
        this.hsl = convertColorIntFromFloat(hsl[0], hsl[1], hsl[2], a);
    }

    public int getRGB() { return rgb; }
    public int getHSL() { return hsl; }

    public float getAlpha()      { return convertAlphaFloatFromInt(rgb); }
    public float getRed()        { return convertRedFloatFromInt  (rgb); }
    public float getGreen()      { return convertGreenFloatFromInt(rgb); }
    public float getBlue()       { return convertBlueFloatFromInt (rgb); }
    public float getHue()        { return convertRedFloatFromInt  (hsl); }
    public float getSaturation() { return convertGreenFloatFromInt(hsl); }
    public float getLightness()  { return convertBlueFloatFromInt (hsl); }

    public float[] getRGBComponents() { return convertColorArrayFromInt(rgb); }
    public float[] getHSLComponents() { return convertColorArrayFromInt(hsl); }

    public float getEuclideanDistance(Color other) {
        float[] values_this  = this .getRGBComponents();
        float[] values_other = other.getRGBComponents();
        return (float) Math.sqrt(
                Math.pow(values_other[0] - values_this[0], 2.0) +
                Math.pow(values_other[1] - values_this[1], 2.0) +
                Math.pow(values_other[2] - values_this[2], 2.0) +
                Math.pow(values_other[3] - values_this[3], 2.0)
        );
    }

    /**
     * Hashcode is based on the RGB integer representation of this color.
     *
     * @return Integer hashcode
     */
    @Override
    public int hashCode() { return rgb; }

    @Override
    public boolean equals(Object other) {
        return other instanceof Color && hashCode() == other.hashCode();
    }

    @Override
    public int compareTo(Color o) {
        return hashCode() - o.hashCode();
    }

    @Override
    public String toString() {
        final float[] colors = convertColorArrayFromInt(rgb);
        return String.format("(%.4f, %.4f, %.4f)", colors[1], colors[2], colors[3]);
    }
}
