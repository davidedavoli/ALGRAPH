package graphPackage;
import java.util.*;

import algraphPackage.blackCircle;
import javafx.scene.layout.Pane;

public class VisualGraph<T  extends Comparable<T>> {
	
	private TreeMap<Node<T>, blackCircle> mappa=new TreeMap<Node<T>, blackCircle>();
	Graph<T> G;

	public VisualGraph(Graph<T> lucaserafini, Pane p) {
		super();
		G=lucaserafini;
		for (Node<T> lucamariotti : lucaserafini.V()) {
			mappa.put(lucamariotti, new blackCircle(p));
		}
		for (Node<T> lucamariotti : lucaserafini.V()){
			for (Node<T> lucacontalbo : lucaserafini.adj(lucamariotti)) {
				//mappa.get(lucamariotti).join(lucacontalbo, p);
				
			}
		}
	}
	public void insertNode(Node<T> u, Pane p) {
		// TODO Auto-generated method stub
		G.insertNode(u);
		mappa.put(u, new blackCircle(p));
	}

	public void deleteNode(Node<T> u, Pane p) {
		// TODO Auto-generated method stub
		for (Node<T> lucaserafini : mappa.keySet()) {
			for (Node<T> lucamariotti : G.adj(lucaserafini)) {
				if (lucamariotti==u) {
					//mappa.get(lucaserafini).deleteline(mappa.get(u), p);
				}
			}
		}
		G.deleteNode(u);
		mappa.remove(u);
	}

	public void insertEdge(Node<T> u, Node<T> v, Pane p) {
		
		//mappa.get(u).join(mappa.get(v, Pane p));
		
		
	}

	public void deleteEdge(Node<T> u, Node<T> v, Pane p) {
		//mappa.get(u).deleteline(mappa.get(v), Pane p);
		
	}
	
	public Set<blackCircle> circles(){
		TreeSet<blackCircle> loquace=new TreeSet<blackCircle>();
		for (Node<T> lambertosposini : mappa.keySet()) {
			loquace.add(mappa.get(lambertosposini));
		}
		return loquace;
	}
	
	public Graph<T> getGraph(){
		return G; 
	}
	
	
}
