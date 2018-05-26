package graphPackae;
import java.util.*;


public class GraphVisit<T extends Comparable<T>> {
	
	
	public void BellmanFord(Graph<T> G, boolean tuttoalvolo) {
		int i=0;
		TreeMap<Node<T>, Integer> index=new TreeMap<Node<T>, Integer>();
		for(Node<T> n : G.V()) {
			index.put(n, i);
			i=i+1;
		}
		i=0;
		
		Integer[] distances= new Integer[G.V().size()];
		for (Node<T> n : G.V())
			distances[index.get(n)]=null;
		
		
		Node<T>[] parents= new Node[G.V().size()];
		for (Node<T> n : G.V())
			parents[index.get(n)]=null;
		
		Node<T> radice=G.getRoot(0);
		
		distances[index.get(radice)]=0;
		
		LinkedList<Node<T>> q=new LinkedList<Node<T>>();
		q.add(radice);
		
		int counter=0;
		
		
		while (!q.isEmpty()) {
			Node<T> n=q.pop();
			//if (!tuttoalvolo)
			// GraphVisualize(G, q, n, m, distances, parents);
				for(Node<T> m : G.adj(n)){
					//if (!tuttoalvolo)
					// GraphVisualize(G, q, n, m, distances, parents);
					if (distances[index.get(m)]==null || distances[index.get(n)]+G.w(n, m)<distances[index.get(m)]) {			
						if (!q.contains(m)) {
							q.addLast(m);
							//if (!tuttoalvolo)
							// GraphVisualize(G, q, n, m, distances, parents);
							}
						parents[index.get(m)]=n;
						//if (!tuttoalvolo)
						// GraphVisualize(G, q, n, m, distances, parents);
						distances[index.get(m)]=distances[index.get(n)]+G.w(n, m);
						//if (!tuttoalvolo)
						// GraphVisualize(G, q, n, m, distances, parents);
						
					}	
				}
				counter++;
				if (counter==G.V().size()*G.V().size())
					break;
		}
		// GraphVisualize(G, q, n, m, distances, parents);
	}
}
