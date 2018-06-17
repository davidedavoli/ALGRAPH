package graphPackage;


import java.io.*;
import java.util.*;
import algraphPackage.Controller;


public class Graph<T extends Comparable<T>> implements IGraph<T>{

	TreeMap<Node<T>, TreeMap<Node<T>, Integer>> pollo;
	public Graph() {
		this.pollo = new TreeMap<Node<T>, TreeMap<Node<T>, Integer>>();
	}

	@Override
	public void insertNode(Node<T> u) {
		if (!pollo.containsKey(u))
			pollo.put(u, new TreeMap<Node<T>, Integer>());
		else {
			pollo.remove(u);
			pollo.put(u, new TreeMap<Node<T>, Integer>());
		}
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
	public void setNodeValue(Node<String> n, String s) {
		n.setValue(s);
	}
	@Override
	public void print() {
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
	public void outGraph(String path) {
		Integer i=0;
		TreeMap<Node<T>, Integer> nodes= new TreeMap<Node<T>, Integer>();
		try {
			FileWriter b=new FileWriter(path);
			BufferedWriter out=new BufferedWriter(b);
			out.write("<N>");
			out.write("\n");
				for (Node<T> n : pollo.keySet()) {
					out.write(i.toString()+":");
					out.write(((String) n.getElement()));
					nodes.put(n, i);
					out.write("\n");
					i++;
				}
			out.write("</N>");
			out.write("\n");
			out.write("<E>");
			out.write("\n");
			i=0;
				for (Node<T> n : pollo.keySet()) {
				for (Node<T> o : pollo.get(n).keySet()) {
					out.write(nodes.get(n).toString());
					out.write(":");
					out.write(nodes.get(o).toString());
					out.write(":");
					out.write(pollo.get(n).get(o).toString());
					out.write("\n");
				} 
			}
				out.write("</E>");
				out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("FILENOTFOUND::OUT");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("EXCEPTION::OUT");
		}
	}
	public String inGraph(String path) {
		Integer i=0;
		TreeMap<Integer, Node<T>> nodes= new TreeMap<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String inStr="";
			inStr=in.readLine();
			if (!inStr.equals("<N>") || inStr==null) {
				throw new IOException("SyntaxError");
				//Error.Inputfile();
			}
			inStr=in.readLine();
			while (!inStr.equals("</N>")) {
				String[] line=inStr.split(":");
				if (line.length<2) {
					throw new IOException("SyntaxError");
					//Error.Inputfile();
					}
				Node<T> m=new Node<T>((T)line[1]);
				nodes.put(Integer.parseInt(line[0]), m);
				this.insertNode(m);
				i++;
				inStr=in.readLine();
			}
			inStr=in.readLine();
			if (!inStr.equals("<E>")) {
				throw new IOException("SyntaxError");
				//Error.Inputfile();
			}
			inStr=in.readLine();
			while (!inStr.equals("</E>")) {
				String[] line=inStr.split(":");
				if (line.length!=2) {
					throw new IOException("SyntaxError");
					//Error.Inputfile()
					}
				this.insertEdge(nodes.get(Integer.parseInt(line[0])), nodes.get(Integer.parseInt(line[1])), Integer.parseInt(line[2]));
				inStr=in.readLine();
				
				
				 
			}
			
			
			
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("FILENOTFOUND");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if(e.getMessage()=="Syntax error")
			{
				return e.getMessage();
				}
			else {
			e.printStackTrace();
			return "Error in input file";
					}
		}
		return "Graph successfuly imported";
		
		
		
	}
	
	public void removeAll() {
		List<Node<T>> toRemove= new ArrayList<Node<T>>(); 
		for (Node<T> key: pollo.keySet())
			toRemove.add(key);
		for (Node<T> key: toRemove)
			pollo.remove(key);
	}
	
	public void randomGraph(int Mnodes, int mnodes, int Mw, int mw, boolean dense) {
		Random rand = new Random();
		int nnodes;
		int wedge;
		if (Mnodes==mnodes)
			nnodes=mnodes;
		else
			nnodes=rand.nextInt(Mnodes-mnodes)+mnodes;
		int nedges;
		if (dense)
		nedges=rand.nextInt(nnodes*(nnodes-1)-(nnodes-1))+nnodes-1;
		else 
			nedges=rand.nextInt(nnodes*3)+nnodes-1;
		nedges=Math.min(nedges, nnodes*(nnodes-1)/2);
		TreeMap<Integer, Node<T>> nodes= new TreeMap<>();
		for (Integer i=0; i<nnodes; i++) {
			nodes.put(i, new Node<T>((T)i.toString()));
			this.insertNode(nodes.get(i));
		}
		for (Integer i=0; i<nedges; i++) {
			int nodea;
			do{
				nodea=rand.nextInt(nnodes);
				}while (pollo.get(nodes.get(nodea)).keySet().size()>=(nnodes-1));
			
			int nodeb;
			do{
				nodeb=rand.nextInt(nnodes);
			}while (nodeb==nodea || pollo.get(nodes.get(nodea)).get(nodes.get(nodeb))!=null);
			if (Mw==mw)
				this.insertEdge(nodes.get(nodea), nodes.get(nodeb), mw);
			else
				this.insertEdge(nodes.get(nodea), nodes.get(nodeb), rand.nextInt(Mw-mw)+mw);
		}
		
	}

}
