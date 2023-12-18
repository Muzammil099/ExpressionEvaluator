package expression_calculator.models.operator;

import expression_calculator.enums.Associativity;

public class SubtractionOperator extends BinaryOperator {
    public SubtractionOperator(int precedence, Associativity associativity) {
        super(precedence, associativity);
    }

    @Override
    public int operate(int operand1, int operand2) {
        return operand1 - operand2;
    }

}
