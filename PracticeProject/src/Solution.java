import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
	    System.out.println("please enter vale");
		Scanner i = new Scanner(System.in);
		int ip = i.nextInt();
		//findPrimenumbers(ip);
		printPatern(ip);
	    //String s = i.nextLine();
	   // i.close();
	   // reverseString(s);
		
		}
	   public static void findPrimenumbers(int number){
		   
		   for (int i = 1; i <= number; i++) {
			   boolean flag = true;
			if(i ==1 || i== 0){
			//	System.out.println("not a prime");
				flag = false;
			}
			else{
				int m = i/2;
				for (int j = 2; j <=m ; j++) {
					if(i%j ==0){
						flag = false;
						break;
					}
				}
			}
			if(flag)
				System.out.println(i+",");
		}
	   }
	   
	   public static void reverseString(String str){
		   String reverse = "";
		   for (int i = str.length() - 1;i >= 0 ;i--) {
			reverse  = reverse + str.charAt(i);
			
		}
		   System.out.println(reverse);
	   }
	   public static void printPatern(int height){
		   int m = height-1;
		   for(int i= 0; i < height; i++){
			   
			   for(int j= 0 ; j <=2*m; j++){
				   if((j == m + i) || j == m - i)
					   System.out.print("*");
				   else
					   System.out.print(" ");
			   }
			   System.out.println("");
		   }
	   }

}
