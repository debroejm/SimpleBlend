package debroejm.simpleblend;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorTest extends ColorTestBase {

    @Test public void ofRGBintTest_clear()     { checkColor(Color.ofRGB(CLEAR),      0.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofRGBintTest_white()     { checkColor(Color.ofRGB(WHITE),      1.0f, 1.0f,   1.0f,   1.0f,   0.0f,   0.0f, 1.0f  ); }
    @Test public void ofRGBintTest_lightGray() { checkColor(Color.ofRGB(LIGHT_GRAY), 1.0f, 0.753f, 0.753f, 0.753f, 0.0f,   0.0f, 0.753f); }
    @Test public void ofRGBintTest_gray()      { checkColor(Color.ofRGB(GRAY),       1.0f, 0.502f, 0.502f, 0.502f, 0.0f,   0.0f, 0.502f); }
    @Test public void ofRGBintTest_darkGray()  { checkColor(Color.ofRGB(DARK_GRAY),  1.0f, 0.251f, 0.251f, 0.251f, 0.0f,   0.0f, 0.251f); }
    @Test public void ofRGBintTest_black()     { checkColor(Color.ofRGB(BLACK),      1.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofRGBintTest_red()       { checkColor(Color.ofRGB(RED),        1.0f, 1.0f,   0.0f,   0.0f,   0.0f,   1.0f, 1.0f  ); }
    @Test public void ofRGBintTest_green()     { checkColor(Color.ofRGB(GREEN),      1.0f, 0.0f,   1.0f,   0.0f,   0.333f, 1.0f, 1.0f  ); }
    @Test public void ofRGBintTest_blue()      { checkColor(Color.ofRGB(BLUE),       1.0f, 0.0f,   0.0f,   1.0f,   0.667f, 1.0f, 1.0f  ); }
    @Test public void ofRGBintTest_yellow()    { checkColor(Color.ofRGB(YELLOW),     1.0f, 1.0f,   1.0f,   0.0f,   0.167f, 1.0f, 1.0f  ); }
    @Test public void ofRGBintTest_cyan()      { checkColor(Color.ofRGB(CYAN),       1.0f, 0.0f,   1.0f,   1.0f,   0.5f,   1.0f, 1.0f  ); }
    @Test public void ofRGBintTest_purple()    { checkColor(Color.ofRGB(PURPLE),     1.0f, 1.0f,   0.0f,   1.0f,   0.833f, 1.0f, 1.0f  ); }

    @Test public void ofRGBfloatTest_clear()     { checkColor(Color.ofRGB(0.0f,   0.0f,   0.0f,   0.0f), 0.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofRGBfloatTest_white()     { checkColor(Color.ofRGB(1.0f,   1.0f,   1.0f,   1.0f), 1.0f, 1.0f,   1.0f,   1.0f,   0.0f,   0.0f, 1.0f  ); }
    @Test public void ofRGBfloatTest_lightGray() { checkColor(Color.ofRGB(0.753f, 0.753f, 0.753f, 1.0f), 1.0f, 0.753f, 0.753f, 0.753f, 0.0f,   0.0f, 0.753f); }
    @Test public void ofRGBfloatTest_gray()      { checkColor(Color.ofRGB(0.502f, 0.502f, 0.502f, 1.0f), 1.0f, 0.502f, 0.502f, 0.502f, 0.0f,   0.0f, 0.502f); }
    @Test public void ofRGBfloatTest_darkGray()  { checkColor(Color.ofRGB(0.251f, 0.251f, 0.251f, 1.0f), 1.0f, 0.251f, 0.251f, 0.251f, 0.0f,   0.0f, 0.251f); }
    @Test public void ofRGBfloatTest_black()     { checkColor(Color.ofRGB(0.0f,   0.0f,   0.0f,   1.0f), 1.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofRGBfloatTest_red()       { checkColor(Color.ofRGB(1.0f,   0.0f,   0.0f,   1.0f), 1.0f, 1.0f,   0.0f,   0.0f,   0.0f,   1.0f, 1.0f  ); }
    @Test public void ofRGBfloatTest_green()     { checkColor(Color.ofRGB(0.0f,   1.0f,   0.0f,   1.0f), 1.0f, 0.0f,   1.0f,   0.0f,   0.333f, 1.0f, 1.0f  ); }
    @Test public void ofRGBfloatTest_blue()      { checkColor(Color.ofRGB(0.0f,   0.0f,   1.0f,   1.0f), 1.0f, 0.0f,   0.0f,   1.0f,   0.667f, 1.0f, 1.0f  ); }
    @Test public void ofRGBfloatTest_yellow()    { checkColor(Color.ofRGB(1.0f,   1.0f,   0.0f,   1.0f), 1.0f, 1.0f,   1.0f,   0.0f,   0.167f, 1.0f, 1.0f  ); }
    @Test public void ofRGBfloatTest_cyan()      { checkColor(Color.ofRGB(0.0f,   1.0f,   1.0f,   1.0f), 1.0f, 0.0f,   1.0f,   1.0f,   0.5f,   1.0f, 1.0f  ); }
    @Test public void ofRGBfloatTest_purple()    { checkColor(Color.ofRGB(1.0f,   0.0f,   1.0f,   1.0f), 1.0f, 1.0f,   0.0f,   1.0f,   0.833f, 1.0f, 1.0f  ); }

    @Test public void ofHSBintTest_clear()     { checkColor(Color.ofHSB(HSB_CLEAR),      0.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofHSBintTest_white()     { checkColor(Color.ofHSB(HSB_WHITE),      1.0f, 1.0f,   1.0f,   1.0f,   0.0f,   0.0f, 1.0f  ); }
    @Test public void ofHSBintTest_lightGray() { checkColor(Color.ofHSB(HSB_LIGHT_GRAY), 1.0f, 0.753f, 0.753f, 0.753f, 0.0f,   0.0f, 0.753f); }
    @Test public void ofHSBintTest_gray()      { checkColor(Color.ofHSB(HSB_GRAY),       1.0f, 0.502f, 0.502f, 0.502f, 0.0f,   0.0f, 0.502f); }
    @Test public void ofHSBintTest_darkGray()  { checkColor(Color.ofHSB(HSB_DARK_GRAY),  1.0f, 0.251f, 0.251f, 0.251f, 0.0f,   0.0f, 0.251f); }
    @Test public void ofHSBintTest_black()     { checkColor(Color.ofHSB(HSB_BLACK),      1.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofHSBintTest_red()       { checkColor(Color.ofHSB(HSB_RED),        1.0f, 1.0f,   0.0f,   0.0f,   0.0f,   1.0f, 1.0f  ); }
    @Test public void ofHSBintTest_green()     { checkColor(Color.ofHSB(HSB_GREEN),      1.0f, 0.0f,   1.0f,   0.0f,   0.333f, 1.0f, 1.0f  ); }
    @Test public void ofHSBintTest_blue()      { checkColor(Color.ofHSB(HSB_BLUE),       1.0f, 0.0f,   0.0f,   1.0f,   0.667f, 1.0f, 1.0f  ); }
    @Test public void ofHSBintTest_yellow()    { checkColor(Color.ofHSB(HSB_YELLOW),     1.0f, 1.0f,   1.0f,   0.0f,   0.167f, 1.0f, 1.0f, 0.02f  ); }
    @Test public void ofHSBintTest_cyan()      { checkColor(Color.ofHSB(HSB_CYAN),       1.0f, 0.0f,   1.0f,   1.0f,   0.5f,   1.0f, 1.0f, 0.02f  ); }
    @Test public void ofHSBintTest_purple()    { checkColor(Color.ofHSB(HSB_PURPLE),     1.0f, 1.0f,   0.0f,   1.0f,   0.833f, 1.0f, 1.0f, 0.02f  ); }

    @Test public void ofHSBfloatTest_clear()     { checkColor(Color.ofHSB(0.0f,   0.0f, 0.0f,   0.0f), 0.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofHSBfloatTest_white()     { checkColor(Color.ofHSB(0.0f,   0.0f, 1.0f,   1.0f), 1.0f, 1.0f,   1.0f,   1.0f,   0.0f,   0.0f, 1.0f  ); }
    @Test public void ofHSBfloatTest_lightGray() { checkColor(Color.ofHSB(0.0f,   0.0f, 0.753f, 1.0f), 1.0f, 0.753f, 0.753f, 0.753f, 0.0f,   0.0f, 0.753f); }
    @Test public void ofHSBfloatTest_gray()      { checkColor(Color.ofHSB(0.0f,   0.0f, 0.502f, 1.0f), 1.0f, 0.502f, 0.502f, 0.502f, 0.0f,   0.0f, 0.502f); }
    @Test public void ofHSBfloatTest_darkGray()  { checkColor(Color.ofHSB(0.0f,   0.0f, 0.251f, 1.0f), 1.0f, 0.251f, 0.251f, 0.251f, 0.0f,   0.0f, 0.251f); }
    @Test public void ofHSBfloatTest_black()     { checkColor(Color.ofHSB(0.0f,   0.0f, 0.0f,   1.0f), 1.0f, 0.0f,   0.0f,   0.0f,   0.0f,   0.0f, 0.0f  ); }
    @Test public void ofHSBfloatTest_red()       { checkColor(Color.ofHSB(0.0f,   1.0f, 1.0f,   1.0f), 1.0f, 1.0f,   0.0f,   0.0f,   0.0f,   1.0f, 1.0f  ); }
    @Test public void ofHSBfloatTest_green()     { checkColor(Color.ofHSB(0.333f, 1.0f, 1.0f,   1.0f), 1.0f, 0.0f,   1.0f,   0.0f,   0.333f, 1.0f, 1.0f  ); }
    @Test public void ofHSBfloatTest_blue()      { checkColor(Color.ofHSB(0.667f, 1.0f, 1.0f,   1.0f), 1.0f, 0.0f,   0.0f,   1.0f,   0.667f, 1.0f, 1.0f  ); }
    @Test public void ofHSBfloatTest_yellow()    { checkColor(Color.ofHSB(0.167f, 1.0f, 1.0f,   1.0f), 1.0f, 1.0f,   1.0f,   0.0f,   0.167f, 1.0f, 1.0f  ); }
    @Test public void ofHSBfloatTest_cyan()      { checkColor(Color.ofHSB(0.5f,   1.0f, 1.0f,   1.0f), 1.0f, 0.0f,   1.0f,   1.0f,   0.5f,   1.0f, 1.0f  ); }
    @Test public void ofHSBfloatTest_purple()    { checkColor(Color.ofHSB(0.833f, 1.0f, 1.0f,   1.0f), 1.0f, 1.0f,   0.0f,   1.0f,   0.833f, 1.0f, 1.0f  ); }
    
    private void checkComponentsMatch(Color color) {
        float[] rgb = color.getRGBComponents();
        float[] hsb = color.getHSBComponents();
        
        assertEquals("Alpha did not match",      color.getAlpha(),      rgb[0], EPSILON);
        assertEquals("Red did not match",        color.getRed(),        rgb[1], EPSILON);
        assertEquals("Green did not match",      color.getGreen(),      rgb[2], EPSILON);
        assertEquals("Blue did not match",       color.getBlue(),       rgb[3], EPSILON);
        
        assertEquals("Alpha did not match",      color.getAlpha(),      hsb[0], EPSILON);
        assertEquals("Hue did not match",        color.getHue(),        hsb[1], EPSILON);
        assertEquals("Saturation did not match", color.getSaturation(), hsb[2], EPSILON);
        assertEquals("Brightness did not match", color.getBrightness(), hsb[3], EPSILON);
    }
    
    @Test public void componentsMatch_clear()     { checkComponentsMatch(Color.CLEAR     ); }
    @Test public void componentsMatch_white()     { checkComponentsMatch(Color.WHITE     ); }
    @Test public void componentsMatch_lightGray() { checkComponentsMatch(Color.LIGHT_GRAY); }
    @Test public void componentsMatch_gray()      { checkComponentsMatch(Color.GRAY      ); }
    @Test public void componentsMatch_darkGray()  { checkComponentsMatch(Color.DARK_GRAY ); }
    @Test public void componentsMatch_black()     { checkComponentsMatch(Color.BLACK     ); }
    @Test public void componentsMatch_red()       { checkComponentsMatch(Color.RED       ); }
    @Test public void componentsMatch_green()     { checkComponentsMatch(Color.GREEN     ); }
    @Test public void componentsMatch_blue()      { checkComponentsMatch(Color.BLUE      ); }
    @Test public void componentsMatch_yellow()    { checkComponentsMatch(Color.YELLOW    ); }
    @Test public void componentsMatch_cyan()      { checkComponentsMatch(Color.CYAN      ); }
    @Test public void componentsMatch_purple()    { checkComponentsMatch(Color.PURPLE    ); }
    
}
