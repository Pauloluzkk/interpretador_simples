package interpretador_simples;

import java.util.List;
import java.util.Scanner;

public class REPL {
    private final Environment environment = new Environment();
    private final Evaluator evaluator = new Evaluator(environment);

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Simple REPL Interpreter. Type 'exit' to quit.");

        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            try {
                Object result = interpret(input);
                if (result != null) {
                    System.out.println(result);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    private Object interpret(String input) {
        if (environment.hasVariable(input)) {
            return input + " = " + environment.getVariable(input);
        }

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.scanTokens();
        Parser parser = new Parser(tokens);
        Expr expr = parser.parse();
        return evaluator.evaluate(expr);
    }

    public static void main(String[] args) {
        new REPL().start();
    }
}