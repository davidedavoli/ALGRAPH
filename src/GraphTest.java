
public class GraphTest {

	public static void main(String[] args) {
		Graph<String> Tudor=new Graph<String>();
		Tudor.randomGraph(10, 2, 20, 10, 10, -10);
		Tudor.print();
		Tudor.outGraph("src/tudor.txt");
		Graph<String> Denis=new Graph<String>();
		Denis.inGraph("src/tudor.txt");
		Denis.print(); 

	}

}
