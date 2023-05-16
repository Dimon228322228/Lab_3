package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {
    Validation validation;

    @BeforeEach
    void prepareData(){
        validation = new Validation();
    }

    @Test
    void checkRightTopSquare() {
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
    void checkLeftTopSquare() {
        for ( int i = 1; i < 6; i++ )
            assertTrue(validation.isPointInShapes(-i, (float) i / 2, i));
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(-i, (float) i / 2 + 0.1, i));
            assertFalse(validation.isPointInShapes(-i - 0.1, (float)i / 2, i));
        }
    }

    @Test
    void checkLeftBottomSquare() {
        for ( int i = 1; i < 6; i++ )
            assertTrue(validation.isPointInShapes(-i, 0, i));
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(-i, -0.1, i));
            assertFalse(validation.isPointInShapes(-i - 0.1, 0, i));
        }
    }

    @Test
    void checkCenter() {
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
    void checkBottomCircle() {
        for ( int i = 1; i < 6; i++ )
            assertTrue(validation.isPointInShapes(0, -i, i));
        for ( int i = 1; i < 6; i++ ){
            assertFalse(validation.isPointInShapes(0, -i - 0.1, i));
            assertFalse(validation.isPointInShapes(-0.1, -i, i));
        }
    }

    @Test
    void checkCircleLength() {
        for ( int i = 1; i < 6; i++ )
            for (double x = 0; x < i - 0.1; x+=0.1){
                double y = -sqrt((i-x)*(i+x));
                assertTrue(validation.isPointInShapes(x, y + 0.1, i));
                assertFalse(validation.isPointInShapes(x, y - 0.1, i));
            }
    }

    @Test
    void checkRightTopCircle() {
        for ( float i = 1; i < 6; i++ ){
            assertTrue(validation.isPointInShapes(i, 0, i));
            assertFalse(validation.isPointInShapes(i, 0 - 0.1, i));
            assertFalse(validation.isPointInShapes(i, 0 + 0.1, i));
            assertFalse(validation.isPointInShapes(i + 0.1, 0, i));
        }
    }

}