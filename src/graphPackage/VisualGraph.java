package algraphPackage;
import java.util.*;
import algraphPackage.*;

import algraphPackage.Arrow;
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
		Arrow toRemove=null;
		for (Node<T> lucaserafini : mappa.keySet()) {
			for (Arrow a: mappa.get(lucaserafini).getInList()) {
				if (a.getParent()==mappa.get(u)) {
					toRemove=(a);
				}
			}
			if(mappa.get(lucaserafini).getInList().contains(toRemove))
			mappa.get(lucaserafini).getInList().remove(toRemove);
		}
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
	
	public void deleteChoosenNodes() {
		Iterator<Node<T>> lucamariotti= mappa.keySet().iterator();
		while( lucamariotti.hasNext()) {
			Node<T> pollo=lucamariotti.next();
			if (mappa.get(pollo).getChosen()) {
				System.out.println(pollo.getElement());
				this.deleteNode(pollo);
			}
		}
		
	}
	
	public void deleteChoosenNode() {
		Node<T> toDelete=null;
		for(Node<T> pollo: mappa.keySet()) {
			if (mappa.get(pollo).getChosen()) {
				System.out.println(pollo.getElement());
				toDelete=pollo;
			}
		}
		if (toDelete!=null) {
			//p.getChildren().remove(toDelete.getElement().);
			this.deleteNode(toDelete);
		}
		if(this.countSelected()>0)
		this.deleteChoosenNode();
	}
	
	public Collection<blackCircle> circles(){
		return mappa.values();
	}
	
	public Graph<T> getGraph(){
		return G; 
	}
	
	public int countSelected() {//conta i nodi selezionati 
		int c=0;
		for (blackCircle b: mappa.values()) {
			System.out.println(b.getText());
			if (b.getChosen()==true)
				c++;
		}
		return c;
	}
	
	public List<blackCircle> selectedCircles(){//ritorna i nodi selezionati
		List<blackCircle> set=new ArrayList<blackCircle>(); 
		for (blackCircle b: mappa.values()) {
			if (b.getChosen()==true)
				set.add(b);
		}
		System.out.println(set.size());
		return set;
	}
	public void visualize() {
		for (blackCircle b: mappa.values()) {
			b.pushInPane();
		}
		for (blackCircle b: mappa.values()) {
		for (Arrow a: b.getOutList()) {
			a.pushInPane(b.getPane());
		}
				
		}
	}
}
