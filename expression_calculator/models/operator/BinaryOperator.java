package expression_calculator.models.operator;

import expression_calculator.enums.Associativity;

public abstract class BinaryOperator extends Operator {
    public BinaryOperator(int precedence, Associativity associativity) {
        super(precedence, associativity);
    }

    public abstract int operate(int operand1, int operand2);

}
