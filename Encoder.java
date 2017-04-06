import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Encoder {
	
	public static void generateCodes(BinHeapNode root, String str, HashMap<String, String> charCode)
	{
	    if(root == null)
	    	return;
	    
	    if(root.left == null && root.right == null)
	    	charCode.put(root.data, str);
	    
	    generateCodes(root.left, str+"0",charCode);
	    generateCodes(root.right,str+"1",charCode);
	}
	
	public static boolean isLeaf(BinHeapNode root)
	{
	    return !(root.left != null) && !(root.right != null) ;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
		    String str;
		    HashMap<String, Integer> huff = new HashMap<String,Integer>();
		    // Building Frequency Table 
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
		    
		    //Building Huffman Tree
		    CacOptHeap binHeap = new CacOptHeap();
			BinHeapNode root = binHeap.buildHuffmanTree(huff);
			
			//Generate Code-Table	
			HashMap<String, String> charCode = new HashMap<String, String>();
			generateCodes(root, "", charCode);
			
			PrintWriter writer = new PrintWriter("code_table.txt", "UTF-8");
			for (Map.Entry<String, String> entry : charCode.entrySet()){
				writer.println(entry.getKey() + " " + entry.getValue());
			}
			
		    writer.close();		
			
		    //Encode original input file by replacing each input value by its code  
		    OutputStream bw1 = new FileOutputStream("encoded.bin");
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
			String line;
			int i = 0;
			StringBuilder s2 = new StringBuilder();
			while((line=br.readLine()) !=null && !line.equals(""))
			{
				if(charCode.containsKey(line))
				{
					s2.append(charCode.get(line));
				}
			}
			String s = s2.toString();
			
			while(i<s.length())
			{
				bw1.write((byte)Integer.parseInt(s.substring(i,i+8),2));
				i = i+8;
			}
			bw1.close();			
		} catch (IOException e) {
		}

	}

}
