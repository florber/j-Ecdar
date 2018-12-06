package logic;

import models.Component;
import org.junit.*;
import parser.Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UniversityTest {

		private static Component adm, machine, researcher, spec, machine3;

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
				String fileName = "src/" + System.mapLibraryName("DBM");
				File lib = new File(fileName);
				System.load(lib.getAbsolutePath());
				List<Component> machines = Parser.parse();
				adm = machines.get(0);
				machine = machines.get(1);
				researcher = machines.get(2);
				spec = machines.get(3);
				machine3 = machines.get(4);
		}

		@Test
		public void testAdmRefinesAdm() {
				Refinement ref = selfRefinesSelf(adm);
				assertTrue(ref.check());
		}

		@Test
		public void testAdmNotRefinesMachine() {
				Refinement ref = simpleRefinesSimple(adm, machine);
				assertFalse(ref.check());
		}

		@Test
		public void testAdmNotRefinesResearcher() {
				Refinement ref = simpleRefinesSimple(adm, researcher);
				assertFalse(ref.check());
		}

		@Test
		public void testAdmNotRefineSpec() {
				Refinement ref = simpleRefinesSimple(adm, spec);
				assertFalse(ref.check());
		}

		@Test
		public void testAdmNotRefinesMachine3() {
				Refinement ref = simpleRefinesSimple(adm, machine3);
				assertFalse(ref.check());
		}

		@Test
		public void testMachineRefinesMachine() {
				Refinement ref = selfRefinesSelf(machine);
				assertTrue(ref.check());
		}

		@Test
		public void testMachineNotRefinesAdm() {
				Refinement ref = simpleRefinesSimple(machine, adm);
				assertFalse(ref.check());
		}

		@Test
		public void testMachineNotRefinesResearcher() {
				Refinement ref = simpleRefinesSimple(machine, researcher);
				assertFalse(ref.check());
		}

		@Test
		public void testMachineNotRefinesSpec() {
				Refinement ref = simpleRefinesSimple(machine, spec);
				assertFalse(ref.check());
		}

		@Test
		public void testMachineNotRefinesMachine3() {
				Refinement ref = simpleRefinesSimple(machine, machine3);
				assertFalse(ref.check());
		}

		@Test
		public void testResRefinesRes() {
				Refinement ref = selfRefinesSelf(researcher);
				assertTrue(ref.check());
		}

		@Test
		public void testResNotRefinesAdm() {
				Refinement ref = simpleRefinesSimple(researcher, adm);
				assertFalse(ref.check());
		}

		@Test
		public void testResNotRefinesMachine() {
				Refinement ref = simpleRefinesSimple(researcher, machine);
				assertFalse(ref.check());
		}

		@Test
		public void testResNotRefinesSpec() {
				Refinement ref = simpleRefinesSimple(researcher, spec);
				assertFalse(ref.check());
		}

		@Test
		public void testResNotRefinesMachine3() {
				Refinement ref = simpleRefinesSimple(researcher, machine3);
				assertFalse(ref.check());
		}

		@Test
		public void testSpecRefinesSpec() {
				Refinement ref = selfRefinesSelf(spec);
				assertTrue(ref.check());
		}

		@Test
		public void testSpecNotRefinesAdm() {
				Refinement ref = simpleRefinesSimple(spec, adm);
				assertFalse(ref.check());
		}

		@Test
		public void testSpecNotRefinesMachine() {
				Refinement ref = simpleRefinesSimple(spec, machine);
				assertFalse(ref.check());
		}

		@Test
		public void testSpecNotRefinesResearcher() {
				Refinement ref = simpleRefinesSimple(spec, researcher);
				assertFalse(ref.check());
		}

		@Test
		public void testSpecNotRefinesMachine3() {
				Refinement ref = simpleRefinesSimple(spec, machine3);
				assertFalse(ref.check());
		}

		@Test
		public void testMachine3RefinesMachine3() {
				Refinement ref = selfRefinesSelf(machine3);
				assertTrue(ref.check());
		}

		@Test
		public void testMachine3RefinesMachine() {
				Refinement ref = simpleRefinesSimple(machine3, machine);
				assertTrue(ref.check());
		}

		@Test
		public void testMachine3NotRefinesAdm() {
				Refinement ref = simpleRefinesSimple(machine3, adm);
				assertFalse(ref.check());
		}

		@Test
		public void testMachine3NotRefinesResearcher() {
				Refinement ref = simpleRefinesSimple(machine3, researcher);
				assertFalse(ref.check());
		}

		@Test
		public void testMachine3NotRefinesSpec() {
				Refinement ref = simpleRefinesSimple(machine3, spec);
				assertFalse(ref.check());
		}

		@Test
		public void testCompRefinesSpec() {
				TransitionSystem ts1 = new ComposedTransitionSystem(new ArrayList<>(Arrays.asList(adm, machine, researcher)));
				TransitionSystem ts2 = new SimpleTransitionSystem(spec);

				Refinement ref = new Refinement(ts1, ts2);
				assertTrue(ref.check());
		}

		@Test
		public void testCompRefinesSelf() {
				TransitionSystem ts1 = new ComposedTransitionSystem(new ArrayList<>(Arrays.asList(adm, machine, researcher)));
				TransitionSystem ts2 = new ComposedTransitionSystem(new ArrayList<>(Arrays.asList(machine, researcher, adm)));

				Refinement ref = new Refinement(ts1, ts2);
				assertTrue(ref.check());
		}


		// helper functions
		private Refinement selfRefinesSelf(Component component) {
				return simpleRefinesSimple(component, component);
		}

		private Refinement simpleRefinesSimple(Component component1, Component component2) {
				TransitionSystem ts1 = new SimpleTransitionSystem(component1);
				TransitionSystem ts2 = new SimpleTransitionSystem(component2);
				return new Refinement(ts1, ts2);
		}
		//
}