package graphPackage;
import java.util.*;
import algraphPackage.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class VisualGraph<T  extends Comparable<T>> {
	
	private TreeMap<Node<T>, blackCircle> mappa=new TreeMap<Node<T>, blackCircle>();
	Graph<T> G;
	Pane p;

	public VisualGraph(Graph<T> lucaserafini, Pane pane) {
		super();
		G=lucaserafini;
		for (Node<T> lucamariotti : lucaserafini.V()) {
			mappa.put(lucamariotti, new blackCircle(pane, (String) lucamariotti.getElement()));
		}
		for (Node<T> lucamariotti : lucaserafini.V()){
			for (Node<T> lucacontalbo : lucaserafini.adj(lucamariotti)) {
				mappa.get(lucamariotti).bind(mappa.get(lucacontalbo), G.w(lucamariotti, lucacontalbo).toString());
				
			}
		}
		this.p=pane;
	}
	
	public void removeAll() {
		G=null;
		p=null;
		List<Node<T>> toRemove= new ArrayList<Node<T>>(); 
		for (Node<T> key: mappa.keySet())
			toRemove.add(key);
		for (Node<T> key: toRemove)
			mappa.remove(key);
	}
	
	public void readGraph(Graph<T> lucaserafini, Pane p) {
		G=lucaserafini;
		for (Node<T> lucamariotti : lucaserafini.V()) {
			mappa.put(lucamariotti, new blackCircle(p, (String) lucamariotti.getElement()));
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
		System.out.println(G.V().size());
	}
	
	public void insertNode() {
		// TODO Auto-generated method stub
		Node<String> u=new Node<String>(String.valueOf(G.V().size()+1));
		G.insertNode((Node<T>) u);
		mappa.put((Node<T>) u, new blackCircle(p, (String) u.getElement()));
		Controller.boundsController(mappa.get(u), mappa.get(u).getPane());
		System.out.println(G.V().size());
	}
	
	
	public void renameNode(Node<String> n, String s) {
		G.setNodeValue(n, s);
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
	
	public void insertEdge(Node<T> u, Node<T> v, String s) {
		G.insertEdge(u, v, Integer.parseInt(s));
		insertEdge(u, v);
	}
	public Set<Arrow> chosenArrows() {
		HashSet<Arrow> chosen=new HashSet<Arrow>();
		for (blackCircle b: mappa.values()) {
			for (Arrow a: b.getOutList()) {
				if (a.getChosen()==true)
					chosen.add(a);
			}
		}
		return chosen;
	}
	public void deleteEdge(Node<T> u, Node<T> v) {
		G.deleteEdge(u, v);
		mappa.get(u).deleteLink(mappa.get(v));
	}
	public void deleteChosenArrows() {
		Iterator<Arrow> lucamariotti= chosenArrows().iterator();
		while (lucamariotti.hasNext()) {
			Arrow a=lucamariotti.next();
			deleteEdge(getNode(a.getParent()), getNode(a.getTarget()));
		}
	}
	public void deleteChosenNodes() {
		Iterator<Node<T>> lucamariotti= mappa.keySet().iterator();
		while( lucamariotti.hasNext()) {
			Node<T> pollo=lucamariotti.next();
			if (mappa.get(pollo).getChosen()) {
				this.deleteNode(pollo);
			}
		}
		
	}
	
	public void deleteChosenNode() {
		Node<T> toDelete=null;
		System.out.println(this.selectedCircles().toString());
		for(Node<T> pollo: mappa.keySet()) {
			if (mappa.get(pollo).getChosen()) {
				toDelete=pollo;
			}
		}
		if (toDelete!=null)
		this.deleteNode(toDelete);
		if(this.countSelected()>0)
		this.deleteChosenNode();
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
	
	public HashSet<blackCircle> selectedCircles(){//ritorna i nodi selezionati
		HashSet<blackCircle> set=new HashSet<blackCircle>(); 
		for (blackCircle b: mappa.values()) {
			if (b.getChosen()==true)
				set.add(b);
		}
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
	
	public void renameNode(blackCircle n, String s) {
		for (Node<T> b: mappa.keySet()) {
				if (mappa.get(b).equals(n))
					G.setNodeValue((Node<String>) b, s);
		
		}
	}
	
	public Node<T> getNode(blackCircle b){
		for (Node<T> nodo: mappa.keySet()) {
			if (mappa.get(nodo)==b)
				return nodo;
				}
		return null;
	}
	
	
	public Node<T> getSelectedNode(){
		for (Node<T> node : mappa.keySet()) {
			if (mappa.get(node).getChosen()==true) return node;
		}
		return null;
	}
	
	public void renameEdge(blackCircle p, blackCircle t, Integer value) {
		G.insertEdge(getNode(p), getNode(t), value);	
		}
		
	public List<Arrow> getArrows (){
		List<Arrow> a=new LinkedList<Arrow>();
		for (blackCircle n: mappa.values()) {
			a.addAll(n.getOutList());
		}
		return a;
	}
	
	
	public blackCircle getBlackCircle(Node<T> n) {
		return mappa.get(n);
		
	}
	
	public void setColor(Color c) {
		for (blackCircle b: mappa.values()) {
			b.getCircle().setFill(c);
		}
		for (Arrow a: getArrows()) {
			a.setColor(c);
		}
	}
	public void allBlack() {
		for(Node<T> n : G.V()) {
			mappa.get(n).getCircle().setFill(Color.BLACK);
			for (Arrow arrow : mappa.get(n).getOutList()) {
				arrow.getLine1().setStroke(Color.BLACK);
				arrow.getLine2().setStroke(Color.BLACK);
				arrow.getLine3().setStroke(Color.BLACK);
			}
		}
	}
}
