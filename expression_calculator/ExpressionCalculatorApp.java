package expression_calculator;

import expression_calculator.services.ExpressionCalculatorService;
import expression_calculator.services.IExpressionCalculator;

public class ExpressionCalculatorApp {

    public static void main(String[] args) {


        System.out.print("Enter an expression: ");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String infixExpression = scanner.nextLine();

        IExpressionCalculator expressionCalculatorService = new ExpressionCalculatorService();

        Integer result = expressionCalculatorService.calculateExpression(infixExpression);
        System.out.println("Result: " + result);

        scanner.close();
    }

}
