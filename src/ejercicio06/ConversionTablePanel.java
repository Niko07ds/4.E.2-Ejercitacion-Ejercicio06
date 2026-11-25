package ejercicio06;

/**
 * Tabla de conversión con el valor actual de la divisa en pesos.
 */
public class ConversionTablePanel implements RateObserver {

    @Override
    public void onRateUpdated(ExchangeRate exchangeRate) {
        System.out.println("Tabla de conversión: 1 " + exchangeRate.currencyCode() + " = "
                + MoneyFormat.amount(exchangeRate.rate()) + " pesos | 100 " + exchangeRate.currencyCode() + " = "
                + MoneyFormat.amount(exchangeRate.rate() * 100) + " pesos");
    }
}
