"" This calculates the sum of distances in a tree ""

import java.util.*;
import java.io.*;

class TreeDistanceSum {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		int val = input.nextInt();
		int[][] val_lst = new int[n - 1][2];
		for (int i = 0; i < val_lst.length; i++) {
			val_lst[i][0] = input.nextInt();
			val_lst[i][1] = input.nextInt();
		}
		TreeDistanceSum sum_calc = new TreeDistanceSum();
		int[] output = sum_calc.TreeDistanceSum(val, val_lst);
		System.out.println(Arrays.toString(output));
	}

	int[] total_nodes;
	int[] output;
	Map<Integer, List<Integer>> graph_structure;
	boolean[] did_visit;
  int counter = 0;
  public int[] TreeDistanceSum(int val, int[][] val_lst) {
        did_visit = new boolean[val];
        total_nodes = new int[val];
        output = new int[val];
        if (val == 1) {
            return output; 
        }
        graph_structure = new HashMap<>();
        for (int[] pter : val_lst) {
        	if (!graph_structure.containsKey(pter[0])) {
        		graph_structure.put(pter[0], new ArrayList<>());
        	}
        	if (!graph_structure.containsKey(pter[1])) {
        		graph_structure.put(pter[1], new ArrayList<>());
        	}
        	//Since it is undirected
        	graph_structure.get(pter[0]).add(pter[1]);
        	graph_structure.get(pter[1]).add(pter[0]);
        }        
        done1(0, 0);                
        output[0] = counter;
        Arrays.fill(did_visit, false);
        did_visit[0] = true;     
        for (Integer child : graph_structure.get(0)) {
        	calcDistance(child, 0, val);	
        }

        
        return output;
    }

    public void calcDistance(int current, int root, int val) {    	
    	if (did_visit[current]) {
    		return;
    	}
    	did_visit[current] = true;
    	if (is_leaf(current)) {    		
    		int distance_root = output[root];
    		int distance_curr = output[root] - total_nodes[current] + val - total_nodes[current];
    		output[current] = distance_curr;
    	}
    	int distance_curr = output[root] - total_nodes[current] + val - total_nodes[current];
		output[current] = distance_curr;
    	List<Integer> neighbors = graph_structure.get(current);
    	if (neighbors.size() > 0) {    		
    		for (Integer neighbor : neighbors) {
    			calcDistance(neighbor, current, val);
    		}
    	}
    }

    public int done1(int root, int depth) {
    	
    	if (did_visit[root]) {
    		return 0;
    	}
    	
    	did_visit[root] = true;
    	
    	if (is_leaf(root)) {
    		counter += depth;
    		total_nodes[root] = 1;
    		return 1;
    	}

    	int child_count = 0;
    	List<Integer> neighbor_nodes = graph_structure.get(root);
    	if (neighbor_nodes.size() > 0) {
    		for (Integer neighbor_node : neighbor_nodes) {
	    		child_count += done1(neighbor_node, depth + 1);
	    	}	
    	}
    	counter += depth;
    	total_nodes[root] = child_count + 1;
    	return child_count + 1;
    }

    public boolean is_leaf(int node) {
    	List<Integer> neighbor_nodes = graph_structure.get(node);
    	if (neighbor_nodes.size() == 0) {
    		return true;
    	}
    	for (Integer neighbor_node : neighbor_nodes) {
    		if (!did_visit[neighbor_node]) {
    			return false;
    		}
    	}
    	return true;
    }
}
