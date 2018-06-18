package graphPackage;
import java.awt.Color;
import java.util.*;

import algraphPackage.Arrow;
import algraphPackage.Controller;
import javafx.scene.control.Button;


public class GraphVisit<T extends Comparable<T>> {
	private int counter=0;
	public static Integer[] distances;
	public GraphVisit() {}
	
	public List<Integer> getDistances() {
		List list = new ArrayList<Integer>();
		for (Integer a : distances) {
			list.add(a);
		}
		return list;
	}
	public void BellmanFord(VisualGraph<T> visualGraph, Node<T> radice, Button end, Button next, Button button1, Button button2, Button button3, Button button4) {
		Controller<T> c=new Controller<>();
		int i=0;
		Graph<T> G=visualGraph.getGraph();
		//boolean tuttoalvolo;
		TreeMap<Node<T>, Integer> index=new TreeMap<Node<T>, Integer>();
		for(Node<T> n : G.V()) {
			index.put(n, i);
			i=i+1;
		}
		i=0;
		
		distances= new Integer[G.V().size()];
		for (Node<T> n : G.V())
			distances[index.get(n)]=null;
		
		
		Node<T>[] parents= new Node[G.V().size()];
		for (Node<T> n : G.V())
			parents[index.get(n)]=null;
		
		//Node<T> radice=G.getRoot(0);
		
		distances[index.get(radice)]=0;
		
		LinkedList<Node<T>> q=new LinkedList<Node<T>>();
		q.add(radice);
		
		
		end.setOnMouseClicked(event -> {
		while (!q.isEmpty()) {
			
			Node<T> n=q.pop();
			c.GraphVisualize(visualGraph, radice, q, n, null, distances, parents, index);
			
				for(Node<T> m : G.adj(n)){
					c.GraphVisualize(visualGraph, radice,q, n, m, distances, parents, index);
					if (distances[index.get(m)]==null || distances[index.get(n)]+G.w(n, m)<distances[index.get(m)]) {			
						if (!q.contains(m)) {
							q.addLast(m);
							c.GraphVisualize(visualGraph, radice, q, n, m, distances, parents, index);
							}
						parents[index.get(m)]=n;
						c.GraphVisualize(visualGraph, radice, q, n, m, distances, parents, index);
						distances[index.get(m)]=distances[index.get(n)]+G.w(n, m);
						c.GraphVisualize(visualGraph, radice, q, n, m, distances, parents, index);
						
					}	
				}
				counter = counter+1;
				if (counter==G.V().size()*G.V().size()) {
					counter = 0;
					break;
				}
		}
		end.setDisable(true);
		next.setDisable(true);
		button1.setDisable(false);
		button2.setDisable(false);
		button3.setDisable(false);
		button4.setDisable(false);
	});
		next.setOnMouseClicked(event -> {
			Node<T> n=q.pop();
			c.GraphVisualize(visualGraph, radice, q, n, null, distances, parents, index);
			
				for(Node<T> m : G.adj(n)){
					c.GraphVisualize(visualGraph, radice,q, n, m, distances, parents, index);
					if (distances[index.get(m)]==null || distances[index.get(n)]+G.w(n, m)<distances[index.get(m)]) {			
						if (!q.contains(m)) {
							q.addLast(m);
							c.GraphVisualize(visualGraph, radice, q, n, m, distances, parents, index);
							}
						parents[index.get(m)]=n;
						c.GraphVisualize(visualGraph, radice, q, n, m, distances, parents, index);
						distances[index.get(m)]=distances[index.get(n)]+G.w(n, m);
						c.GraphVisualize(visualGraph, radice, q, n, m, distances, parents, index);
						
					}	
				}
				counter = counter+1;
				if (counter==G.V().size()*G.V().size() || q.isEmpty()) {
					counter = 0;
					end.setDisable(true);
					next.setDisable(true);
					button1.setDisable(false);
					button2.setDisable(false);
					button3.setDisable(false);
					button4.setDisable(false);
					}
		});
		
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
