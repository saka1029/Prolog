package prolog;

public interface Unifiable extends Expression {

    boolean unify(Unifiable u, Binding b);

}
