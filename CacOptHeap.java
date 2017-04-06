import java.util.HashMap;
import java.util.Map;

public class CacOptHeap {
	
public static int cntr = 0;
	
	public BinHeapNode buildHuffmanTree(HashMap<String, Integer> huff) {
		BinHeapNode left;
		BinHeapNode right;
		BinHeapNode top;
		
		cntr = huff.size();
		BinHeapNode[] minHeap = createAndBuildMinHeap(huff);
	 
	    while (cntr != 1)
	    {
	        left  = extractMin(minHeap);
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
	    for (i = (n - 1) / 4; i >= 0; --i)
	        minHeapify(minHeap, i, cntr);
	}
	
	public static void minHeapify(BinHeapNode[] minHeap, int idx, int cntr)
	{
	    int smallest = idx;
	    int left1  = 4 * idx + 1;
	    int left2  = 4 * idx + 2;
	    int right1 = 4 * idx + 3;
	    int right2 = 4 * idx + 4;
	 
	    if (left1 < cntr && minHeap[left1].frequency < minHeap[smallest].frequency)
	      smallest = left1;
	    
	    if (left2 < cntr && minHeap[left2].frequency < minHeap[smallest].frequency)
		      smallest = left2;
	    
	    if (right1 < cntr && minHeap[right1].frequency < minHeap[smallest].frequency)
		      smallest = right1;
	 
	    if (right2 < cntr && minHeap[right2].frequency < minHeap[smallest].frequency)
	      smallest = right2;
	 
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
