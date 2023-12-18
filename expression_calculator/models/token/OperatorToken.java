package expression_calculator.models.token;

public class OperatorToken extends Token {
    char symbol;

    public OperatorToken(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
