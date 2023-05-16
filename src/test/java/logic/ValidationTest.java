package logic;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {
    Validation validation;

    @Before
    public void prepareData(){
        validation = new Validation();
    }

    @Test
    public void checkRightTopSquare() {
        for ( int i = 1; i < 6; i++ ){
            assertTrue(validation.isPointInShapes(0, (float) i / 2, i));
            //                                      ^^^        ^^^     ^^^
            //                                       x          y       r
        }
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(0, (float) i / 2 + 0.1, i));
            assertFalse(validation.isPointInShapes(0.1, (float)i / 2, i));
        }
    }
    @Test
    public void checkLeftTopSquare() {
        for ( int i = 1; i < 6; i++ )
            assertTrue(validation.isPointInShapes(-i, (float) i / 2, i));
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(-i, (float) i / 2 + 0.1, i));
            assertFalse(validation.isPointInShapes(-i - 0.1, (float)i / 2, i));
        }
    }

    @Test
    public void checkLeftBottomSquare() {
        for ( int i = 1; i < 6; i++ )
            assertTrue(validation.isPointInShapes(-i, 0, i));
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(-i, -0.1, i));
            assertFalse(validation.isPointInShapes(-i - 0.1, 0, i));
        }
    }

    @Test
    public void checkCenter() {
        for ( int i = 1; i < 6; i++ ){
            assertTrue(validation.isPointInShapes(0, 0, i));
            assertTrue(validation.isPointInShapes(0.1, 0, i));
            assertTrue(validation.isPointInShapes(-0.1, 0, i));
            assertTrue(validation.isPointInShapes(0, 0.1, i));
            assertTrue(validation.isPointInShapes(0, -0.1, i));
            assertTrue(validation.isPointInShapes(0.1, 0.1, i));
            assertTrue(validation.isPointInShapes(-0.1, 0.1, i));
            assertTrue(validation.isPointInShapes(0.1, -0.1, i));
        }
        for ( int i = 1; i < 6; i++ )
            assertFalse(validation.isPointInShapes(-0.1, -0.1, i));
    }

    @Test
    public void checkBottomCircle() {
        for ( int i = 1; i < 6; i++ )
            assertTrue(validation.isPointInShapes(0, -i, i));
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(0, -i - 0.1, i));
            assertFalse(validation.isPointInShapes(-0.1, -i, i));
        }
    }

    @Test
    public void checkCircleLength() {
        for ( int i = 1; i < 6; i++ )
            for (double x = 0; x < i - 0.1; x+=0.1){
                double y = -sqrt((i-x)*(i+x));
                assertTrue(validation.isPointInShapes(x, y + 0.1, i));
                assertFalse(validation.isPointInShapes(x, y - 0.1, i));
            }
    }

    @Test
    public void checkRightTopCircle() {
        for ( float i = 1; i < 6; i++ ){
            assertTrue(validation.isPointInShapes(i, 0, i));
            assertFalse(validation.isPointInShapes(i, 0 - 0.1, i));
            assertFalse(validation.isPointInShapes(i, 0 + 0.1, i));
            assertFalse(validation.isPointInShapes(i + 0.1, 0, i));
        }
    }

}