package util;

import java.util.Objects;

public class Pair<F, S> {

    public final F first;
    public final S second;

    public Pair(F first, S second) {
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

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }
}
