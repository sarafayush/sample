import java.util.Scanner;

public class CognIzantExample {
 public static void main(String[] args) {
	
	 Scanner sc = new Scanner(System.in);
	 int n = sc.nextInt();
	 sc.close();
	 int p =n;
	 int r= 2;
	 int q;
	 for(int i = 1 ; i<=n ; i++){
		 
		 if(p > 0){
			 q = n-p;
			 p = p -2;//2 0
			 if(p == 0)
				 r = 1; 
		 }
		 else{
			 q = n-r;
			 r = r+2;
		 }
		 for(int  j = 1 ; j<=n ; j++){
			 System.out.print((q*n + j) + " ");
		 }
		 System.out.println();
	 }
}
}
