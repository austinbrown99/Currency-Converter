package Main;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

/**
 * Currency Converter
 * This program converts the value inputted by the user to the desired currency
 * There is the ability to convert between 168 different currencies
 * It uses a conversion api created by Ritaja and can be found at the following link
 * https://github.com/Ritaja/java-exchange-rates
 *
 * @author Austin Brown
 * @version 1.0
 * @since 3/12/2021
 */

public class createWindow {
    public static void main(String[] args) {
        createTheWindow();
    }

    /**
     * This method creates the window
     * The convert button calls Convert.convert and passes the entry entry, result entry, converting from, and converting to
     * The close button closes the window
     */
    private static void createTheWindow() {
        Currency[] currencyChoices = Currency.values();

        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 300));

        JPanel titlePanel = (JPanel) frame.getContentPane();
        titlePanel.setLayout(null);

        JPanel bodyPanel = (JPanel) frame.getContentPane();
        bodyPanel.setLayout(null);

        JLabel title = new JLabel("Currency Converter", JLabel.CENTER);
        titlePanel.add(title);
        title.setVerticalAlignment(JLabel.TOP);
        title.setFont(new Font("Verdana", Font.BOLD, 20));
        title.setBounds(0, 10, 500, 100);
        title.setBackground(new Color(50, 50, 50));

        JLabel convertThis = new JLabel("Convert This Amount:");
        bodyPanel.add(convertThis);
        convertThis.setBounds(20, 75, (int) convertThis.getPreferredSize().getWidth(), (int) convertThis.getPreferredSize().getHeight());

        JLabel fromThisCurrency = new JLabel("From This Currency:");
        bodyPanel.add(fromThisCurrency);
        fromThisCurrency.setBounds(190, 75, (int) fromThisCurrency.getPreferredSize().getWidth(), (int) fromThisCurrency.getPreferredSize().getHeight());

        JLabel toThisCurrency = new JLabel("To This Currency:");
        bodyPanel.add(toThisCurrency);
        toThisCurrency.setBounds(350, 75, (int) toThisCurrency.getPreferredSize().getWidth(), (int) toThisCurrency.getPreferredSize().getHeight());

        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        JFormattedTextField convertThisNumber = new JFormattedTextField(amountFormat);
        bodyPanel.add(convertThisNumber);
        convertThisNumber.setColumns(10);
        convertThisNumber.setBounds(20, 100, (int) convertThis.getPreferredSize().getWidth(), 25);

        JComboBox<Currency> fromCurrency = new JComboBox<>(currencyChoices);
        bodyPanel.add(fromCurrency);
        fromCurrency.setBounds(190, 100, (int) fromThisCurrency.getPreferredSize().getWidth(), 25);

        JComboBox<Currency> toCurrency = new JComboBox<>(currencyChoices);
        bodyPanel.add(toCurrency);
        toCurrency.setBounds(350, 100, (int) toThisCurrency.getPreferredSize().getWidth(), 25);

        JLabel resultLabel = new JLabel("Result:");
        bodyPanel.add(resultLabel);
        resultLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        resultLabel.setBounds(130, 150, (int) resultLabel.getPreferredSize().getWidth(), (int) resultLabel.getPreferredSize().getHeight());

        JTextField result = new JTextField();
        bodyPanel.add(result);
        result.setBounds(190, 150, 150, 25);
        result.setEditable(false);
        result.setBackground(Color.white);

        JButton convert = new JButton("Convert");
        bodyPanel.add(convert);
        convert.setBounds(125, 200, 100, 35);
        convert.addActionListener(e -> {
            try {
                Convert.convert(convertThisNumber, result, Currency.get(String.valueOf(fromCurrency.getSelectedItem())), Currency.get(String.valueOf(toCurrency.getSelectedItem())));
            } catch (ServiceException | StorageException | CurrencyNotSupportedException | EndpointException | JSONException serviceException) {
                serviceException.printStackTrace();
            }
        });

        JButton close = new JButton("Close");
        bodyPanel.add(close);
        close.setBounds(250, 200, 100, 35);
        close.addActionListener(e -> frame.dispose());

        frame.pack();
        frame.setVisible(true);
    }
}
