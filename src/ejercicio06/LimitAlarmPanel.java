package ejercicio06;

/**
 * Alarma de límite: se dispara cuando una divisa específica alcanza o supera un valor máximo.
 */
public class LimitAlarmPanel implements RateObserver {

    private final String currencyCode;
    private final double upperLimit;

    public LimitAlarmPanel(String currencyCode, double upperLimit) {
        this.currencyCode = currencyCode;
        this.upperLimit = upperLimit;
    }

    @Override
    public void onRateUpdated(ExchangeRate exchangeRate) {
        if (exchangeRate.currencyCode().equals(currencyCode) && exchangeRate.rate() >= upperLimit) {
            System.out.println("Alarma de límite: " + currencyCode + " llegó a " + MoneyFormat.amount(exchangeRate.rate())
                    + " y supera el límite de " + MoneyFormat.amount(upperLimit));
        }
    }
}
