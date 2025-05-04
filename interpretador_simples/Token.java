package interpretador_simples;

public class Token {
    public final TokenType type;
    public final String lexeme;
    public final Object literal;

    public Token(TokenType type, String lexeme, Object literal) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "Token{" + type + " " + lexeme + " " + literal + "}";
    }
}

enum TokenType {
    NUMBER, VARIABLE,
    PLUS, MINUS, STAR, SLASH,
    EQUAL,
    LPAREN, RPAREN,
    EOF
}