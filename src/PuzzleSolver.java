import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/** This class implements solving of a puzzle made of numbers, by comparing and laying last two digits of
 * one element with first two digits of another. **/
public class PuzzleSolver {

    /** Main method of the class **/
    public static void main(String[] args) {
        PuzzleSolver obj = new PuzzleSolver();
        obj.run(args[0]);
    }

    /** Simple program table of contents **/
    void run(String filename) {
        ArrayList<String> input = readFile(filename);
        ArrayList<PuzzleElement> puzzleElements = elementCreator(input);
        searchConnections(puzzleElements);
        String result = longestPuzzleFinder(puzzleElements).puzzleValue;

        // Output of the program
        System.out.println(result);
        logResult(result);
    }

    /** Simple logger that saves time and result puzzle **/
    void logResult(String result) {
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timeNow = LocalDateTime.now().format(formatter);
            String log = "Time : " + timeNow + "\nPuzzle : " + result + "\n\n";
            writer.write(log);
        } catch (Exception e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /** A function that reads file line by line
     * @return List of strings (lines of the file) **/
    ArrayList<String> readFile(String filename) {
        ArrayList<String> result = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                result.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist: " + e.getMessage());
        }

        return result;
    }

    /** A function that creates element objects of the puzzle
     * @return List of elements of the puzzle**/
    ArrayList<PuzzleElement> elementCreator(ArrayList<String> input) {
        ArrayList<PuzzleElement> result = new ArrayList<>();

        for (String el : input) {
            result.add(new PuzzleElement(el));
        }

        return result;
    }

    /** A function that searches elements for connections between them. The condition of connection is
     * equality of one element`s two last digits with other element`s first two digits **/
    void searchConnections(ArrayList<PuzzleElement> puzzleElements) {
        for (int i = 0; i < puzzleElements.size(); i++) {
            for (int j = i + 1; j < puzzleElements.size(); j++) {
                puzzleElements.get(i).checkConnection(puzzleElements.get(j), j, i);
            }
        }
    }

    /** A function that finds longest puzzle, by trying all elements as first element.
     * Head of the recursive function.
     * @return Longest puzzle **/
    Puzzle longestPuzzleFinder(ArrayList<PuzzleElement> puzzleElements) {
        int maxLength = 0;
        Puzzle puzzle, longestPuzzle = null;

        for (int i = 0; i < puzzleElements.size(); i++) {
            puzzle = puzzleBuilder(puzzleElements, new Puzzle(puzzleElements.get(i), i), maxLength);
            int currentLength = puzzle.puzzleValue.length();
            if (currentLength > maxLength) {
                maxLength = currentLength;
                longestPuzzle = puzzle;
            }
        }

        return longestPuzzle;
    }

    /** A recursive function that builds all possible puzzles, leaving only longest one.
     * @return Longest puzzle **/
    Puzzle puzzleBuilder(ArrayList<PuzzleElement> puzzleElements, Puzzle puzzle, int maxLen) {
        Puzzle longestPuzzle = new Puzzle(puzzle);

        for (int i = 0; i < puzzleElements.size(); i++) {
            for (int j : puzzleElements.get(i).tailConnectable) {
                if (!puzzle.elements.contains(j)) {
                    puzzle = new Puzzle(puzzle, puzzleElements.get(j), j);
                    puzzle = puzzleBuilder(puzzleElements, puzzle, maxLen);
                    int currLen = puzzle.puzzleValue.length();
                    if (currLen > maxLen) {
                        maxLen = currLen;
                        longestPuzzle = puzzle;
                    }
                }
            }
        }

        return longestPuzzle;
    }
}
