package ejercicio06;

import java.util.Map;

public class Main {

    // Valores iniciales reales al 25/11/2026 (precio de venta en pesos argentinos):
    // dólar oficial del Banco Nación y euro informado ese día en bancos de Argentina
    private static final double INITIAL_USD = 1615.00;
    private static final double INITIAL_EUR = 1805.00;

    // Límite de la alarma: aproximadamente un 2 % por encima del dólar inicial
    private static final double USD_ALARM_LIMIT = 1650.00;

    public static void main(String[] args) {
        CurrencyMarket market = new CurrencyMarket(Map.of("USD", INITIAL_USD, "EUR", INITIAL_EUR));

        RateObserver trend = new TrendChartPanel();
        RateObserver table = new ConversionTablePanel();
        RateObserver alarm = new LimitAlarmPanel("USD", USD_ALARM_LIMIT);
        RateObserver oneShot = new SelfRemovingPanel(market);

        System.out.println("Suscribiendo las herramientas de visualización");
        System.out.println("Cotizaciones iniciales: USD = " + MoneyFormat.amount(INITIAL_USD)
                + " pesos, EUR = " + MoneyFormat.amount(INITIAL_EUR) + " pesos");
        market.addObserver(trend);
        market.addObserver(table);
        market.addObserver(alarm);
        market.addObserver(oneShot);

        // A partir de acá las variaciones son simuladas
        double usdUp = 1628.50;
        System.out.println();
        System.out.println("Nueva cotización del dólar: " + MoneyFormat.amount(usdUp));
        market.updateRate("USD", usdUp);

        double eurUp = 1830.00;
        System.out.println();
        System.out.println("Nueva cotización del euro: " + MoneyFormat.amount(eurUp));
        market.updateRate("EUR", eurUp);

        double usdSpike = 1655.00;
        System.out.println();
        System.out.println("Nueva cotización del dólar: " + MoneyFormat.amount(usdSpike));
        market.updateRate("USD", usdSpike);

        System.out.println();
        System.out.println("Se desvincula el gráfico de tendencia");
        market.removeObserver(trend);

        double eurDown = 1790.00;
        System.out.println();
        System.out.println("Nueva cotización del euro: " + MoneyFormat.amount(eurDown));
        market.updateRate("EUR", eurDown);

        System.out.println();
        System.out.println("Intento de actualizar una divisa desconocida");
        try {
            market.updateRate("GBP", 2100.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Operación rechazada: " + e.getMessage());
        }
    }
}
