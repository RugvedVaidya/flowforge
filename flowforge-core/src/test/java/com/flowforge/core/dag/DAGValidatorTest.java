package com.flowforge.core.dag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DAGValidatorTest {
    
    private final DAGValidator validator = new DAGValidator();

    @Test 
    void shouldAcceptValidDAG() {

        DAG dag = new DAG();
        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));
        dag.addNode(new DAGNode("C"));
        dag.addNode(new DAGNode("D"));

        dag.addEdge("A", "B");
        dag.addEdge("B", "C");
        dag.addEdge("A", "D");

        assertFalse(validator.hasCycle(dag));
    }

    @Test 
    void shouldDetectSimpleCycle() {
        
        DAG dag = new DAG();
        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));
        dag.addNode(new DAGNode("C"));
        dag.addNode(new DAGNode("D"));

        dag.addEdge("A", "B");
        dag.addEdge("B", "C");
        dag.addEdge("A", "D");
        dag.addEdge("B", "A");

        assertTrue(validator.hasCycle(dag));
    }

    @Test 
    void shouldDetectCycleWithDisconnectedComponents() {
        DAG dag = new DAG();
        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));
        dag.addNode(new DAGNode("C"));
        dag.addNode(new DAGNode("D"));

        dag.addEdge("A", "B");
        dag.addEdge("B", "C");
        dag.addEdge("B", "A");

        assertTrue(validator.hasCycle(dag));
    }

    @Test 
    void shouldAcceptGraphWithDisconnectedNodes() {
        
        DAG dag = new DAG();
        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));
        dag.addNode(new DAGNode("C"));
        dag.addNode(new DAGNode("D"));

        dag.addEdge("A", "B");
        dag.addEdge("B", "C");

        assertFalse(validator.hasCycle(dag));
    }

    @Test 
    void shouldAcceptEmptyDAG() {
        DAG dag = new DAG();
        assertFalse(validator.hasCycle(dag));
    }

    @Test 
    void shouldRejectInvalidDAGDuringValidation() {
        DAG dag = new DAG();
        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));
        dag.addEdge("A", "B");
        dag.addEdge("B", "A");

        assertThrows(IllegalArgumentException.class, () -> validator.validate(dag));
    }
}
