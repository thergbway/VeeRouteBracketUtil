package veeroute;

import veeroute.bracket.BracketExpression;
import veeroute.bracket.exception.ParseException;

import java.io.Console;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();

        if (console == null) {
            System.out.println("Console does not exist, sorry...");
            return;
        }

        while (true) {
            String input = console.readLine("Type expression or exit: ");
            if (input.equalsIgnoreCase("exit"))
                return;
            try {

                BracketExpression bracketExpression = BracketExpression.parseExpression(input);
                console.printf("Your expression: %s%n", bracketExpression);
                console.printf("Expression details:%n%s", bracketExpression.toStringWithPositions());
            } catch (ParseException e) {
                console.printf("You typed invalid expression!%n");
                console.printf("Exception details: %s%n", e.getMessage());
                e.printStackTrace(console.writer());
            } finally {
                console.printf("%n%n");
            }
        }
    }
}