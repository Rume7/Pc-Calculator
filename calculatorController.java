package mycalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class calculatorController
{
    @FXML private TextArea screen;
    @FXML private String lastOperator = "";

    /**
     * @param evt
     * @modifies concatenates all the numbers and dot buttons clicked to the screen
     */
    public void digitClickedHandler(ActionEvent evt)
    {
        Button clickedButton = (Button)evt.getTarget();
        String buttonLabel = clickedButton.getText();

        checkScreen();

        if (buttonLabel.equals("1"))
            screen.appendText("1");
        else if (buttonLabel.equals("2"))
            screen.appendText("2");
        else if (buttonLabel.equals("3"))
            screen.appendText("3");
        else if (buttonLabel.equals("4"))
            screen.appendText("4");
        else if (buttonLabel.equals("5"))
            screen.appendText("5");
        else if (buttonLabel.equals("6"))
            screen.appendText("6");
        else if (buttonLabel.equals("7"))
            screen.appendText("7");
        else if (buttonLabel.equals("8"))
            screen.appendText("8");
        else if (buttonLabel.equals("9"))
            screen.appendText("9");
        else if (buttonLabel.equals("0"))
            screen.appendText("0");
        else if (buttonLabel.equals("."))
            screen.appendText(".");
    }

    public void operationClickHandler(ActionEvent evt)
    {
        Button clickedOperator = (Button)evt.getTarget();
        String operatorLabel = clickedOperator.getText();

        if (operatorLabel.equals("+ -"))
        {
//            try
//            {
//                if (screen.getText().length() > 0)
//                {
//                    if (screen.getText().charAt(0) == '+')
//                        screen.setText("-" + extractValue());
//                    else if (screen.getText().charAt(0) == '-')
//                        screen.setText("" + extractValue());
//                }
//            }
//            catch (CalculatorException ce)
//            {
//                screen.setText("Check your values.");
//            }
        }
        else if (operatorLabel.equals("+"))
            screen.appendText(" + ");
        else if (operatorLabel.equals("-"))
            screen.appendText(" - ");
        else if (operatorLabel.equals("X"))
            screen.appendText(" X ");
        else  if (operatorLabel.equals("/"))
            screen.appendText(" / ");
        else if (operatorLabel.equals("="))
        {
            double result = compute(screen.getText());

            String resultString = result + "";

            if (resultString.endsWith("0"))
                screen.appendText(" = " + resultString.substring(0, resultString.length()-2));
            else
                screen.appendText(" = " + result);

            lastOperator = "=";
        }
    }

    /**
     * @modifies takes in the numbers and operators on the screen and computes them.
     * @returns the value obtained from either addition, et al.
     */
    private double compute(String string)
    {
        char[] operator = new char[]{'+', '-','X', '/'};
        String op1 = "", op2 = "";
        char operand = ' ';

        for (int i = 0; i < string.length(); i++)
        {
            char op = string.charAt(i);
            if (op == '+' || op == '-' || op == '/' || op=='X')
            {
                op1 = string.substring(0, i).trim();
                operand = string.charAt(i);
                op2 = string.substring((i+1)).trim();
                break;
            }
        }

        double answer = 0;

        switch (operand)
        {
            case '+':
                answer = Double.parseDouble(op1) + Double.parseDouble(op2);
                break;
            case '-':
                answer = Double.parseDouble(op1) - Double.parseDouble(op2);
                break;
            case 'X':
                answer = Double.parseDouble(op1) * Double.parseDouble(op2);
                break;
            case '/':
                try{
                    answer = Double.parseDouble(op1) / Double.parseDouble(op2);
                    break;
                }
                catch (ArithmeticException ae)
                {
                    screen.setText("Wrong division");
                }

        }
        return answer;
    }

    /**
     * @modifies checks the screen if the equals sign has been entered
     * and set the last operator to an empty string.
     */
    @FXML private void checkScreen()
    {
        if (lastOperator.equals("="))
        {
            screen.setText("");
            lastOperator = "";
        }
    }

    @FXML private Button sine;
    @FXML private Button cosine;
    @FXML private Button tangent;

    @FXML public void trigonometric(ActionEvent evt)
    {
        double value = 0;

        try {
            if (evt.getTarget().equals(sine))
            {
                value = Math.sin(extractValue());
                screen.setText("sin " + screen.getText() + " = " + value);
            }
            else if (evt.getTarget().equals(cosine))
            {
                value = Math.cos(extractValue());
                screen.setText("cos " + screen.getText() + " = " + value);
            }
            else if (evt.getTarget().equals(tangent))
            {
                value = Math.tan(extractValue());
                screen.setText("tan " + screen.getText() + " = " + value);
            }
        }
        catch (CalculatorException ce)
        {
            screen.setText(ce.getMessage());
        }
        catch (ArithmeticException ae)
        {
            screen.setText("Wrong arithmetic operation");
        }
    }

    /**
         * get the text on the screen
         * separate the number from the point at which the equal sign exit
         * carry out the operation of the trigonometry
         */
    @FXML private double extractValue() throws CalculatorException
    {
        double value = 0;

        if (screen.getText().length() > 0)
        {
            if (screen.getText().contains("="))
            {
                String screenValue = screen.getText();
                int equalIndex = screenValue.lastIndexOf("=");
                String figureString = screenValue.substring(equalIndex + 1).trim();
                value = Double.parseDouble(figureString);
                screen.setText(figureString);
                return value;
            }
            else
                return Double.parseDouble(screen.getText());
        }
        throw new CalculatorException();
    }

    @FXML public void clearScreen(ActionEvent evt)
    {
        Button clear = (Button)evt.getSource();

        if (clear.getText().equals("C"))
            screen.setText("");
    }

    @FXML public void OnOrOff(ActionEvent evt)
    {
        Button clickedButton = (Button)evt.getTarget();
        String buttonLabel = clickedButton.getText();

        if ("OFF".equals(buttonLabel)){
            screen.setText("");
            System.exit(0);
        }
    }
}
