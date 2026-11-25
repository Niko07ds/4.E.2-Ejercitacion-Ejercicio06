# Ejercicio 06 - Panel Financiero de Variación de Divisas

## Descripción

Una plataforma de cambio de moneda procesa la cotización del dólar y del euro en tiempo real y alimenta al mismo tiempo a distintas herramientas de visualización para los operadores: gráficos de tendencia, tablas de conversión y alarmas de límite.

## Objetivos

- Propagar datos numéricos frecuentes desde un punto centralizado de notificación.
- Calcular la diferencia porcentual respecto al ciclo anterior.
- Enviar a los paneles solo los datos necesarios para redibujarse.
- Evitar errores ante modificaciones simultáneas de la lista de suscriptores.

## Implementación del patrón Observer

Se usa la variante push con un objeto de datos inmutable.

- **Sujeto:** `CurrencyMarket` guarda la cotización de cada divisa. En `updateRate` calcula la variación porcentual y notifica.
- **Dato enviado:** `ExchangeRate` es un `record` con el código de la divisa, la cotización actual, la anterior y la variación.
- **Observador:** `RateObserver` declara `onRateUpdated(ExchangeRate exchangeRate)`.
- **Observadores concretos:** `TrendChartPanel`, `ConversionTablePanel` y `LimitAlarmPanel`.
- **Seguridad concurrente:** la lista es un `CopyOnWriteArrayList`. `SelfRemovingPanel` se desvincula durante el envío sin provocar errores.
- **Valores reales:** el mercado parte de las cotizaciones del 25/11/2026 en pesos argentinos: dólar oficial del Banco Nación a 1.615,00 y euro a 1.805,00 (precio de venta). Las variaciones posteriores son simuladas.

## Propósito de la clase

- `ExchangeRate`: cotización inmutable que reciben los paneles.
- `RateObserver`: interfaz común de las herramientas de visualización.
- `CurrencyMarket`: mercado de divisas y sujeto observable.
- `TrendChartPanel`: indica si la divisa sube, baja o se mantiene.
- `ConversionTablePanel`: muestra el valor en pesos.
- `LimitAlarmPanel`: se dispara cuando una divisa alcanza un límite.
- `SelfRemovingPanel`: panel que se desvincula en plena notificación.
- `MoneyFormat`: utilidad que da formato argentino a montos y porcentajes.
- `Main`: parte de las cotizaciones reales, simula cambios de precio, desvincula paneles y prueba una divisa desconocida.

## Concepto clave

Los paneles reciben un objeto liviano e inmutable con todo lo necesario para redibujarse, sin consultar al mercado. Además, la lista de suscriptores admite cambios durante la notificación.

## Resultado

![ejercicio06.png](ejercicio06.png)

> Las cotizaciones iniciales corresponden actualemte y al cambiar todos los días: para usar valores actuales hay que modificar las constantes `INITIAL_USD` e `INITIAL_EUR` de `Main`.

## Conclusión

El patrón Observer permite alimentar muchas herramientas con una sola actualización de cotización. Con datos inmutables y una lista segura ante concurrencia, el sistema es eficiente y fácil de ampliar con nuevos paneles.
