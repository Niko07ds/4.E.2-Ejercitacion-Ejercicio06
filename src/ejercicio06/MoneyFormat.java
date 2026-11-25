package ejercicio06;

import java.util.Locale;

/**
 * Utilidad de formato con la convención argentina (punto para miles y coma para decimales),
 * para que la salida sea la misma sin importar el idioma del sistema.
 */
public final class MoneyFormat {

    private static final Locale ARGENTINA = Locale.forLanguageTag("es-AR");

    private MoneyFormat() {
    }

    public static String amount(double value) {
        return String.format(ARGENTINA, "%,.2f", value);
    }

    public static String percent(double value) {
        return String.format(ARGENTINA, "%+.2f%%", value);
    }
}
