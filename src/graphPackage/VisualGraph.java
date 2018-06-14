package graphPackage;
import java.util.*;

import algraphPackage.blackCircle;
import javafx.scene.layout.Pane;

public class VisualGraph<T  extends Comparable<T>> {
	
	private TreeMap<Node<T>, blackCircle> mappa=new TreeMap<Node<T>, blackCircle>();
	Graph<T> G;
	Pane p;

	public VisualGraph(Graph<T> lucaserafini, Pane p) {
		super();
		G=lucaserafini;
		for (Node<T> lucamariotti : lucaserafini.V()) {
			mappa.put(lucamariotti, new blackCircle(p));
		}
		for (Node<T> lucamariotti : lucaserafini.V()){
			for (Node<T> lucacontalbo : lucaserafini.adj(lucamariotti)) {
				mappa.get(lucamariotti).bind(mappa.get(lucacontalbo), G.w(lucamariotti, lucacontalbo).toString());
				
			}
		}
		this.p=p;
	}
	public void insertNode(Node<T> u) {
		// TODO Auto-generated method stub
		G.insertNode(u);
		mappa.put(u, new blackCircle(p));
	}

	public void deleteNode(Node<T> u) {
		// TODO Auto-generated method stub
		for (Node<T> lucaserafini : mappa.keySet()) {
			for (Node<T> lucamariotti : G.adj(lucaserafini)) {
				if (lucamariotti==u) {
					mappa.get(lucaserafini).deleteLink(mappa.get(u));
				}
			}
		}
		G.deleteNode(u);
		mappa.remove(u);
	}

	public void insertEdge(Node<T> u, Node<T> v) {
		
		mappa.get(u).bind(mappa.get(v), G.w(u, v).toString());
		
		
	}

	public void deleteEdge(Node<T> u, Node<T> v) {
		mappa.get(u).deleteLink(mappa.get(v));
		G.deleteEdge(u, v);
	}
	
	public void deleteChoosenEdges() {
		for(Node<T> lucamariotti: mappa.keySet()) {
			if (mappa.get(lucamariotti).getChosen()) {
				this.deleteNode(lucamariotti);
			}
		}
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
