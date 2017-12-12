package debroejm.simpleblend;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorStaticTest extends ColorTestBase {
    
    static final float RED_VALUE = 0.2f;
    static final float GREEN_VALUE = 0.75f;
    static final float BLUE_VALUE = 0.47f;

    static final float HUE_VALUE = 0.3f;
    static final float SATURATION_VALUE = 0.95f;
    static final float BRIGHTNESS_VALUE = 0.67f;

    @Test
    public void clampTest_min() {
        float value = Color.clamp(-10.0f, -1.0f, 1.0f);
        assertEquals(-1.0f, value, 0.001f);
    }

    @Test
    public void clampTest_max() {
        float value = Color.clamp(42.0f, -1.0f, 1.0f);
        assertEquals(1.0f, value, 0.001f);
    }

    @Test
    public void clampTest_normal() {
        float value = Color.clamp(0.5f, -1.0f, 1.0f);
        assertEquals(0.5f, value, 0.001f);
    }

    @Test
    public void colorIntFromFloatOverloadTest() {
        int arrayValue     = Color.convertColorIntFromFloat(new float[]{ 1.0f, RED_VALUE, GREEN_VALUE, BLUE_VALUE });
        int componentValue = Color.convertColorIntFromFloat(RED_VALUE, GREEN_VALUE, BLUE_VALUE, 1.0f);
        int noAlphaValue   = Color.convertColorIntFromFloat(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
        assertEquals("Component overload did not produce same result as array method for Int from Float",
                arrayValue, componentValue);
        assertEquals("No alpha overload did not produce same result as array method for Int from Float",
                arrayValue, noAlphaValue);
    }

    private void checkColorInt(int color, float a, float r, float g, float b) {
        int value = Color.convertColorIntFromFloat(r, g, b, a);
        assertEquals("Converted color integer did not match expected value", color, value);
    }

    @Test public void colorIntFromFloatTest_clear()     { checkColorInt(CLEAR,      0.0f, 0.0f,   0.0f,   0.0f  ); }
    @Test public void colorIntFromFloatTest_white()     { checkColorInt(WHITE,      1.0f, 1.0f,   1.0f,   1.0f  ); }
    @Test public void colorIntFromFloatTest_lightGray() { checkColorInt(LIGHT_GRAY, 1.0f, 0.753f, 0.753f, 0.753f); }
    @Test public void colorIntFromFloatTest_gray()      { checkColorInt(GRAY,       1.0f, 0.502f, 0.502f, 0.502f); }
    @Test public void colorIntFromFloatTest_darkGray()  { checkColorInt(DARK_GRAY,  1.0f, 0.251f, 0.251f, 0.251f); }
    @Test public void colorIntFromFloatTest_black()     { checkColorInt(BLACK,      1.0f, 0.0f,   0.0f,   0.0f  ); }
    @Test public void colorIntFromFloatTest_red()       { checkColorInt(RED,        1.0f, 1.0f,   0.0f,   0.0f  ); }
    @Test public void colorIntFromFloatTest_green()     { checkColorInt(GREEN,      1.0f, 0.0f,   1.0f,   0.0f  ); }
    @Test public void colorIntFromFloatTest_blue()      { checkColorInt(BLUE,       1.0f, 0.0f,   0.0f,   1.0f  ); }
    @Test public void colorIntFromFloatTest_yellow()    { checkColorInt(YELLOW,     1.0f, 1.0f,   1.0f,   0.0f  ); }
    @Test public void colorIntFromFloatTest_cyan()      { checkColorInt(CYAN,       1.0f, 0.0f,   1.0f,   1.0f  ); }
    @Test public void colorIntFromFloatTest_purple()    { checkColorInt(PURPLE,     1.0f, 1.0f,   0.0f,   1.0f  ); }

    private void checkColorArray(int color, float a, float r, float g, float b) {
        float[] value = Color.convertColorArrayFromInt(color);
        assertEquals("Alpha did not match expected value",  a, value[0], EPSILON);
        assertEquals("Red did not match expected value",    r, value[1], EPSILON);
        assertEquals("Green did not match expected value",  g, value[2], EPSILON);
        assertEquals("Blue did not match expected value",   b, value[3], EPSILON);
    }

    @Test public void colorArrayFromIntTest_clear()     { checkColorArray(CLEAR,      0.0f, 0.0f,   0.0f,   0.0f  ); }
    @Test public void colorArrayFromIntTest_white()     { checkColorArray(WHITE,      1.0f, 1.0f,   1.0f,   1.0f  ); }
    @Test public void colorArrayFromIntTest_lightGray() { checkColorArray(LIGHT_GRAY, 1.0f, 0.753f, 0.753f, 0.753f); }
    @Test public void colorArrayFromIntTest_gray()      { checkColorArray(GRAY,       1.0f, 0.502f, 0.502f, 0.502f); }
    @Test public void colorArrayFromIntTest_darkGray()  { checkColorArray(DARK_GRAY,  1.0f, 0.251f, 0.251f, 0.251f); }
    @Test public void colorArrayFromIntTest_black()     { checkColorArray(BLACK,      1.0f, 0.0f,   0.0f,   0.0f  ); }
    @Test public void colorArrayFromIntTest_red()       { checkColorArray(RED,        1.0f, 1.0f,   0.0f,   0.0f  ); }
    @Test public void colorArrayFromIntTest_green()     { checkColorArray(GREEN,      1.0f, 0.0f,   1.0f,   0.0f  ); }
    @Test public void colorArrayFromIntTest_blue()      { checkColorArray(BLUE,       1.0f, 0.0f,   0.0f,   1.0f  ); }
    @Test public void colorArrayFromIntTest_yellow()    { checkColorArray(YELLOW,     1.0f, 1.0f,   1.0f,   0.0f  ); }
    @Test public void colorArrayFromIntTest_cyan()      { checkColorArray(CYAN,       1.0f, 0.0f,   1.0f,   1.0f  ); }
    @Test public void colorArrayFromIntTest_purple()    { checkColorArray(PURPLE,     1.0f, 1.0f,   0.0f,   1.0f  ); }

    @Test
    public void RGBtoHSBoverloadTest() {
        float[] arrayHSB     = Color.convertRGBtoHSB(new float[]{ 1.0f, RED_VALUE, GREEN_VALUE, BLUE_VALUE });
        float[] componentHSB = Color.convertRGBtoHSB(RED_VALUE, GREEN_VALUE, BLUE_VALUE, 1.0f);
        float[] noAlphaHSB   = Color.convertRGBtoHSB(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
        assertArrayEquals("Component overload did not produce same result as array method for RGB to HSB",
                arrayHSB, componentHSB, EPSILON);
        assertArrayEquals("No alpha overload did not produce same result as array method for RGB to HSB",
                arrayHSB, noAlphaHSB,   EPSILON);
    }
    
    private void checkRGBtoHSB(int color, float h, float s, float b) {
        float[] values = Color.convertColorArrayFromInt(color);
        float[] hsb = Color.convertRGBtoHSB(values);
        assertEquals("Hue did not match expected value",           h, hsb[1], EPSILON);
        assertEquals("Saturation did not match expected value",    s, hsb[2], EPSILON);
        assertEquals("Lightness did not match expected value",     b, hsb[3], EPSILON);
        assertEquals("Alpha did not match original value", values[0], hsb[0], EPSILON);
    }

    @Test public void RGBtoHSBtest_clear()     { checkRGBtoHSB(CLEAR,      0.0f,   0.0f, 0.0f  ); }
    @Test public void RGBtoHSBtest_white()     { checkRGBtoHSB(WHITE,      0.0f,   0.0f, 1.0f  ); }
    @Test public void RGBtoHSBtest_lightGray() { checkRGBtoHSB(LIGHT_GRAY, 0.0f,   0.0f, 0.753f); }
    @Test public void RGBtoHSBtest_gray()      { checkRGBtoHSB(GRAY,       0.0f,   0.0f, 0.502f); }
    @Test public void RGBtoHSBtest_darkGray()  { checkRGBtoHSB(DARK_GRAY,  0.0f,   0.0f, 0.251f); }
    @Test public void RGBtoHSBtest_black()     { checkRGBtoHSB(BLACK,      0.0f,   0.0f, 0.0f  ); }
    @Test public void RGBtoHSBtest_red()       { checkRGBtoHSB(RED,        0.0f,   1.0f, 1.0f  ); }
    @Test public void RGBtoHSBtest_green()     { checkRGBtoHSB(GREEN,      0.333f, 1.0f, 1.0f  ); }
    @Test public void RGBtoHSBtest_blue()      { checkRGBtoHSB(BLUE,       0.667f, 1.0f, 1.0f  ); }
    @Test public void RGBtoHSBtest_yellow()    { checkRGBtoHSB(YELLOW,     0.167f, 1.0f, 1.0f  ); }
    @Test public void RGBtoHSBtest_cyan()      { checkRGBtoHSB(CYAN,       0.5f,   1.0f, 1.0f  ); }
    @Test public void RGBtoHSBtest_purple()    { checkRGBtoHSB(PURPLE,     0.833f, 1.0f, 1.0f  ); }

    @Test
    public void HSBtoRGBoverloadTest() {
        float[] arrayRGB     = Color.convertHSBtoRGB(new float[]{ 1.0f, HUE_VALUE, SATURATION_VALUE, BRIGHTNESS_VALUE });
        float[] componentRGB = Color.convertHSBtoRGB(HUE_VALUE, SATURATION_VALUE, BRIGHTNESS_VALUE, 1.0f);
        float[] noAlphaRGB   = Color.convertHSBtoRGB(HUE_VALUE, SATURATION_VALUE, BRIGHTNESS_VALUE);
        assertArrayEquals("Component overload did not produce same result as array method for HSB to RGB",
                arrayRGB, componentRGB, EPSILON);
        assertArrayEquals("No alpha overload did not produce same result as array method for HSB to RGB",
                arrayRGB, noAlphaRGB,   EPSILON);
    }

    private void checkHSBtoRGB(int color, float h, float s, float b, float a) {
        float[] values = Color.convertColorArrayFromInt(color);
        float[] rgb = Color.convertHSBtoRGB(h, s, b, a);
        assertEquals("Red did not match expected value",   values[1], rgb[1], EPSILON);
        assertEquals("Green did not match expected value", values[2], rgb[2], EPSILON);
        assertEquals("Blue did not match expected value",  values[3], rgb[3], EPSILON);
        assertEquals("Alpha did not match original value", values[0], rgb[0], EPSILON);
    }

    @Test public void HSBtoRGBtest_clear()     { checkHSBtoRGB(CLEAR,      0.0f,   0.0f, 0.0f,   0.0f); }
    @Test public void HSBtoRGBtest_white()     { checkHSBtoRGB(WHITE,      0.0f,   0.0f, 1.0f,   1.0f); }
    @Test public void HSBtoRGBtest_lightGray() { checkHSBtoRGB(LIGHT_GRAY, 0.0f,   0.0f, 0.753f, 1.0f); }
    @Test public void HSBtoRGBtest_gray()      { checkHSBtoRGB(GRAY,       0.0f,   0.0f, 0.502f, 1.0f); }
    @Test public void HSBtoRGBtest_darkGray()  { checkHSBtoRGB(DARK_GRAY,  0.0f,   0.0f, 0.251f, 1.0f); }
    @Test public void HSBtoRGBtest_black()     { checkHSBtoRGB(BLACK,      0.0f,   0.0f, 0.0f,   1.0f); }
    @Test public void HSBtoRGBtest_red()       { checkHSBtoRGB(RED,        0.0f,   1.0f, 1.0f,   1.0f); }
    @Test public void HSBtoRGBtest_green()     { checkHSBtoRGB(GREEN,      0.333f, 1.0f, 1.0f,   1.0f); }
    @Test public void HSBtoRGBtest_blue()      { checkHSBtoRGB(BLUE,       0.667f, 1.0f, 1.0f,   1.0f); }
    @Test public void HSBtoRGBtest_yellow()    { checkHSBtoRGB(YELLOW,     0.167f, 1.0f, 1.0f,   1.0f); }
    @Test public void HSBtoRGBtest_cyan()      { checkHSBtoRGB(CYAN,       0.5f,   1.0f, 1.0f,   1.0f); }
    @Test public void HSBtoRGBtest_purple()    { checkHSBtoRGB(PURPLE,     0.833f, 1.0f, 1.0f,   1.0f); }

}