package com.flowforge.core.dag;
import java.util.*;

public class DAG {
    
    private final Map<String, DAGNode> nodes; //registered nodes
    private final Map<String, Set<String>> adjacencyList; //outgoing edges

    public DAG() {
        this.nodes = new LinkedHashMap<>(); //linkedhashmap for insertion order preservation
        //makes graph deterministic 
        this.adjacencyList = new LinkedHashMap<>();
    }

    public void addNode(DAGNode node) {

        Objects.requireNonNull(node, "Node cannot be null");
        String nodeId = node.getId();

        if(nodes.containsKey(nodeId)){
            throw new IllegalArgumentException("Node exists: " + nodeId);
        }
        nodes.put(nodeId, node);
        adjacencyList.put(nodeId, new LinkedHashSet<>());
    }

    public void addEdge(String sourceId, String destinationId) {

        if(!nodes.containsKey(sourceId)) {
            throw new IllegalArgumentException("source node doesnot exist: "+ sourceId);
        }

        if(!nodes.containsKey(destinationId)) {
            throw new IllegalArgumentException("destination node does not exist: " + destinationId);
        }

        if(sourceId.equals(destinationId)) {
            throw new IllegalArgumentException("no self edge allowed" );
        }

        adjacencyList.get(sourceId).add(destinationId);
    }

    public DAGNode getNode(String nodeId) {
        return nodes.get(nodeId);
    }

    public Set<String> getNeighbors(String nodeId) {

        if(!adjacencyList.containsKey(nodeId)) {
            throw new IllegalArgumentException("node doesnot exist: " + nodeId);
        }

        return Collections.unmodifiableSet(adjacencyList.get(nodeId)); // protects graphs structure from external modification
    }

    public Set<String> getNodeIds() {
        return Collections.unmodifiableSet(nodes.keySet());
    }

    public int getNodeCount() {
        return nodes.size();
    }

    public int getEdgeCount() {

        int edgeCount = 0;
        for(Set<String> neighbors : adjacencyList.values()) {
            edgeCount += neighbors.size();
        }
        return edgeCount;
    }
}
