package expression_calculator.services;

import expression_calculator.enums.Associativity;
import expression_calculator.factories.OperatorFactory;
import expression_calculator.models.operator.BinaryOperator;
import expression_calculator.models.operator.Operator;
import expression_calculator.models.operator.UnaryOperator;
import expression_calculator.models.token.Operand;
import expression_calculator.models.token.OperatorToken;
import expression_calculator.models.token.Token;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionCalculatorService implements IExpressionCalculator {

    OperatorFactory operatorFactory;

    public ExpressionCalculatorService(){
        this.operatorFactory = OperatorFactory.getInstance();
    }

    public Integer calculateExpression(String infixExpression) {

        List<Token> tokens = tokenize(infixExpression);

        List<Token> postfixExpression = infixToPostfix(tokens);

        return evaluatePostfix(postfixExpression);

    }

    private List<Token> tokenize(String infixExpression) {

        infixExpression = infixExpression.replaceAll("\\s", "");

        List<Token> tokens = new ArrayList<>();

        validateExpression(infixExpression);

        String operandRegex = "\\d+";
        String operatorRegex = "[" + getAllowableOperators() +"]";
        String combinedRegex = operandRegex + "|" + operatorRegex;

        Pattern pattern = Pattern.compile(combinedRegex);
        Matcher matcher = pattern.matcher(infixExpression);

        System.out.println("Here is the operator and operands");
        matcher.reset();
        while (matcher.find()) {
            if(isOperand(matcher.group())){
                System.out.println(String.format("Operand: %s", matcher.group()));
                tokens.add(new Operand(Integer.parseInt(matcher.group())));
            }
            else if(isOperator(matcher.group())){
                System.out.println(String.format("Operator: %s", matcher.group()));
                tokens.add(new OperatorToken(matcher.group().charAt(0)));
            }
        }


        return tokens;
    }

    private Matcher validateExpression(String infixExpression) {

        String expressionRegex = "[\\d[" + getAllowableOperators() + "]]+\\d$";
        Pattern pattern = Pattern.compile(expressionRegex);
        Matcher matcher = pattern.matcher(infixExpression);

        if(!matcher.matches()){
            throw new IllegalArgumentException("Invalid expression");
        }
        return matcher;
    }

    private boolean isOperand(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return token.length() == 1 && operatorFactory.getAllowableOperator().contains(token.charAt(0));
    }

    private String getAllowableOperators(){

        Set<Character> allowableOperators = operatorFactory.getAllowableOperator();
        StringBuilder stringBuilder = new StringBuilder();
        for (Character ch : allowableOperators) {
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();

    }

    private List<Token> infixToPostfix(List<Token> tokens) {

        List<Token> postfixExpression = new ArrayList<>();
        Stack<OperatorToken> operatorStack = new Stack<>();

        for (Token token : tokens) {
            if (token instanceof Operand) {
                postfixExpression.add(token);
            }
            else {
                OperatorToken operatorToken = ((OperatorToken)token);
                while (!operatorStack.isEmpty() && hasLowerPrecedence(operatorFactory.getOperator(operatorToken.getSymbol()), operatorFactory.getOperator(operatorStack.peek().getSymbol()))) {
                    postfixExpression.add(operatorStack.pop());
                }
                operatorStack.push(operatorToken);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExpression.add(operatorStack.pop());
        }

        return postfixExpression;

    }

    private int evaluatePostfix(List<Token> postfixExpression) {

        Stack<Integer> stack = new Stack<>();

        for (Token token : postfixExpression) {
            if (token instanceof Operand) {
                stack.push(((Operand) token).getValue());
            } else {
                OperatorToken operatorToken = (OperatorToken) token;
                Operator operator = operatorFactory.getOperator(operatorToken.getSymbol());
                if(operator instanceof BinaryOperator){

                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    System.out.println(String.format("Evaluating expression %d %c %d",operand1, operatorToken.getSymbol(), operand2));
                    stack.push(((BinaryOperator) operator).operate(operand1,operand2));
                }
                else if(operator instanceof UnaryOperator){
                    int operand1 = stack.pop();
                    System.out.println(String.format("Evaluating expression %c %d", operatorToken.getSymbol(), operand1 ));
                    stack.push(((UnaryOperator) operator).operate(operand1));
                }
            }
        }

        return stack.pop();

    }

    private boolean hasLowerPrecedence(Operator op1, Operator op2) {

        if (op1.getAssociativity() == Associativity.LEFT) {
            return op1.getPrecedence() > op2.getPrecedence();
        } else {
            return op1.getPrecedence() >= op2.getPrecedence();
        }

    }


}
