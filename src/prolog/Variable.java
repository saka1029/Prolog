package prolog;

public class Variable implements Unifiable {

    private static int seed = 0;
    final int id;
    final String name;

    private Variable(String name) {
        this.name = name;
        id = seed++;
    }

    public static Variable of(String name) {
        return new Variable(name);
    }

    @Override
    public String toString() {
        return name + "_" + id;
    }

    @Override
    public boolean unify(Unifiable u, Binding b) {
        if (u.equals(this))
            return true;
        if (b.contains(this))
            return b.get(this).unify(u, b);
        b.put(this, u);
        return true;
    }

}
