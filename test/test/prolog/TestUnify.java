package test.prolog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import prolog.Binding;
import prolog.Constant;
import prolog.Term;
import prolog.Variable;

public class TestUnify {

    Constant friend = Constant.of("friend"),
        bill = Constant.of("bill"),
        george = Constant.of("george"),
        kate = Constant.of("kate"),
        merry = Constant.of("merry");

    Variable X = Variable.of("X"),
        Y = Variable.of("Y");

    List<Term> terms = Arrays.asList(
        Term.of(friend, bill, george),
        Term.of(friend, bill, kate),
        Term.of(friend, bill, merry),
        Term.of(friend, george, bill),
        Term.of(friend, george, kate),
        Term.of(friend, kate, merry));

    @Test
    public void testAll() {
        Term goal = Term.of(friend, X, Y);
        List<Term> matches = new ArrayList<>();
        for (Term t : terms) {
            Binding b = new Binding();
            if (goal.unify(t, b))
                matches.add(t);
        }
        assertEquals(terms, matches);
    }

    @Test
    public void testBill() {
        Term goal = Term.of(friend, bill, Y);
        List<Term> matches = new ArrayList<>();
        for (Term t : terms) {
            Binding b = new Binding();
            if (goal.unify(t, b))
                matches.add(t);
        }
        assertEquals(3, matches.size());
        for (Term t : matches)
            assertEquals(bill, t.get(1));
    }

    @Test
    public void testVariables() {
        Term goal = Term.of(friend, X, bill);
        Term term = Term.of(friend, george, Y);
        Binding b = new Binding();
        assertTrue(goal.unify(term, b));
        assertEquals(george, b.get(X));
        assertEquals(bill, b.get(Y));
        System.out.println(b);
    }

    @Test
    public void testVariableTerm() {
        Variable goal = X;
        Term term = Term.of(friend, george, bill);
        Binding b = new Binding();
        assertTrue(goal.unify(term, b));
        assertEquals(term, b.get(X));
        System.out.println(b);
    }

}
