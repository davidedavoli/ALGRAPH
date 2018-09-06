package graphPackage;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

import algraphPackage.Controller;

public class BellmanFordMoore<T extends Comparable<T>> {
	VisualGraph<T> visualGraph;
	Graph<T> G;
	private int pc;
	private TreeMap<Node<T>, Integer> index;
	private Integer[] distances;
	private Node<T>[] parents;
	private Node<T> radice;
	private LinkedList<Node<T>> q;
	private Node<T> n;
	private Iterator<Node<T>> iter;
	private Node<T> m;
	private LinkedList<Node<T>> speranza;
	
	
	
	public BellmanFordMoore(VisualGraph<T> vg, Node<T> radice){
		visualGraph=vg;
		G=visualGraph.getGraph();
		pc=0;
		
		Graph<T> G=visualGraph.getGraph();
		index=new TreeMap<Node<T>, Integer>();
		
		int i=0;
		for(Node<T> n : G.V()) {
			index.put(n, i);
			i=i+1;
		}
		i=0;
		
		distances= new Integer[G.V().size()];
		for (Node<T> n : G.V())
			distances[index.get(n)]=null;
		
		
		parents= new Node[G.V().size()];
		for (Node<T> n : G.V())
			parents[index.get(n)]=null;
		
		//Node<T> radice=G.getRoot(0);
		
		if (radice!=null)
		distances[index.get(radice)]=0;
		
		q=new LinkedList<Node<T>>();
		q.add(radice);
	}
	public int getPc() {
		return pc;
	}
	public TreeMap<Node<T>, Integer> getIndex() {
		return index;
	}
	public Integer[] getDistances() {
		return distances;
	}
	public Node<T>[] getParents() {
		return parents;
	}
	public Node<T> getRadice() {
		return radice;
	}
	
	
	public void programCounter() {
		switch (pc) {
		case 0:
			popQueue();
			break;
		case 1:
			forNodesCondition();
			break;
		case 2:
			relaxCondition();
			break;
		case 3:
			qContains();
			break;
		case 4:
			qInsert();
			break;
		case 5:
			setParent();
			break;
		case 6:
			setDistance();
			break;
		default:
			break;
		}
	}
	
	void popQueue() {
		if (!q.isEmpty()) {
		n=q.pop();
		//for(Node<T> node: G.adj(n))
		//	speranza.add(node);
		iter=G.adj(n).iterator();
		Controller.items.add(0, "n=Q.dequeue() //deuqueue of node "+n.getElement()+" from queue");
		pc++;
		}
		else {
			Controller.items.add(0, "End of computation");
			Controller.setEnd(true);
		}
	}
	
	public LinkedList<Node<T>> getQ() {
		return q;
	}
	public Node<T> getN() {
		return n;
	}
	public Node<T> getM() {
		return m;
	}
	void forNodesCondition() {
		Controller.items.add(0, "for (m: adj(n)) //evaluation of cycle condition");
		if (iter.hasNext()) {
			pc++;
			setM();
			}
		else
			pc=0;
	}
	
	void setM() {
		m=iter.next();
		//m=speranza.pop();
		Controller.items.add(0, "m=adj(n) //set M="+m.getElement());
	}
	
	void relaxCondition() {
		boolean b=distances[index.get(m)]==null || distances[index.get(n)]+G.w(n, m)<distances[index.get(m)];
		
		if (b) {
			Controller.items.add(0, "D[m]>D[n]+w(n,m) //"+m.getElement()+" is relaxed");
			pc++;
		}
		else {
			Controller.items.add(0, "D[m]<D[n]+w(n,m) //"+m.getElement()+" is already nearer");
			pc=1;
			
		}
	}
	
	void qContains() {
		if (!q.contains(m)) {
			Controller.items.add(0, "!Q.contains(m)=true //"+m.getElement()+" is not in the queue");
			pc++;
		}
		else
			pc+=2;
	}
	
	void qInsert() {
		Controller.items.add(0, "Q.enqueue(m) //Node "+m.getElement()+" enqueued");
		q.addLast(m);
		pc++;
	}
	
	void setParent() {
		parents[index.get(m)]=n;
		Controller.items.add(0, "P[m]=n //Parent of node "+m.getElement()+" updated. New parent: "+n.getElement());
		pc++;
	}
	
	void setDistance() {
		distances[index.get(m)]=distances[index.get(n)]+G.w(n, m);
		Controller.items.add(0, "D[m]=d[m]+ G.w(n,m) //Distance of node "+m.getElement()+" updated. New distance: "+distances[index.get(m)]);
		pc=1;
	}
	
	public void setRadice(Node<T> r) {
		radice=r;
		pc=0;
		
		Graph<T> G=visualGraph.getGraph();
		index=new TreeMap<Node<T>, Integer>();
		
		int i=0;
		for(Node<T> n : G.V()) {
			index.put(n, i);
			i=i+1;
		}
		i=0;
		
		distances= new Integer[G.V().size()];
		for (Node<T> n : G.V())
			distances[index.get(n)]=null;
		
		
		parents= new Node[G.V().size()];
		for (Node<T> n : G.V())
			parents[index.get(n)]=null;
		
		//Node<T> radice=G.getRoot(0);
		
		if (radice!=null)
		distances[index.get(radice)]=0;
		
		q=new LinkedList<Node<T>>();
		q.add(radice);
		
		Controller.items.add(0, "Computation Started");
	}
	
	
	public VisualGraph<T> getVisualGraph() {
		return visualGraph;
	}
	
	
}
