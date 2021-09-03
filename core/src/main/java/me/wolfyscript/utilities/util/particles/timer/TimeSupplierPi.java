package me.wolfyscript.utilities.util.particles.timer;

import com.google.common.base.Preconditions;
import me.wolfyscript.utilities.util.NamespacedKey;

/**
 * Provides an increasing value by a fraction of PI.<br>
 * Formula: <br>
 * <code>t += Math.PI / fraction</code>
 */
public class TimeSupplierPi extends TimeSupplier {

    private double fraction;

    public TimeSupplierPi() {
        this(1);
    }

    public TimeSupplierPi(double fraction) {
        super(NamespacedKey.wolfyutilties("pi_fraction"));
        this.fraction = fraction;
    }

    public double getFraction() {
        return fraction;
    }

    public void setFraction(double fraction) {
        Preconditions.checkArgument(fraction != 0, "Cannot divide by 0! Must be less or bigger than 0!");
        Preconditions.checkArgument(fraction > 0 ? (stopValue > 0) : (stopValue < 0), "Invalid fraction! A fraction of " + fraction + " and a stop value of " + stopValue + " will cause it to increase/decrease indefinitely!");
        this.fraction = fraction;
    }

    @Override
    public double increase(double time) {
        time += Math.PI / fraction;
        return time;
    }

    @Override
    public void setStopValue(double stopValue) {
        Preconditions.checkArgument(fraction > 0 ? (stopValue > 0) : (stopValue < 0), "Invalid stop time! Value of " + stopValue + " will cause it to increase/decrease indefinitely!");
        super.setStopValue(stopValue);
    }

    public boolean shouldStop(double time) {
        if (fraction > 0) {
            return time > getStopValue();
        }
        return time < getStopValue();
    }
}
