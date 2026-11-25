package ejercicio06;

/**
 * Interfaz común de las herramientas de visualización de los operadores.
 */
public interface RateObserver {

    void onRateUpdated(ExchangeRate exchangeRate);
}
