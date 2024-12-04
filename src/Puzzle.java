import java.util.ArrayList;

/** This class represents whole puzzle: elements that connected to each other**/
public class Puzzle {

    /** List that contains indexes of already used elements **/
    public ArrayList<Integer> elements = new ArrayList<>();

    /** Value of a puzzle **/
    public String puzzleValue = "";

    /** This constructor is used to make a puzzle from only one element **/
    public Puzzle(PuzzleElement firstElement, int index) {
        this.elements.add(index);
        this.puzzleValue += firstElement.head + firstElement.body + firstElement.tail;
    }

    /** This constructor is used to connect an element to puzzle **/
    public Puzzle(Puzzle puzzleResult, PuzzleElement puzzleElement, int index) {
        this.elements = puzzleResult.elements;
        this.elements.add(index);
        this.puzzleValue = puzzleResult.puzzleValue + puzzleElement.body + puzzleElement.tail;
    }

    /** This constructor is used to create a copy of a puzzle **/
    public Puzzle(Puzzle puzzleResult) {
        this.elements = puzzleResult.elements;
        this.puzzleValue = puzzleResult.puzzleValue;
    }
}
