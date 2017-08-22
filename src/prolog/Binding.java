package prolog;

import java.util.HashMap;
import java.util.Map;

public class Binding {

    private final Map<Variable, Unifiable> binding = new HashMap<>();

    public void put(Variable v, Unifiable u) {
        binding.put(v, u);
    }

    public boolean contains(Variable v) {
        return binding.containsKey(v);
    }

    public Unifiable get(Variable v) {
        return binding.get(v);
    }

    @Override
    public String toString() {
        return "Binding:" + binding;
    }

}
