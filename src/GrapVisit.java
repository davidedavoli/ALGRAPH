import java.util.*;


public class GrapVisit<T extends Comparable<T>> {
	
	
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
		
		
		
		while (!q.isEmpty()) {
			Node<T> n=q.pop();
				for(Node<T> m : G.adj(n)){
					if (distances[index.get(m)]==null || distances[index.get(n)]+G.w(m, n)<distances[index.get(m)]) {
						if (!q.contains(m)) {
							q.add(m);
							}
						parents[index.get(m)]=n;
						distances[index.get(m)]=distances[index.get(n)]+G.w(m, n);
					}	
				}
		}
		
	}
}
