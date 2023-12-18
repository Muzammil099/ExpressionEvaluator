package expression_calculator.factories;

import expression_calculator.configs.ConfigLoader;
import expression_calculator.enums.Associativity;
import expression_calculator.models.operator.Operator;
import expression_calculator.models.operator.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OperatorFactory {
    private static final Map<Character, Operator> operators =  new HashMap<>();

    private static OperatorFactory instance = null;
    private OperatorFactory() {

        if(operators.isEmpty()) {
            operators.putIfAbsent('a', new AdditionOperator(1, Associativity.LEFT));
            operators.putIfAbsent('s', new SubtractionOperator(2, Associativity.LEFT));
            operators.putIfAbsent('m', new MultiplicationOperator(3, Associativity.LEFT));
            operators.putIfAbsent('d', new DivisionOperator(4, Associativity.LEFT));
            operators.putIfAbsent('r', new ReminderOperator(5, Associativity.LEFT));
            ConfigLoader.loadOperatorConfig();
        }
    }

    public static OperatorFactory getInstance(){

        if(instance == null){
            instance = new OperatorFactory();
        }
        return instance;
    }

    public Operator getOperator(char symbol) {
        return operators.get(symbol);
    }

    public Set<Character> getAllowableOperator(){
        return operators.keySet();
    }
    public void updateOperatorConfig(Character operatorSymbol, int precedence, Associativity associativity){

        Operator operator = operators.get(operatorSymbol);
        operator.setPrecedence(precedence);
        operator.getAssociativity(associativity);

    }

}

