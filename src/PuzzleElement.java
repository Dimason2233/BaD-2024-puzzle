import java.util.ArrayList;

/** This class represents an element of the puzzle **/
public class PuzzleElement {

    /** First two digits of element **/
    public String head;

    /** Body of element (all digits except first and last two) **/
    public String body;

    /** Last two digits of element **/
    public String tail;

    /** List of indexes of all elements that can be connected to this element **/
    public ArrayList<Integer> tailConnectable = new ArrayList<>();

    /** A simple constructor that converts string to an object of this class **/
    public PuzzleElement(String element) {
        int len = element.length();
        this.head = element.substring(0,2);
        this.body = element.substring(2,len - 2);
        this.tail = element.substring(len - 2, len);
    }

    /** A method that saves index if two elements could be connected to each other **/
    public void checkConnection(PuzzleElement checkingElem, int checkingIndex, int currentIndex) {
        if (this.tail.equals(checkingElem.head)) {
            tailConnectable.add(checkingIndex);
        }
        if (this.head.equals(checkingElem.tail)) {
            checkingElem.tailConnectable.add(currentIndex);
        }
    }

}
