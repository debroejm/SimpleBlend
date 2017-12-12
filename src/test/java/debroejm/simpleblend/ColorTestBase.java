package debroejm.simpleblend;

import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

@Ignore
public class ColorTestBase {

    void checkColor(
            Color color, float alpha,
            float red, float green, float blue,
            float hue, float saturation, float brightness,
            float epsilon
    ) {
        assertEquals("Alpha did not match expected value",      alpha,      color.getAlpha(),      epsilon);
        assertEquals("Red did not match expected value",        red,        color.getRed(),        epsilon);
        assertEquals("Green did not match expected value",      green,      color.getGreen(),      epsilon);
        assertEquals("Blue did not match expected value",       blue,       color.getBlue(),       epsilon);
        assertEquals("Hue did not match expected value",        hue,        color.getHue(),        epsilon);
        assertEquals("Saturation did not match expected value", saturation, color.getSaturation(), epsilon);
        assertEquals("Brightness did not match expected value", brightness, color.getBrightness(), epsilon);
    }

    void checkColor(
            Color color, float alpha,
            float red, float green, float blue,
            float hue, float saturation, float brightness
    ) {
        checkColor(color, alpha, red, green, blue, hue, saturation, brightness, EPSILON);
    }

    static final float EPSILON = 0.005f;

    static final int CLEAR          = 0;

    static final int WHITE          = 0xFFFFFFFF;
    static final int LIGHT_GRAY     = 0xFFC0C0C0;
    static final int GRAY           = 0xFF808080;
    static final int DARK_GRAY      = 0xFF404040;
    static final int BLACK          = 0xFF000000;

    static final int RED            = 0xFFFF0000;
    static final int GREEN          = 0xFF00FF00;
    static final int BLUE           = 0xFF0000FF;
    static final int YELLOW         = 0xFFFFFF00;
    static final int CYAN           = 0xFF00FFFF;
    static final int PURPLE         = 0xFFFF00FF;

    static final int HSB_CLEAR      = 0;

    static final int HSB_WHITE      = 0xFF0000FF;
    static final int HSB_LIGHT_GRAY = 0xFF0000C0;
    static final int HSB_GRAY       = 0xFF000080;
    static final int HSB_DARK_GRAY  = 0xFF000040;
    static final int HSB_BLACK      = 0xFF000000;

    static final int HSB_RED        = 0xFF00FFFF;
    static final int HSB_GREEN      = 0xFF55FFFF;
    static final int HSB_BLUE       = 0xFFAAFFFF;
    static final int HSB_YELLOW     = 0xFF2BFFFF;
    static final int HSB_CYAN       = 0xFF7FFFFF;
    static final int HSB_PURPLE     = 0xFFD5FFFF;
}
