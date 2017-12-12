package debroejm.simpleblend;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorCommonTest extends ColorTestBase {

    @Test public void colorTest_clear() { checkColor(Color.CLEAR, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f); }

    @Test public void colorTest_white()     { checkColor(Color.WHITE,      1.0f, 1.0f,   1.0f,   1.0f,   0.0f, 0.0f, 1.0f  ); }
    @Test public void colorTest_lightGray() { checkColor(Color.LIGHT_GRAY, 1.0f, 0.753f, 0.753f, 0.753f, 0.0f, 0.0f, 0.753f); }
    @Test public void colorTest_gray()      { checkColor(Color.GRAY,       1.0f, 0.502f, 0.502f, 0.502f, 0.0f, 0.0f, 0.502f); }
    @Test public void colorTest_darkGray()  { checkColor(Color.DARK_GRAY,  1.0f, 0.251f, 0.251f, 0.251f, 0.0f, 0.0f, 0.251f); }
    @Test public void colorTest_black()     { checkColor(Color.BLACK,      1.0f, 0.0f,   0.0f,   0.0f,   0.0f, 0.0f, 0.0f  ); }

    @Test public void colorTest_lightGrey() { checkColor(Color.LIGHT_GREY, 1.0f, 0.753f, 0.753f, 0.753f, 0.0f, 0.0f, 0.753f); }
    @Test public void colorTest_grey()      { checkColor(Color.GREY,       1.0f, 0.502f, 0.502f, 0.502f, 0.0f, 0.0f, 0.502f); }
    @Test public void colorTest_darkGrey()  { checkColor(Color.DARK_GREY,  1.0f, 0.251f, 0.251f, 0.251f, 0.0f, 0.0f, 0.251f); }

    @Test public void colorTest_red()    { checkColor(Color.RED,    1.0f, 1.0f, 0.0f, 0.0f, 0.0f,   1.0f, 1.0f); }
    @Test public void colorTest_green()  { checkColor(Color.GREEN,  1.0f, 0.0f, 1.0f, 0.0f, 0.333f, 1.0f, 1.0f); }
    @Test public void colorTest_blue()   { checkColor(Color.BLUE,   1.0f, 0.0f, 0.0f, 1.0f, 0.667f, 1.0f, 1.0f); }
    @Test public void colorTest_yellow() { checkColor(Color.YELLOW, 1.0f, 1.0f, 1.0f, 0.0f, 0.167f, 1.0f, 1.0f); }
    @Test public void colorTest_cyan()   { checkColor(Color.CYAN,   1.0f, 0.0f, 1.0f, 1.0f, 0.5f,   1.0f, 1.0f); }
    @Test public void colorTest_purple() { checkColor(Color.PURPLE, 1.0f, 1.0f, 0.0f, 1.0f, 0.833f, 1.0f, 1.0f); }
}
