import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class YahtzeeTest {
    @Test
    public void testScore() {
        assertScoreEquals(2, "twos", 1, 1, 2, 4, 4);
        assertScoreEquals(6, "threes", 1, 1, 2, 3, 3);
        assertScoreEquals(5, "fives", 1, 1, 2, 3, 5);
        assertScoreEquals(18, "sixes", 1, 1, 6, 6, 6);
        assertScoreEquals(8, "pair", 3, 3, 3, 4, 4);
        assertScoreEquals(10, "pair", 3, 5, 5, 4, 4);
        assertScoreEquals(8, "two pair", 1, 1, 2, 3, 3);
        assertScoreEquals(0, "two pair", 1, 1, 3, 3, 3);
        assertScoreEquals(9, "three of a kind", 1, 1, 3, 3, 3);
        assertScoreEquals(0, "three of a kind", 1, 3, 3, 3, 3);
        assertScoreEquals(12, "four of a kind", 1, 3, 3, 3, 3);
        assertScoreEquals(15, "small straight", 1,2, 3, 4, 5);
        assertScoreEquals(0, "small straight", 2,2, 3, 4, 5);
        assertScoreEquals(20, "large straight", 6,2, 3, 4, 5);
        assertScoreEquals(8, "full house", 1, 1, 2, 2, 2);
        assertScoreEquals(0, "full house", 4, 4, 4, 4, 4);
        assertScoreEquals(50, "yahtzee", 4, 4, 4, 4, 4);
        assertScoreEquals(0, "yahtzee", 5, 4, 4, 4, 4);
        assertScoreEquals(21, "chance", 5, 4, 4, 4, 4);
        assertScoreEquals(12, "chance", 1, 1, 2, 4, 4);
    }

    @Test
    public void returns8Given11244AndFours() {
       assertScoreEquals(8, "fours", 1, 1, 2, 4, 4);
    }
    @Test
    public void returns0Given11233AndFours() {
        assertScoreEquals(0, "fours", 1, 1, 2, 3, 3);
    }
    @Test
    public void returns2Given11233AndOnes() {
        assertScoreEquals(2, "ones", 1, 1, 2, 3, 3);
    }
    @Test
    public void returns0Given22233AndOnes() {
        assertScoreEquals(0, "ones", 2, 2, 2, 3, 3);
    }

    public void assertScoreEquals(int expected, String category, Integer...dice) {

        int actual = new Yahtzee(dice).score(category);
        assertEquals(expected, actual);
    }


}
