package ejercicio06;

/**
 * Gráfico de tendencia: indica si la divisa sube, baja o se mantiene.
 */
public class TrendChartPanel implements RateObserver {

    @Override
    public void onRateUpdated(ExchangeRate exchangeRate) {
        String trend = exchangeRate.changePercent() > 0 ? "sube" : exchangeRate.changePercent() < 0 ? "baja" : "estable";
        System.out.println("Gráfico de tendencia: " + exchangeRate.currencyCode() + " " + trend
                + " (" + MoneyFormat.percent(exchangeRate.changePercent()) + ")");
    }
}
