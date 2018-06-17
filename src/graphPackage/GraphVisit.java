package graphPackage;
import java.awt.Color;
import java.util.*;

import algraphPackage.Arrow;
import javafx.scene.control.Button;


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
	
	
	public Integer GraphVisualize(Button next, Button end, VisualGraph<T> visualGraph) {
		Integer b = new Integer(-1);
		visualGraph.getBlackCircle(visualGraph.getSelectedNode()).getCircle().setFill(Color.GREEN); //colora il primo
		end.setOnMouseClicked(event -> {
			b = 1;
			//considero q come la lista contenenti tutti i nodi per bellman
			for (Node <T> node : q) {
				visualGraph.getBlackCircle(node).getCircle().setFill(Color.RED); // colora nodi
				//if (q non finita)
				for (Arrow arrow : visualGraph.getBlackCircle(node).getOutList()) {
					if (arrow.getTarget() == visualGraph.getBlackCircle(q.get(q.indexOf(node)+1))){ //colora frecce
						arrow.getLine1().setStroke(Color.RED);
						arrow.getLine2().setStroke(Color.RED);
						arrow.getLine3().setStroke(Color.RED);
					}
				}
			}
		});
		next.setOnMouseClicked(event ->{
			b = 0;
			//considero n e m quelli da collegare
			for (Arrow arrow : visualGraph.getBlackCircle(n).getOutList()) {
				if (arrow.getTarget() == visualGraph.getBlackCircle(m)) {
					arrow.getLine1().setStroke(Color.RED);
					arrow.getLine2().setStroke(Color.RED);
					arrow.getLine3().setStroke(Color.RED);
				}
			}
			visualGraph.getBlackCircle(m);
		});
		if (b==0 || b==1) return b;
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
