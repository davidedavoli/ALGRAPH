package graphPackage;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class GraphTest {

	public static void main(String[] args) {
		Graph<String> Tudor=new Graph<String>();
		GraphVisit<String> Positano=new GraphVisit<String>();
		Tudor.randomGraph(10, 2, 10, -10, true);
		Tudor.print();
		LinkedList<Node<String>> t=(LinkedList<Node<String>>) Positano.detectNegativeCycles(Tudor, Tudor.getRoot(0));
		System.out.println(t.toString());
		Positano.BellmanFord(Tudor);
		Tudor.outGraph("src/tudor.txt");
		Graph<String> Denis=new Graph<String>();
		Denis.inGraph("src/tudor.txt");
		Denis.print(); 

	}

}
