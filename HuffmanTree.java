import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTree {
	
	public static void build_tree_using_binary_heap(HashMap<String, Integer> huff){
		BinHeap binHeap = new BinHeap();
		binHeap.buildHuffmanTree(huff);
	}
	
	public static void build_tree_using_4way_heap(HashMap<String, Integer> huff){
		CacOptHeap bin4Heap = new CacOptHeap();
		bin4Heap.buildHuffmanTree(huff);
	}
	
	public static void build_tree_using_pairing_heap(HashMap<String, Integer> huff){
    	PairHeap bhwf=new PairHeap();
    	bhwf.buildHuffmanTree();	

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try {
		    BufferedReader in = new BufferedReader(new FileReader("sample_input_large.txt"));
		    String str;
		    HashMap<String, Integer> huff = new HashMap<String,Integer>();
		    
		    // Insert into HashMap
		    while ((str = in.readLine()) != null){
		    	Integer freq = huff.get(str);
		    	if(freq != null)
		    		freq++;
		    	else
		    		freq = 1;
		    	
		    	if(str != "" && !str.isEmpty() && str != null){
		    		huff.put(str,freq);
		    	}
		    }
		    
		    in.close();
		    
		    long startTime;
		    
			// binary heap
		    startTime = System.nanoTime();
		    
			for(int i = 0; i < 10; i++){ //run 10 times on given data set
				build_tree_using_binary_heap(huff);
			}
			 
			System.out.println("Time using binary heap (nanoseconds): " + (System.nanoTime() - startTime)/10);
		    
			// 4-way heap
			startTime = System.nanoTime();
		    
			for(int i = 0; i < 10; i++){ //run 10 times on given data set
				build_tree_using_4way_heap(huff);
			}
			 
			System.out.println("Time using 4-way heap (nanoseconds): " + (System.nanoTime() - startTime)/10);
			
			// pairing heap
			build_tree_using_pairing_heap(huff);
		    
		} catch (IOException e) {
		}

	}

}
