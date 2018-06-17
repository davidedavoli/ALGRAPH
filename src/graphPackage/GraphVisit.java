package graphPackage;
import java.util.*;


public class GraphVisit<T extends Comparable<T>> {
	
	
	public void BellmanFord(Graph<T> G) {
		int i=0;
		//boolean tuttoalvolo;
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
			//tuttoalvolo=GraphVisualize(G, q, n, m, distances, parents))
				for(Node<T> m : G.adj(n)){
					//if (!tuttoalvolo)
					// tuttoalvolo=GraphVisualize(G, q, n, m, distances, parents);
					if (distances[index.get(m)]==null || distances[index.get(n)]+G.w(n, m)<distances[index.get(m)]) {			
						if (!q.contains(m)) {
							q.addLast(m);
							//if (!tuttoalvolo)
							// tuttoalvolo=GraphVisualize(G, q, n, m, distances, parents);
							}
						parents[index.get(m)]=n;
						//if (!tuttoalvolo)
						// tuttoalvolo=GraphVisualize(G, q, n, m, distances, parents);
						distances[index.get(m)]=distances[index.get(n)]+G.w(n, m);
						//if (!tuttoalvolo)
						// tuttoalvolo=GraphVisualize(G, q, n, m, distances, parents);
						
					}	
				}
				counter++;
				if (counter==G.V().size()*G.V().size())
					break;
		}
		// GraphVisualize(G, q, n, m, distances, parents);
	}
	
	public List<Node<T>> detectNegativeCycles(Graph<T> G, Node<T> radice) {
		List<Node<T>> cycleList=new LinkedList<Node<T>>();
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
		
		//Node<T> radice=G.getRoot(0);
		
		distances[index.get(radice)]=0;
		
		for(int i1=0; i1<G.V().size()-2;i1++) {
			for(Node<T> n: G.V()) {
				for (Node<T> m: G.adj(n)) {
					if (distances[index.get(n)]!=null && (distances[index.get(m)]==null || distances[index.get(n)]+G.w(n, m)<distances[index.get(m)])) {			
						parents[index.get(m)]=n;
						distances[index.get(m)]=distances[index.get(n)]+G.w(n, m);
					}	
				}
		}
	}
		
		Node<T> errorNode=null;
			for(Node<T> n: G.V()) {
				for (Node<T> m: G.adj(n)) {
				if (distances[index.get(m)]!=null && distances[index.get(n)]!=null && distances[index.get(m)]>distances[index.get(n)]+G.w(n, m)) {
					errorNode=m;
					cycleList.add(errorNode);
					}
				if (errorNode!=null)
					break;
				}
			if (errorNode!=null)
				break;
			}
		Node<T> q=null;
		if (errorNode!=null) {
			q=parents[index.get(errorNode)];
			while (!cycleList.contains(q)) {
				cycleList.add(q);
				q=parents[index.get(q)];
			}
			cycleList.add(errorNode);
		}
		return cycleList;
	}
}
