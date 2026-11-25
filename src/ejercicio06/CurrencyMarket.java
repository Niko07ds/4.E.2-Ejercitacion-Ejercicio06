package ejercicio06;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Mercado de divisas (sujeto observable).
 * Guarda la cotización de cada divisa y notifica a los paneles cuando cambia.
 * La lista de suscriptores es segura ante modificaciones durante la notificación.
 */
public class CurrencyMarket {

    private final Map<String, Double> rates = new HashMap<>();
    private final CopyOnWriteArrayList<RateObserver> observers = new CopyOnWriteArrayList<>();

    public CurrencyMarket(Map<String, Double> initialRates) {
        rates.putAll(initialRates);
    }

    public void addObserver(RateObserver observer) {
        if (observer != null) {
            observers.addIfAbsent(observer);
        }
    }

    public void removeObserver(RateObserver observer) {
        observers.remove(observer);
    }

    /**
     * Actualiza la cotización de una divisa, calcula la diferencia porcentual respecto
     * al ciclo anterior y envía la nueva cotización a todos los paneles suscritos.
     */
    public synchronized void updateRate(String currencyCode, double newRate) {
        Double previousRate = rates.get(currencyCode);
        if (previousRate == null) {
            throw new IllegalArgumentException("Divisa desconocida: " + currencyCode);
        }
        if (newRate <= 0) {
            throw new IllegalArgumentException("La cotización debe ser mayor a cero");
        }
        double changePercent = (newRate - previousRate) / previousRate * 100;
        rates.put(currencyCode, newRate);

        ExchangeRate exchangeRate = new ExchangeRate(currencyCode, newRate, previousRate, changePercent);
        for (RateObserver observer : observers) {
            observer.onRateUpdated(exchangeRate);
        }
    }
}
