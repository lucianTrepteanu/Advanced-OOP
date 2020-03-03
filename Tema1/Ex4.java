import java.util.Scanner; 


public class Ex4
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args){
        System.out.println("Introduceti valoarea:");
        int n=sc.nextInt();
        
        
        int fact=1;
        for(int i=1;i<=n;i++){
            fact=fact*i;
        }
        System.out.println("Factorialul este "+fact);
        
        System.out.println("");
        sc.close();
    }
}
