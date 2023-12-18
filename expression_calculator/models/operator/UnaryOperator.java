package expression_calculator.models.operator;

import expression_calculator.enums.Associativity;

public abstract class UnaryOperator extends Operator {
    public UnaryOperator(int precedence, Associativity associativity) {
        super(precedence, associativity);
    }

    public abstract int operate(int operand);

}
