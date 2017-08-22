package prolog;

import java.util.ArrayList;
import java.util.List;

public class Term implements Unifiable {

    final List<Unifiable> terms = new ArrayList<>();

    private Term(Constant predicate, Unifiable... args) {
        terms.add(predicate);
        for (Unifiable u : args)
            terms.add(u);
    }

    public static Term of(Constant predicate, Unifiable... args) {
        return new Term(predicate, args);
    }

    public Unifiable get(int index) {
        return terms.get(index);
    }

    public int size() {
        return terms.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(terms.get(0)).append("(");
        String sep = "";
        for (Unifiable u : terms) {
            sb.append(sep).append(u);
            sep = ", ";
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public boolean unify(Unifiable u, Binding b) {
        if (u instanceof Term) {
            Term t = (Term)u;
            if (t.size() != size())
                return false;
            for (int i = 0; i < size(); ++i)
                if (!get(i).unify(t.get(i), b))
                    return false;
            return true;
        }
        if (u instanceof Variable)
            return u.unify(this, b);
        // if (u instanceof Constant)
        return false;
    }

}
