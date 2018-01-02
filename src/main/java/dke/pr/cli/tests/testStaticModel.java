package dke.pr.cli.tests;

import java.io.IOException;
import java.util.Arrays;

import dke.pr.cli.CBRInterface;

public class testStaticModel {
	public static void main(String[] args) throws IOException {
		CBRInterface fl = new CBRInterface("C:/TEMP/dke/flora2/CBRM/ctxModelAIM.flr",
				"C:/TEMP/dke/flora2/CBRM/bc.flr", "AIMCtx", "SemNOTAMCase");

		fl.setDebug(false);

		System.out.println("Contexts: " + fl.getCtxs());

		System.out.print("\nCtx Hier: ");
		for (String[] strings : fl.getCtxHierarchy()) {
			System.out.print(Arrays.toString(strings) + ", ");
		}
		System.out
				.println("\n\nCtx File: "
						+ fl.getCtxFile("allInterests_allFlightPhases_allEventScenarios"));

		System.out.println("\nParams:   "+fl.getParameters());
		System.out.println("\nParam Va: "+fl.getParameterValues());
		System.out.print("\nValue hi: ");
		for (String[] strings : fl.getParameterValuesHiearchy("Interest")) {
			System.out.print(Arrays.toString(strings) + ", ");
		}

		System.out.println("\n\nRel Ctxs: " + fl.detRelevantCtxs("bc1"));
		System.out.println("\nCaseSpec: "+fl.detCaseSpecificCtx("bc1", "m"));

		System.out.println("\nnew BC:   "+fl.newBusinessCase("${bc2:SemNOTAMCase[interestSpec->iSpec2,notam->n1]@bc}"));
		System.out.println("\nRel Ctxs: " + fl.detRelevantCtxs("bc2"));
		System.out.println("\nCaseSpec: "+fl.detCaseSpecificCtx("bc2", "m"));

		fl.close();
	}
}
