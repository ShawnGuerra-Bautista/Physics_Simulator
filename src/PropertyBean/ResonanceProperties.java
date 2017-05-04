package PropertyBean;

import Main.PhysicsInterface;
import static Main.PhysicsInterface.speedOfSound;
import static javafx.beans.binding.Bindings.divide;
import static javafx.beans.binding.Bindings.multiply;
import static javafx.beans.binding.Bindings.subtract;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class ResonanceProperties implements PhysicsInterface {

    private final DoubleProperty orderOfHarmonic = new SimpleDoubleProperty();

    public double getOrderOfHarmonic() {
        return orderOfHarmonic.get();
    }

    public void setOrderOfHarmonic(double value) {
        orderOfHarmonic.set(value);
    }

    public DoubleProperty orderOfHarmonicProperty() {
        return orderOfHarmonic;
    }

    private final DoubleProperty wavelength = new SimpleDoubleProperty();

    public double getWavelength() {
        return wavelength.get();
    }

    public void setWavelength(double value) {
        wavelength.set(value);
    }

    public DoubleProperty wavelengthProperty() {
        wavelength.bind(factorProperty());
        return wavelength;
    }

    private final DoubleProperty frequency = new SimpleDoubleProperty();

    public double getFrequency() {
        return frequency.get();
    }

    public void setFrequency(double value) {
        frequency.set(value);
    }

    public DoubleProperty frequencyProperty() {
        frequency.bind(divide(speedOfSound, wavelengthProperty()));
        return frequency;
    }

    private final DoubleProperty factor = new SimpleDoubleProperty();

    public double getFactor() {
        return factor.get();
    }

    public void setFactor(double value) {
        factor.set(value);
    }

    public DoubleProperty factorProperty() {
        factor.bind(divide(4,
                subtract(multiply(2, orderOfHarmonicProperty()), 1)));
        return factor;
    }

    private final DoubleProperty wavelengthToDrawGraph = new SimpleDoubleProperty();

    public double getWavelengthToDrawGraph() {
        return wavelengthToDrawGraph.get();
    }

    public void setWavelengthToDrawGraph(double value) {
        wavelengthToDrawGraph.set(value);
    }

    public DoubleProperty wavelengthToDrawGraphProperty() {
        wavelengthToDrawGraph.bind(multiply(factorProperty(), 900));
        return wavelengthToDrawGraph;
    }

    private final DoubleProperty k = new SimpleDoubleProperty();

    public double getK() {
        return k.get();
    }

    public void setK(double value) {
        k.set(value);
    }

    public DoubleProperty kProperty() {
        k.bind(divide(piTimesTwo, wavelengthToDrawGraphProperty()));
        return k;
    }

    private final DoubleProperty amplitudeMax = new SimpleDoubleProperty();

    public double getAmplitudeMax() {
        return amplitudeMax.get();
    }

    public void setAmplitudeMax(double value) {
        amplitudeMax.set(value);
    }

    public DoubleProperty amplitudeMaxProperty() {
        amplitudeMax.bind(divide(wavelengthToDrawGraphProperty(), 24));
        return amplitudeMax;
    }
    private final DoubleProperty amplitude = new SimpleDoubleProperty();

    public double getAmplitude() {
        return amplitude.get();
    }

    public void setAmplitude(double value) {
        amplitude.set(value);
    }

    public DoubleProperty amplitudeProperty() {
        return amplitude;
    }

    private final DoubleProperty amplitudeMultiple = new SimpleDoubleProperty();

    public double getAmplitudeMultiple() {
        return amplitudeMultiple.get();
    }

    public void setAmplitudeMultiple(double value) {
        amplitudeMultiple.set(value);
    }

    public DoubleProperty amplitudeMultipleProperty() {
        return amplitudeMultiple;
    }

    private final DoubleProperty positiveCosine = new SimpleDoubleProperty();

    public double getPositiveCosine() {
        return positiveCosine.get();
    }

    public void setPositiveCosine(double value) {
        positiveCosine.set(value);
    }

    public DoubleProperty positiveCosineProperty() {
        return positiveCosine;
    }

    private final DoubleProperty negativeCosine = new SimpleDoubleProperty();

    public double getNegativeCosine() {
        return negativeCosine.get();
    }

    public void setNegativeCosine(double value) {
        negativeCosine.set(value);
    }

    public DoubleProperty negativeCosineProperty() {
        return negativeCosine;
    }

    private final DoubleProperty time = new SimpleDoubleProperty();

    public double getTime() {
        return time.get();
    }

    public void setTime(double value) {
        time.set(value);
    }

    public DoubleProperty timeProperty() {
        return time;
    }
}
