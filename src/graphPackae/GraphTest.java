package graphPackae;

public class GraphTest {

	public static void main(String[] args) {
		Graph<String> Tudor=new Graph<String>();
		GraphVisit<String> Positano=new GraphVisit<String>();
		Tudor.randomGraph(10, 2, 10, -10);
		Tudor.print();
		Positano.BellmanFord(Tudor, true);
		Tudor.outGraph("src/tudor.txt");
		Graph<String> Denis=new Graph<String>();
		Denis.inGraph("src/tudor.txt");
		Denis.print(); 

	}

}
