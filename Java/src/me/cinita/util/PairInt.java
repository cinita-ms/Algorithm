package me.cinita.util;

import java.util.Objects;

public class PairInt {

    public final int first;
    public final int second;

    public PairInt(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "IntPair[" + this.first + "," + this.second + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pair
                && Objects.equals(this.first, ((Pair) obj).first)
                && Objects.equals(this.second, ((Pair) obj).second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.first, this, second);
    }

    public static PairInt of(int first, int second) {
        return new PairInt(first, second);
    }
}
