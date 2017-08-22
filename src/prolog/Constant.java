package prolog;

import java.util.HashMap;
import java.util.Map;

public class Constant implements Unifiable {

    private static final Map<String, Constant> map = new HashMap<>();

    private final String name;

    private Constant(String name) {
        this.name = name;
    }

    public static Constant of(String name) {
        return map.computeIfAbsent(name, n -> new Constant(n));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean unify(Unifiable u, Binding b) {
        if (u.equals(this))
            return true;
        if (u instanceof Variable)
            return u.unify(this, b);
        // if (u instanceof Term)
        return false;
    }

}
