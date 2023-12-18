package expression_calculator.configs;


import expression_calculator.enums.Associativity;
import expression_calculator.factories.OperatorFactory;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static void loadOperatorConfig() {

        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream("operator-config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            OperatorFactory operatorFactory = OperatorFactory.getInstance();
            for (Object key : properties.keySet()) {
                String operatorKey = (String) key;
                char operatorSymbol = operatorKey.charAt(0);

                int precedence = Integer.parseInt(properties.getProperty(operatorSymbol + ".precedence"));
                Associativity associativity = Associativity.valueOf(properties.getProperty(operatorSymbol + ".associativity"));
                operatorFactory.updateOperatorConfig(operatorSymbol, precedence, associativity);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
