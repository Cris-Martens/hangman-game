import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

final int MAX_MISTAKES = 6;
final String filePath = "words.txt";

void main() {
    // create objects
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    ArrayList<String> words = new ArrayList<>();

    // Read strings from file into words list
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = reader.readLine()) != null)
            words.add(line.trim());
    }
    catch (FileNotFoundException e) {
        IO.println("Count not open file");
    }
    catch (IOException e) {
        IO.println("Something went wrong");
    }



    // Get word for game
    String word = words.get(random.nextInt(words.size()));


    ArrayList<Character> wordState = new ArrayList<>();

    for (int i = 0; i < word.length(); i++) {
        wordState.add('_');
    }

    IO.println("Welcome to Java Hangman!");

    int wrongGuesses = 0;
    while (wrongGuesses < MAX_MISTAKES) {
        IO.println(drawHangman(wrongGuesses));
        IO.print("Word: ");
        for (char c : wordState) {
            IO.print(c + " ");
        }
        IO.print("\n");
        IO.print("Guess a character: ");
        char guess = scanner.next().toLowerCase().charAt(0);

        if (word.indexOf(guess) >= 0) {
            IO.println("Correct!");
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    wordState.set(i, guess);
                }
            }
            if (!wordState.contains('_')) {
                IO.println("\n" + drawHangman(wrongGuesses));
                IO.println("Congradulations!\nThe word was " + word);
                break;
            }
        } else {
            IO.println("Wrong!");
            wrongGuesses++;
        }
    }
    if (wrongGuesses >= MAX_MISTAKES) {
        IO.println("\n" + drawHangman(wrongGuesses));
        IO.println("GAME OVER!\nThe word was " + word);

    }
}

public static String drawHangman(int wrongGuesses) {
    return switch(wrongGuesses) {
        case 0 -> """
                
                
                
                """;
        case 1 -> """
                         O
                
                
                """;
        case 2 -> """
                         O
                         |
                
                """;
        case 3 -> """
                         O
                        /|
                
                """;
        case 4 -> """
                         O
                        /|\\
                
                """;
        case 5 -> """
                         O
                        /|\\
                        /
                """;
        case 6 -> """
                         O
                        /|\\
                        / \\
                """;
        default -> "";
    };
}