import java.util.Scanner; 


public class Ex7
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args){
        System.out.println("Introduceti valoarea:");
        int n=sc.nextInt();
        
        int a=1;
        int b=1;
        int c=0;
        
        if(n==1 || n==2){
            System.out.println("Al "+n+"-lea termen Fibonacci este: 1");
            return;
        }
        
        for(int i=3;i<=n;i++){
            c=a+b;
            a=b;
            b=c;
        }
        
        System.out.println("Al "+n+"-lea termen Fibonacci este: "+c);
        
        System.out.println("");
        sc.close();
    }
}
