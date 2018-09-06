package graphPackage;


import java.io.*;
import java.util.*;
import algraphPackage.Controller;


public class Graph<T extends Comparable<T>> implements IGraph<T>{

	HashMap<Node<T>, HashMap<Node<T>, Integer>> grafo;
	public Graph() {
		this.grafo = new HashMap<Node<T>, HashMap<Node<T>, Integer>>();
	}

	@Override
	public void insertNode(Node<T> u) {
		if (!grafo.containsKey(u))
			grafo.put(u, new HashMap<Node<T>, Integer>());
		else {
			grafo.remove(u);
			grafo.put(u, new HashMap<Node<T>, Integer>());
		}
	}

	@Override
	public void deleteNode(Node<T> u) {
		grafo.remove(u);
		for (Node<T> follo : grafo.keySet()) {
			if (grafo.get(follo).keySet().contains(u))
				grafo.get(follo).remove(u);
		}
		
	}

	@Override
	public void insertEdge(Node<T> u, Node<T> v, Integer distance) {
		if (grafo.containsKey(u) && grafo.containsKey(v)) {
				grafo.get(u).put(v, distance);
		}
		
	}

	@Override
	public void deleteEdge(Node<T> u, Node<T> v) {
		if (grafo.containsKey(u) && grafo.containsKey(v) && grafo.get(u).containsKey(v)) {
			grafo.get(u).remove(v);
		}
			
		
	}

	@Override
	public Set<Node<T>> adj(Node<T> u) {
		TreeSet<Node<T>> p=new TreeSet<Node<T>>();
		if (!grafo.containsKey(u))
			return null;
		for (Node<T> gol : grafo.get(u).keySet()){
			p.add(gol);
		}
		return p;
	}

	@Override
	public Set<Node<T>> V() {
		return grafo.keySet();
	}
	public void setNodeValue(Node<String> n, String s) {
		/*HashMap<Node<T>, HashMap<Node<T>, Integer>> newGraph=new HashMap<Node<T>, HashMap<Node<T>, Integer>>();
		Node<String> m= new Node<String>(null);
		HashMap<Node<T>, Integer> dist=new HashMap<Node<T>, Integer>();
		
		for (Node<T> nodea: grafo.keySet()) {
			if (nodea.equals(n)) {
				newGraph.put((Node<T>)m, grafo.get(n));
			}
			else {
				for (Node<T> nodeb: grafo.get(nodea).keySet()) {
					if (!nodeb.equals(n))
					dist.put(nodeb, grafo.get(nodea).get(nodeb));
					else
					dist.put((Node<T>)m, grafo.get(nodea).get(nodeb));
				}
				newGraph.put(nodea, dist);
				dist=new HashMap<Node<T>, Integer>();
			}
			
		}
		
		grafo=newGraph;
		*/
		n.setValue(s);
	}
	@Override
	public void print() {
		String p="";
		for (Node <T> callo : grafo.keySet()) {
			p+=callo.toString()+" :"+grafo.get(callo).toString()+"\n";
		}
		System.out.print(p);

		
	}
	
	public Integer w(Node<T> a, Node<T> b) {
		if (grafo.containsKey(a) && grafo.containsKey(b) && grafo.get(a).containsKey(b))
			return grafo.get(a).get(b);
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
		HashMap<Node<T>, Integer> nodes= new HashMap<Node<T>, Integer>();
		try {
			FileWriter b=new FileWriter(path);
			BufferedWriter out=new BufferedWriter(b);
			out.write("<N>");
			out.write("\n");
				for (Node<T> n : grafo.keySet()) {
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
				for (Node<T> n : grafo.keySet()) {
					if (n!=null && grafo.get(n)!=null)
				for (Node<T> o : grafo.get(n).keySet()) {
					out.write(nodes.get(n).toString());
					out.write(":");
					out.write(nodes.get(o).toString());
					out.write(":");
					out.write(grafo.get(n).get(o).toString());
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
		HashMap<Integer, Node<T>> nodes= new HashMap<>();
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
			return "Error in input file";
					}
		}catch (NumberFormatException e) {
			return "Error in input file";
		}
		return "Graph successfuly imported";
		
		
		
	}
	
	public void removeAll() {
		List<Node<T>> toRemove= new ArrayList<Node<T>>(); 
		for (Node<T> key: grafo.keySet())
			toRemove.add(key);
		for (Node<T> key: toRemove)
			grafo.remove(key);
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
		HashMap<Integer, Node<T>> nodes= new HashMap<>();
		for (Integer i=0; i<nnodes; i++) {
			nodes.put(i, new Node<T>((T)i.toString()));
			this.insertNode(nodes.get(i));
		}
		for (Integer i=0; i<nedges; i++) {
			int nodea;
			do{
				nodea=rand.nextInt(nnodes);
				}while (grafo.get(nodes.get(nodea)).keySet().size()>=(nnodes-1));
			
			int nodeb;
			do{
				nodeb=rand.nextInt(nnodes);
			}while (nodeb==nodea || grafo.get(nodes.get(nodea)).get(nodes.get(nodeb))!=null);
			if (Mw==mw)
				this.insertEdge(nodes.get(nodea), nodes.get(nodeb), mw);
			else
				this.insertEdge(nodes.get(nodea), nodes.get(nodeb), rand.nextInt(Mw-mw)+mw);
		}
		
	}
	public HashMap<Node<T>, HashMap<Node<T>, Integer>> getHashMap(){
		return grafo;
	}
}
