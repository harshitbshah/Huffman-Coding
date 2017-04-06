import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Decoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    try {   	    
	    //Code Table
	    BufferedReader br = new BufferedReader(new FileReader(args[1]));
	    String line;
	    HashMap<String, String> huff = new HashMap<String,String>();
	    while ((line = br.readLine()) != null) {
	        String tmp[] = line.split(" ");
	        huff.put(tmp[0], tmp[1]);
	    }
	    br.close();
	    
	    //Construct Huffman Tree from Code Table
	    BinHeapNode root = new BinHeapNode(null, null);
	    BinHeapNode curr;
	    for (Map.Entry<String, String> entry : huff.entrySet()){
	    	char[] charVal = entry.getValue().toCharArray();
	    	curr = root;
	    	for(int i = 0; i < charVal.length; i++){
	    		
	    		if(charVal[i] == '0'){
	    			if(curr.left != null)
	    				curr = curr.left;
	    			else{
		    			if(i == charVal.length - 1)
		    				curr.left = new BinHeapNode(entry.getKey(), null);
		    			else
	    					curr.left = new BinHeapNode(null, null);
		    			curr = curr.left;
	    			}
	    		}
	    		else{
	    			if(curr.right != null)
		    			curr = curr.right;
		    		else{
		    			if(i == charVal.length - 1)
		    				curr.right = new BinHeapNode(entry.getKey(), null);
		    			else
		    				curr.right = new BinHeapNode(null, null);
		    			curr = curr.right;
		    		}
	    		}
	    		
	    	}
	    }
	    
	    //Decode Binary File			
		byte[] bFile = Files.readAllBytes(Paths.get(args[0]));
		StringBuilder sb = new StringBuilder();
		for (int i=0; i < bFile.length;i++)
		{
			sb.append(String.format("%8s", Integer.toBinaryString(bFile[i] & 0xFF)).replace(' ', '0'));
		}
		String decodedString = sb.toString();
		
		//Traverse through Huffman Tree to decode bits from Encoded Binary File
		BufferedWriter buff = new BufferedWriter(new FileWriter("decoded.txt"));
		int j=0; 
		while(j<decodedString.length())
		{
			BinHeapNode temp = root;
			while(!(temp.left==null && temp.right==null))
			{
				if(decodedString.charAt(j)=='0')
				{
					temp = temp.left;
				}
				else
				{
					temp = temp.right;
				}
				j++;
			}			
			buff.write(temp.data);
			buff.newLine();			
		}
		buff.close();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
