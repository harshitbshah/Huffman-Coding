import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PairHeap {
	
HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	
	public class HuffmanNodes{
		int data;
		int freq;
		HuffmanNodes left;
		HuffmanNodes right;
		
		HuffmanNodes(int data,int freq){
			this.data=data;
			this.freq=freq;
			this.left=null;
			this.right=null;
		}
	}
	
	
	public class pairingHeapDataFreq {
		
		
		private pairNode root=null;
		private pairNode minPointer=null; 
		private int size=0;
		
		
		class pairNode{
		    int freq;
		    int data;
		    pairNode leftSibling;
		    pairNode rightSibling;
		    pairNode child;
		    HuffmanNodes hnodes;
		    public pairNode(HuffmanNodes hnodes){
		    	this.leftSibling=null;
		    	this.rightSibling=null;
		    	this.child=null;
		    	this.data=hnodes.data;
		    	this.freq=hnodes.freq;
		    	this.hnodes=hnodes;
		    }   
		}
		
		 public int size( )
		    {
		        return this.size;
		    }
		
		public void insertKey(HuffmanNodes hnodes){
			pairNode newNode=new pairNode(hnodes);
			this.size++;
			if(minPointer==null){
				root=newNode;
				minPointer=newNode;
			}else{
			    compareLink(minPointer,newNode);
			}
		}
		
		public pairNode compareLink(pairNode first,pairNode second){
			if(second == null){
				return first;
			}else if(second.freq>=first.freq && first.child==null){
				first.child=second;
	 			second.leftSibling=first;
				minPointer=first;
	 			return first;
			}else if(second.freq>=first.freq && first.child!=null){
				second.leftSibling=first;
				second.rightSibling=first.child;
				first.child.leftSibling=second;
				first.child=second;
				minPointer=first;
				return first;
			}else if(first.freq>second.freq && second.child==null){
				second.child=first;
				first.leftSibling=second;
				root=second;
				minPointer=second;
				return second;
			}else if(first.freq>second.freq && second.child!=null){
				first.rightSibling=second.child;
				second.child.leftSibling=first;
				second.child=first;
				first.leftSibling=second;
				root=second;
				minPointer=second;
				return second;
			}
			System.out.println(first.freq+" "+second.freq+" "+second.child);
			return null;
		
		}
		
		public HuffmanNodes extractMin(){
			pairNode minimum=minPointer;
		    int count=0;
		    int k=0;
		    this.size--;
		    pairNode child=minPointer.child;
		    pairNode temp=child;
		    
		    minPointer.child=null;

		    while(temp!=null){
		    	temp=temp.rightSibling;
		    	count=count+1;
		    }
		    
		   pairNode pt[]=new pairNode[count];
		   temp=child;
		   
		   for(int i=0;i<count;i++){
			   pt[i]=temp;
		       temp=temp.rightSibling;
		       pt[i].rightSibling=null;
		       pt[i].leftSibling=null;
		   }
		   
		   for( k=0; k + 1 < count; k += 2 ){   
			   pt[ k ] = compareLink( pt[ k ], pt[ k + 1 ] );
		   }
		   
	       int j = k - 2;
	       if( j == count - 3 && count!=1)
	    	   pt[ j ] = compareLink( pt[ j ], pt[ j + 2 ] );
	       for( ; j >= 2; j -= 2 ){
	    	   pt[ j - 2 ] = compareLink( pt[ j - 2 ], pt[ j ] );
		   }
	       
	       
	       if(count==1){
		    	minPointer=pt[0];
		    }
		   
		   return minimum.hnodes;
		}
		
		void HuffmanCodes(int arr[]){
			pairingHeapDataFreq bhwf=new pairingHeapDataFreq(); 
	    	for(int i=0;i<arr.length;i++){
	    		if(arr[i]!=0){
	    			HuffmanNodes hnodes2=new HuffmanNodes(i,arr[i]);
		    		bhwf.insertKey(hnodes2);
	    		}	
	    	}
	    	
	    	while(bhwf.size()!=1){
	    		HuffmanNodes left=bhwf.extractMin();
	    		HuffmanNodes right=bhwf.extractMin();
	    		HuffmanNodes top= new HuffmanNodes(Integer.MIN_VALUE,left.freq+right.freq);
	    		top.left=left;
	    		top.right=right;
	    		if(bhwf.size()==0){
	    			bhwf.minPointer=null;
	    		}
	    		bhwf.insertKey(top);
	    	}
	    }   
	}
    
    void buildHuffmanTrees(int arr[]){
    	pairingHeapDataFreq bhwf=new pairingHeapDataFreq();
    	bhwf.HuffmanCodes(arr);	
    }
	
	public void buildHuffmanTree() {
		// TODO Auto-generated method stub
		PairHeap hcbh=new PairHeap();
		int arr[]=new int[10000000];
		ArrayList <Integer> fileContentarr=new ArrayList<Integer>();
		int i=0;
		Arrays.fill(arr, 0);	

			String FILENAME="sample_input_large.txt";
			String currentLine;
			
			try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
				while ((currentLine = br.readLine()) != null && !currentLine.equals("")) {
					int value=Integer.parseInt(currentLine);
					arr[value]=arr[value]+1;
					fileContentarr.add(value);
				}
				long startTime = System.nanoTime();
				for(i=0;i<10;i++){
					PairHeap hcbh1=new PairHeap();
					hcbh1.buildHuffmanTrees(arr);
				}
				long stopTime = System.nanoTime();
				System.out.println("Time using pairing heap (nanoseconds): " + (stopTime - startTime)/ 10);
				
			}catch (IOException e) {
					e.printStackTrace();
		    }
	}
}
