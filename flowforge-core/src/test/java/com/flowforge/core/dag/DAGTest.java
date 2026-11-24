package com.flowforge.core.dag;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DAGTest {

    @Test 
    void shouldAddNodes() {
        DAG dag = new DAG();

        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));

        assertEquals(2, dag.getNodeCount());
    }

    @Test 
    void shouldAddDirectedEdges() {
        DAG dag = new DAG();

        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));

        dag.addEdge("A", "B");

        assertTrue(dag.getNeighbors("A").contains("B"));
        assertFalse(dag.getNeighbors("B").contains("A"));
    }

    @Test 
    void shouldRejectDuplicateNodes() {
        DAG dag = new DAG();

        dag.addNode(new DAGNode("A"));

        assertThrows(IllegalArgumentException.class, () -> dag.addNode(new DAGNode("A")));
    }

    @Test 
    void shouldRejectMissingSource() {
        DAG dag = new DAG();

        dag.addNode(new DAGNode("A"));
        assertThrows(IllegalArgumentException.class, () -> dag.addEdge("B", "A"));
    }

    @Test 
    void shouldRejectSelfLoop() {
        DAG dag = new DAG();
        dag.addNode(new DAGNode("A"));
        assertThrows(IllegalArgumentException.class, () -> dag.addEdge("A", "A"));
    }

    @Test 
    void shouldNotAddDulicateEdges() {

        DAG dag = new DAG();

        dag.addNode(new DAGNode("A"));
        dag.addNode(new DAGNode("B"));
        dag.addEdge("A", "B");

        assertEquals(1, dag.getEdgeCount());
    }
}
