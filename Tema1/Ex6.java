import java.util.Scanner; 


public class Ex6
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args){
        System.out.println("Introduceti valoarea a:");
        int a=sc.nextInt();
        
        System.out.println("Introduceti valoarea b:");
        int b=sc.nextInt();
        
        int res=1;
        for(int i=1;i<=b;i++){
            res=res*a;
        }
        System.out.println(res);
        
        System.out.println("");
        sc.close();
    }
}
