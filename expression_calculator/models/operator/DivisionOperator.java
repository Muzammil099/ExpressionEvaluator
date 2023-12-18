package expression_calculator.models.operator;

import expression_calculator.enums.Associativity;

public class DivisionOperator extends BinaryOperator {
    public DivisionOperator(int precedence, Associativity associativity) {
        super(precedence, associativity);
    }

    @Override
    public int operate(int operand1, int operand2) {
        if(operand2 == 0)
            throw new ArithmeticException("Division by zero");
        return operand1 / operand2;
    }
}
