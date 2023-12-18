package expression_calculator.models.operator;

import expression_calculator.enums.Associativity;

public class ReminderOperator extends BinaryOperator {
    public ReminderOperator(int precedence, Associativity associativity) {
        super(precedence, associativity);
    }

    @Override
    public int operate(int operand1, int operand2) {
        return operand1 % operand2;
    }

}
