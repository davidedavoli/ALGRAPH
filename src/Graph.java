

import java.util.*;


public class Graph<T extends Comparable<T>> implements IGraph<T>{

	TreeMap<Node<T>, TreeMap<Node<T>, Integer>> pollo;
	public Graph() {
		this.pollo = new TreeMap<Node<T>, TreeMap<Node<T>, Integer>>();
	}

	@Override
	public void insertNode(Node<T> u) {
		if (!pollo.containsKey(u))
			pollo.put(u, new TreeMap<Node<T>, Integer>());
	}

	@Override
	public void deleteNode(Node<T> u) {
		pollo.remove(u);
		for (Node<T> follo : pollo.keySet()) {
			if (pollo.get(follo).keySet().contains(u))
				pollo.get(follo).remove(u);
		}
		
	}

	@Override
	public void insertEdge(Node<T> u, Node<T> v, Integer distance) {
		if (pollo.containsKey(u) && pollo.containsKey(v)) {
				pollo.get(u).put(v, distance);
		}
		
	}

	@Override
	public void deleteEdge(Node<T> u, Node<T> v) {
		if (pollo.containsKey(u) && pollo.containsKey(v) && pollo.get(u).containsKey(v)) {
			pollo.get(u).remove(v);
		}
			
		
	}

	@Override
	public Set<Node<T>> adj(Node<T> u) {
		TreeSet<Node<T>> p=new TreeSet<Node<T>>();
		if (!pollo.containsKey(u))
			return null;
		for (Node<T> gol : pollo.get(u).keySet()){
			p.add(gol);
		}
		return p;
	}

	@Override
	public Set<Node<T>> V() {
		return pollo.keySet();
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		String p="";
		for (Node <T> callo : pollo.keySet()) {
			p+=callo.toString()+" :"+pollo.get(callo).toString()+"\n";
		}
		System.out.print(p);

		
	}
	
	public Integer w(Node<T> a, Node<T> b) {
		if (pollo.containsKey(a) && pollo.containsKey(b) && pollo.get(a).containsKey(b))
			return pollo.get(a).get(b);
		else
			return null;
	}
	
	public Node<T> getRoot(int a){
		int i=0;
		for (Node<T> n : this.V()) {
			if (i==a)
				return n;
		}
		return null;
	}

}
