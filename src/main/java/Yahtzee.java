import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class Yahtzee {
    public static final int YAHTZEE = 50;
    ArrayList<Integer> dice;

    public Yahtzee(Integer...ints ){
        dice = new ArrayList<>();
        Collections.addAll(dice, ints );
    }

    public int score(String category) {
        switch(category){
            case "fours":
                return scoreSingles(4);
            case "ones":
                return scoreSingles(1);
            case "twos":
                return scoreSingles(2);
            case "threes":
                return scoreSingles(3);
            case "fives":
                return scoreSingles(5);
            case "sixes":
                return scoreSingles(6);
            case "pair":
                return scorePair();
            case "two pair":
                return scoreTwoPair();
            case "three of a kind":
                return scoreNOfAKind(3);
            case "four of a kind":
                return scoreNOfAKind(4);
            case "small straight":
                return scoreStraight(1, 2, 3, 4, 5);
            case "large straight":
                return scoreStraight(2, 3, 4, 5, 6);
            case "full house":
                return scoreFullHouse();
            case "yahtzee":
                return scoreYahtzee();
            case "chance":
                return getSum();
        }
        return 0;
    }

    private int scoreYahtzee() {
        return dice.stream().allMatch(sameAsFirst()) ? YAHTZEE : 0;
    }

    private Predicate<Integer> sameAsFirst() {
        return i->i==dice.get(0);
    }

    private int scoreFullHouse() {
        return hasPair() && hasThreeOfAKind() ? getSum() : 0;
    }

    private int getSum() {
        return dice.stream().mapToInt(i -> i).sum();
    }

    private boolean hasThreeOfAKind() {
            return dice.stream().filter(i->frequency(i) == 3).count() == 3;

    }

    private boolean hasPair() {
        return dice.stream().filter(i->frequency(i) == 2).count() == 2;
    }

    private int scoreStraight(Integer...ints) {
        return dice.containsAll(list(ints)) ? getSum() : 0;
    }

    private ArrayList<Integer> list(Integer...ints) {
        ArrayList<Integer> returnValue = new ArrayList<>();
        Collections.addAll(returnValue, ints);
        return returnValue;
    }

    private int scoreNOfAKind(int n) {
        return dice.stream().filter(i -> frequency(i) == n).mapToInt(i -> i).sum();
    }

    private int scoreTwoPair() {
        int count = 0;
        int sum = 0;

        for(int d: dice){
            if(frequency(d) == 2){
                count ++;
                sum += d;
            }
        }

        if(count == 2 * 2){
            return sum;
        }else{
            return 0;
        }
    }

    private int frequency(int d) {
        return Collections.frequency(dice, d);
    }

    private int scorePair() {
            return dice.stream().
                    filter(i-> frequency(i) == 2).
                    mapToInt(i->i).max().orElse(0) * 2;
    }

    private int scoreSingles(int i) {
        return frequency(i) * i;
    }
}
