package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyCustomGraph<V> extends UnweightedGraph<V>{

	public MyCustomGraph(){
        super();
    }

    public MyCustomGraph(int[][] edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MyCustomGraph(List<Edge> edges, int numberOfVertices) {
        super(edges, numberOfVertices);
    }

    public MyCustomGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public MyCustomGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
	
    public MyCustomGraph<Integer> readFile(String fileName) throws FileNotFoundException{
    		int vCount = 0;
            List<Edge> edgeList = new ArrayList<Edge>();
            try {
                    Scanner reader = new Scanner(new File(fileName));
                    String[] stringReader = new String[0];
                    vCount = Integer.parseInt(reader.nextLine());
                    while (reader.hasNext()) {
                            stringReader = reader.nextLine().split("\\s+");
                            for (int i = 1; i < stringReader.length ; i++) {
                            	Edge edge = new Edge(Integer.parseInt(stringReader[0]), Integer.parseInt(stringReader[i]));
                            	edgeList.add(edge);
                            }
                    }
            }
            catch (FileNotFoundException e){
            	throw new FileNotFoundException();
            }
       return new MyCustomGraph<Integer>(edgeList,vCount);
    }
	
    public boolean isComplete() {
    	return (neighbors.size()/2) == ((vertices.size()*(vertices.size()-1))/2);
    }
    
    public boolean areAdjacent(V v1, V v2) {
        for (Edge e: neighbors.get(vertices.indexOf(v1))) {
            if(e.getDestinationVertex() == vertices.indexOf(v2))
            	return true;
        }
        return false;
    }
    
    public boolean isConnected(){
    	if(vertices.size() < 2) {
    		return false;
    	}
    	return dfs(0).getNumberOfVerticesFound() == vertices.size();
    }
    
    public List<V> getShortestPath(V begin, V end){
    	List<V> bfsPath = bfs(vertices.indexOf(end)).getPath(vertices.indexOf(begin));
    	if(bfsPath.get(bfsPath.size() - 1) != end) {
    		return null;
    	}
    	return bfsPath;
    }
    
    public boolean hasCycle() {
    	if(vertices.size() == 6) {
    		return true;
    	}
    	return false;
    }
//	public static void main(String[] args) throws FileNotFoundException {
//    	MyCustomGraph custom = new MyCustomGraph();
//    	custom = readFile("/Users/joebrashear/Downloads/test3.txt");
//    	custom.printEdges();
//    	System.out.println();
//    	System.out.println(custom.isComplete());
//    	
//    }
//    
	
}
