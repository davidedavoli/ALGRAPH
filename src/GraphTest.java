
public class GraphTest {

	public static void main(String[] args) {
		Graph<String> Tudor=new Graph<String>();
		Node<String> lollo=new Node<String>("Lollo");
		Node<String> pollo=new Node<String>("Pollo");
		Tudor.insertNode(lollo);
		Tudor.insertNode(pollo);
		Tudor.insertEdge(lollo, pollo, 10);
		Tudor.print();
		Tudor.outGraph("src/tudor.txt");
		

	}

}
