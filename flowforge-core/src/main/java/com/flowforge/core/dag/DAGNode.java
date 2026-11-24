package com.flowforge.core.dag;
import java.util.*;

//preventing extending this class and changing behaviour, thats why final

public final class DAGNode {
    
    private final String id;

    public DAGNode(String id){
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("id cannot be null or blank");
        }
        this.id = id;
    }

    public String getId(){
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof DAGNode other)) return false;
        return id.equals(other.id);
    }

    @Override 
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override 
    public String toString() {
        return "DAGNode{id='" + id + "'}";
    }
}
