import java.util.Scanner; 


public class Ex3
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args){
        System.out.println("Introduceti valoarea:");
        int n=sc.nextInt();
       
        System.out.println("Divizorii numarului sunt: ");
        for(int i=1;i<=n;i++){
            if(n%i==0){
                System.out.print(i+" ");
            }
        }
        System.out.println("");
        
        sc.close();
    }
}
