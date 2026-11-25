package ejercicio06;

/**
 * Panel que se desvincula a sí mismo durante la notificación.
 * Demuestra que la lista de suscriptores es segura ante modificaciones simultáneas.
 */
public class SelfRemovingPanel implements RateObserver {

    private final CurrencyMarket market;

    public SelfRemovingPanel(CurrencyMarket market) {
        this.market = market;
    }

    @Override
    public void onRateUpdated(ExchangeRate exchangeRate) {
        System.out.println("Panel de un solo uso: recibió " + exchangeRate.currencyCode() + " y se desvincula durante el envío");
        market.removeObserver(this);
    }
}
