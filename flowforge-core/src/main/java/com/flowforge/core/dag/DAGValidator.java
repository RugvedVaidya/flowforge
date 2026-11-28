package com.flowforge.core.dag;
import java.util.*;

public class DAGValidator { //kahns algorithm
    
    public boolean hasCycle(DAG dag) {
        Objects.requireNonNull(dag, "dag cant be null");
        Map<String, Integer> inDegree = new HashMap<>();

        for(String nodeId : dag.getNodeIds()) {
            inDegree.put(nodeId, 0);
        }

        for(String nodeId : dag.getNodeIds()) {
            for(String neighborId : dag.getNeighbors(nodeId)){
                inDegree.put(neighborId, inDegree.get(neighborId) + 1);
            }
        }

        Queue<String> q = new ArrayDeque<>();
        
        for(String nodeId : inDegree.keySet()) {
            if(inDegree.get(nodeId) == 0) {
                q.offer(nodeId);
            }
        }

        int processedNodes = 0;

        while(!q.isEmpty()) {

            String current = q.poll();
            processedNodes++;

            for(String neighbor : dag.getNeighbors(current)) {
                int newInDeg = inDegree.get(neighbor) - 1;
                inDegree.put(neighbor, newInDeg);

                if(newInDeg == 0) {
                    q.offer(neighbor);
                }
            }
        }
        return processedNodes != dag.getNodeIds().size();
    }

    public void validate(DAG dag) {
        if(hasCycle(dag)) {
            throw new IllegalArgumentException("DAG has a cycle");
        }
    }
}
