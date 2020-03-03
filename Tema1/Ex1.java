import java.util.Scanner; 


public class Ex1
{
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Introduceti valoarea:");
        int n=sc.nextInt();
        
        for(int i=0;i<=n;i+=2){
            System.out.print(i+" ");
        }
        System.out.println("");
        sc.close();
    }
}
