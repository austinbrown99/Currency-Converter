package Main;

import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;
import com.ritaja.xchangerate.util.Strategy;
import org.json.JSONException;

import javax.swing.*;
import java.math.BigDecimal;

public class Convert {
    // create the appropriate service object
    private static final CurrencyConverter converter = new CurrencyConverterBuilder()
            .strategy(Strategy.CURRENCY_LAYER_FILESTORE)
            .accessKey("2c963a083175cbb8f830d05b94fa3b1a")
            .buildConverter();

    /**
     * This method is called from the actionListener tied to the convert button
     * It takes the currency amount, currency converting from, and currency converting to
     * and uses the following api to convert
     * @apiNote https://github.com/Ritaja/java-exchange-rates
     *
     * @param fromCurrencyEntry The entry box the user enters currency value into
     * @param result The entry box that the result is entered into
     * @param fromCurrency The currency converted from
     * @param toCurrency The currency converted to
     */
    protected static void convert(JFormattedTextField fromCurrencyEntry, JTextField result, Currency fromCurrency, Currency toCurrency) throws ServiceException, StorageException, CurrencyNotSupportedException, EndpointException, JSONException {
        // convert between currencies
        // general format of conversion is convertCurrency(amount, fromCurrency, toCurrency)
        result.setText(String.valueOf(converter.convertCurrency(new BigDecimal(fromCurrencyEntry.getText()), fromCurrency, toCurrency)));
    }
}
