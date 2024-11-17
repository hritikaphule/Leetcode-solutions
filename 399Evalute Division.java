import java.util.*;

public class Solution {
    // Function to calculate the result of queries
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Map to store the graph
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        // Build the graph
        for (int i = 0; i < equations.size(); i++) {
            String A = equations.get(i).get(0);
            String B = equations.get(i).get(1);
            double value = values[i];
            
            graph.putIfAbsent(A, new HashMap<>());
            graph.putIfAbsent(B, new HashMap<>());
            graph.get(A).put(B, value);
            graph.get(B).put(A, 1.0 / value);
        }
        
        // Process each query
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String C = queries.get(i).get(0);
            String D = queries.get(i).get(1);
            results[i] = dfs(C, D, graph, new HashSet<>());
        }
        
        return results;
    }
    
    // DFS to find the result of C / D
    private double dfs(String current, String target, Map<String, Map<String, Double>> graph, Set<String> visited) {
        // If the current node or target node is not in the graph
        if (!graph.containsKey(current) || !graph.containsKey(target)) {
            return -1.0;
        }
        
        // If the current node is the target node
        if (current.equals(target)) {
            return 1.0;
        }
        
        visited.add(current);
        
        // Visit neighbors
        for (Map.Entry<String, Double> neighbor : graph.get(current).entrySet()) {
            String next = neighbor.getKey();
            double value = neighbor.getValue();
            
            if (!visited.contains(next)) {
                double product = dfs(next, target, graph, visited);
                if (product != -1.0) {
                    return value * product;
                }
            }
        }
        
        return -1.0;
    }
}
