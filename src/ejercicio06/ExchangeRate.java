package ejercicio06;

/**
 * Cotización inmutable de una divisa. Contiene solo los datos primitivos
 * que los paneles necesitan para redibujarse.
 */
public record ExchangeRate(String currencyCode, double rate, double previousRate, double changePercent) {
}
