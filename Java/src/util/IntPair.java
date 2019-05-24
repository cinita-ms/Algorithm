package util;

import java.util.Objects;

public class IntPair {

    public final int first;
    public final int second;

    public IntPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair[" + this.first + "," + this.second + "]";
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

    public static IntPair of(int first, int second) {
        return new IntPair(first, second);
    }
}
