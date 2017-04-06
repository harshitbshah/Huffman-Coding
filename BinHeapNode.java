public class BinHeapNode {
	String data;
	Integer frequency;
	BinHeapNode left;
	BinHeapNode right;
	
	public BinHeapNode(String data, Integer frequency) {
		super();
		this.data = data;
		this.frequency = frequency;
		this.left = null;
		this.right = null;
	}
	
}
