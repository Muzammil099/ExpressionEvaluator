package expression_calculator.models.token;

public class Operand extends Token {
    Integer value;

    public Operand(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}