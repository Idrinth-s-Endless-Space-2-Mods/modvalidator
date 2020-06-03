package de.idrinth.endlessspace2.modvalidator;

import java.util.Objects;

public class Pair {

    public final String from;
    public final String to;

    public Pair(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int hashCode() {
        int hash = 73 * 3 + Objects.hashCode(this.from);
        return 73 * hash + Objects.hashCode(this.to);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        return Objects.equals(this.from, other.from) && Objects.equals(this.to, other.to);
    }
}
