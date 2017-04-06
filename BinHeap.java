import java.util.HashMap;
import java.util.Map;

public class BinHeap {
	
public static int cntr = 0;
	
	public BinHeapNode buildHuffmanTree(HashMap<String, Integer> huff) {
		BinHeapNode left;
		BinHeapNode right;
		BinHeapNode top;
		
		cntr = huff.size();
		BinHeapNode[] minHeap = createAndBuildMinHeap(huff);
	 
	    while (cntr != 1)
	    {
	    	left = extractMin(minHeap);
	        right = extractMin(minHeap);
	 
	        top = new BinHeapNode("$", left.frequency + right.frequency);
	        top.left = left;
	        top.right = right;
	        
	        cntr++;
		    minHeap[cntr - 1] = top;		    
	    }
	    return extractMin(minHeap);
    }
	
	public static BinHeapNode[] createAndBuildMinHeap(HashMap<String, Integer> huff)
	{
	    BinHeapNode[] minHeap = new BinHeapNode[huff.size()];
	    int i = 0;
	    for (Map.Entry<String, Integer> entry : huff.entrySet()){
	        minHeap[i] = new BinHeapNode(entry.getKey(),entry.getValue());
	        i++;
	    }
	    
	    buildMinHeap(minHeap);
	    return minHeap;
	}
	
	public static void buildMinHeap(BinHeapNode[] minHeap)
	{
	    int n = minHeap.length - 1;
	    int i;
	    for (i = (n - 1) / 2; i >= 0; --i)
	        minHeapify(minHeap, i, cntr);
	}
	
	public static void minHeapify(BinHeapNode[] minHeap, int idx, int cntr)
	{
		int smallest = idx;
	    int left = 2 * idx + 1;
	    int right = 2 * idx + 2;
	 
	    if (left < cntr && minHeap[left].frequency < minHeap[smallest].frequency)
	      smallest = left;
	 
	    if (right < cntr && minHeap[right].frequency < minHeap[smallest].frequency)
	      smallest = right;
	 
	    if (smallest != idx)
	    {
	        swapMinHeapNode(minHeap,smallest,idx);
	        minHeapify(minHeap, smallest, cntr);
	    }
	}
	
	public static void swapMinHeapNode(BinHeapNode[] minHeap, int smallest, int idx)
	{
	    BinHeapNode temp = minHeap[smallest];
	    minHeap[smallest] = minHeap[idx];
	    minHeap[idx] = temp;
	}
	
	public static BinHeapNode extractMin(BinHeapNode[] minHeap)
	{
	    BinHeapNode tmp = minHeap[0];
	    minHeap[0] = minHeap[cntr - 1];
	    cntr--;
	    minHeapify(minHeap, 0, cntr);
	    return tmp;
	}
}
