package graphPackae;
import java.util.*;

import algraphPackage.blackCircle;

public class VisualGraph<T  extends Comparable<T>> {
	
	private TreeMap<Node<T>, blackCircle> mappa=new TreeMap<Node<T>, blackCircle>();
	Graph<T> G;

	public VisualGraph(Graph<T> lucaserafini) {
		super();
		G=lucaserafini;
		for (Node<T> lucamariotti : lucaserafini.V()) {
			mappa.put(lucamariotti, new blackCircle(null));
		}
		for (Node<T> lucamariotti : lucaserafini.V()){
			for (Node<T> lucacontalbo : lucaserafini.adj(lucamariotti)) {
				//mappa.get(lucamariotti).join(lucacontalbo);
				
			}
		}
	}
	public void insertNode(Node<T> u) {
		// TODO Auto-generated method stub
		G.insertNode(u);
		mappa.put(u, new blackCircle(null));
	}

	public void deleteNode(Node<T> u) {
		// TODO Auto-generated method stub
		for (Node<T> lucaserafini : mappa.keySet()) {
			for (Node<T> lucamariotti : G.adj(lucaserafini)) {
				if (lucamariotti==u) {
					//mappa.get(lucaserafini).deleteline(mappa.get(u));
				}
			}
		}
		G.deleteNode(u);
		mappa.remove(u);
	}

	public void insertEdge(Node<T> u, Node<T> v) {
		
		//mappa.get(u).join(mappa.get(v));
		
		
	}

	public void deleteEdge(Node<T> u, Node<T> v) {
		//mappa.get(u).deleteline(mappa.get(v));
		
	}
	
	public Set<blackCircle> circles(){
		TreeSet<blackCircle> loquace=new TreeSet<blackCircle>();
		for (Node<T> lambertosposini : mappa.keySet()) {
			loquace.add(mappa.get(lambertosposini));
		}
		return loquace;
	}
	
	
}
