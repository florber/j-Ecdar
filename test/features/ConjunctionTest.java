package features;

import logic.Conjunction;
import logic.Refinement;
import logic.SimpleTransitionSystem;
import logic.TransitionSystem;
import models.Automaton;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.JSONParser;

import static org.junit.Assert.assertTrue;

public class ConjunctionTest {
    private static TransitionSystem t1, t1Copy, t2, t2Copy, t3, t3Copy, t4, t4Copy, t5, t5Copy, t6, t7, t8, t9, t10, t11, t12;

    @BeforeClass
    public static void setUpBeforeClass() {
        String base = "./samples/json/Conjunction/";
        String[] components = new String[]{"GlobalDeclarations.json",
                "Components/Test1.json",
                "Components/Test2.json",
                "Components/Test3.json",
                "Components/Test4.json",
                "Components/Test5.json",
                "Components/Test6.json",
                "Components/Test7.json",
                "Components/Test8.json",
                "Components/Test9.json",
                "Components/Test10.json",
                "Components/Test11.json",
                "Components/Test12.json"};
        Automaton[] machines = JSONParser.parse(base, components, true);

        t1 = new SimpleTransitionSystem(machines[0]);
        t1Copy = new SimpleTransitionSystem(new Automaton(machines[0]));
        t2 = new SimpleTransitionSystem(machines[1]);
        t2Copy = new SimpleTransitionSystem(new Automaton(machines[1]));
        t3 = new SimpleTransitionSystem(machines[2]);
        t3Copy = new SimpleTransitionSystem(new Automaton(machines[2]));
        t4 = new SimpleTransitionSystem(machines[3]);
        t4Copy = new SimpleTransitionSystem(new Automaton(machines[3]));
        t5 = new SimpleTransitionSystem(machines[4]);
        t5Copy = new SimpleTransitionSystem(new Automaton(machines[4]));
        t6 = new SimpleTransitionSystem(machines[5]);
        t7 = new SimpleTransitionSystem(machines[6]);
        t8 = new SimpleTransitionSystem(machines[7]);
        t9 = new SimpleTransitionSystem(machines[8]);
        t10 = new SimpleTransitionSystem(machines[9]);
        t11 = new SimpleTransitionSystem(machines[10]);
        t12 = new SimpleTransitionSystem(machines[11]);
    }

    @Test
    public void T1RefinesSelf() {
        assertTrue(new Refinement(t1, t1Copy).check());
    }

    @Test
    public void T2RefinesSelf() {
        assertTrue(new Refinement(t2, t2Copy).check());
    }

    @Test
    public void T3RefinesSelf() {
        assertTrue(new Refinement(t3, t3Copy).check());
    }

    @Test
    public void T4RefinesSelf() {
        assertTrue(new Refinement(t4, t4Copy).check());
    }

    @Test
    public void T5RefinesSelf() {
        assertTrue(new Refinement(t5, t5Copy).check());
    }

    @Test
    public void T1ConjT2RefinesT3() {
        assertTrue(new Refinement(new Conjunction(new TransitionSystem[]{t1, t2}), t3).check());
    }

    @Test
    public void T2ConjT3RefinesT1() {
        assertTrue(new Refinement(new Conjunction(new TransitionSystem[]{t2, t3}), t1).check());
    }

    @Test
    public void T1ConjT3RefinesT2() {
        assertTrue(new Refinement(new Conjunction(new TransitionSystem[]{t1, t3}), t2).check());
    }

    @Test
    public void T1ConjT2ConjT4RefinesT5() {
        assertTrue(new Refinement(new Conjunction(new TransitionSystem[]{t1, t2, t4}), t5).check());
    }

    @Test
    public void T3ConjT4RefinesT5() {
        assertTrue(new Refinement(new Conjunction(new TransitionSystem[]{t3, t4}), t5).check());
    }

    @Test
    public void test1NestedConjRefinesT5() {
        TransitionSystem ts1 = new Conjunction(new TransitionSystem[]{t1, t2});
        TransitionSystem ts2 = new Conjunction(new TransitionSystem[]{ts1, t4});

        assertTrue(new Refinement(ts2, t5).check());
    }

    @Test
    public void T6ConjT7RefinesT8() {
        assertTrue(new Refinement(new Conjunction(new TransitionSystem[]{t6, t7}), t8).check());
    }

    @Test
    public void test1NestedConjRefinesT12() {
        TransitionSystem ts1 = new Conjunction(new TransitionSystem[]{t9, t10});
        TransitionSystem ts2 = new Conjunction(new TransitionSystem[]{ts1, t11});

        assertTrue(new Refinement(ts2, t12).check());
    }
}
