package com.aiman.cfggenerator;

import soot.*;
import soot.jimple.JimpleBody;
import soot.options.Options;
import soot.toolkits.graph.ClassicCompleteUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.util.dot.DotGraph;

public class CFGGenerator {

    public static String sourceDirectory = "/Users/aimanfatima/Work/ucdavis/wq24/ecs289c/sootup-cfg-on-bugswarm-artifacts/target/classes/com/aiman/examples";
    public static String clsName = "SimpleCalculator";
    public static String methodName = "calculator";

    public static void setupSoot() {
        G.reset();
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(sourceDirectory);
        SootClass sc = Scene.v().loadClassAndSupport(clsName);
        sc.setApplicationClass();
        Scene.v().loadNecessaryClasses();
    }

    public static void main(String[] args) {
        setupSoot();

        // Retrieve example class body
        SootClass mainClass = Scene.v().getSootClass(clsName);
        SootMethod sm = mainClass.getMethodByName(methodName);
        JimpleBody body = (JimpleBody) sm.retrieveActiveBody();

        UnitGraph ug = new ClassicCompleteUnitGraph(sm.getActiveBody());
        generateDotGraphFromUnitGraph(sm, ug, "output/dotfiles/" + sm.getName() + ".dot");
    }

    public static void generateDotGraphFromUnitGraph(SootMethod method, UnitGraph graph, String outputFileName) {
        DotGraph dot = new DotGraph(outputFileName);
        dot.setGraphLabel(method.getName());

        // Create a node for each unit in the graph
        for (Unit unit : graph) {
            String unitId = unit.toString();
            dot.drawNode(unitId).setLabel(unitId.substring(0, Math.min(unitId.length(), 50))); // Truncate long labels
//            dot.drawNode(unitId).setLabel(unitId);
        }

        // Create an edge for each successor relationship
        for (Unit unit : graph) {
            String srcId = unit.toString();
            for (Unit succ : graph.getSuccsOf(unit)) {
                String dstId = succ.toString();
                dot.drawEdge(srcId, dstId);
            }
        }

        // Save the DotGraph to a file
        dot.plot(outputFileName);
    }
}
