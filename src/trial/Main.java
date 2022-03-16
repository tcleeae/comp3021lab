package trial;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    String keywords = "java or LAB attendance OR SESSION";
    
    String search[] = keywords.toLowerCase().split(" ");
    
    for (int i = 0; i < search.length; i++) {
    	//System.out.println(search[i]);
    	if (search[i].equals("or")) {
    		System.out.println(search[i-1] + search[i+1]);
				i++;
			
		
    	}
    	if ( i+1 < search.length) {
		if(!search[i+1].equals("or")) {
			System.out.println(search[i] + search[i+1]);
			
		}
	}
    }
    
    
    
////    String str[] = {" ", " ", " "};
////	int j = 0;
////	
////    for (int i = 0; i < search.length; i++) {
////    	if (search[i].equals("or")) {
////    		str[j] = search[i-1] + search[i+1];
////    		j++;
////			i++;
////    	}
////    }
////    for (String s : str) {
////    	System.out.println(s);
////    }
//    
    ArrayList<String> str = new ArrayList<String>();
	//String str[] = null;
	//int j = 0;
	
	for (int i = 0; i < search.length; i++) {
    	if (search[i].equals("or")) {
    		str.add(search[i-1] + search[i+1]);
    		//j++;
			i++;
    	}
    }
	
	for (String s : str) {
    	System.out.println(s);
    }
    
	System.out.println(str.get(0).substring(0, 4) + str.get(1).substring(0, 10) + str.get(1).substring(10, 17));
    
  } 
}
