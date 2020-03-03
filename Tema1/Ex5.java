import java.util.Scanner; 


public class Ex5
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args){
        System.out.println("Introduceti valoarea:");
        int n=sc.nextInt();
        
        if(n==1){
            System.out.println(0);
            return;
        }
        if(n==2){
            System.out.println(1);
        }
        
        for(int i=2;i*i<=n;i++){
            if(n%i==0){
                System.out.println(0);
                return;
            }
        }
        
        System.out.println(1);
        
        System.out.println("");
        sc.close();
    }
}
