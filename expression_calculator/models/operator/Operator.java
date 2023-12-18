package expression_calculator.models.operator;

import expression_calculator.enums.Associativity;

public abstract class Operator {
    private int precedence;
    private Associativity associativity;

    public Operator(int precedence, Associativity associativity) {
        this.precedence = precedence;
        this.associativity = associativity;
    }

    public int getPrecedence() {
        return precedence;
    }

    public Associativity getAssociativity() {
        return associativity;
    }

    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }

    public void getAssociativity(Associativity associativity) {
        this.associativity = associativity;
    }
}
