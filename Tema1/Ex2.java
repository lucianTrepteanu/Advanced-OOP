import java.util.Scanner; 


public class Ex2
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args){
        System.out.println("Introduceti valoarea a:");
        int a=sc.nextInt();
        
        System.out.println("Introduceti valoarea b:");
        int b=sc.nextInt();
        
        if(a<b){
            System.out.println(b+" este numarul mai mare");
        } else {
            System.out.println(a+" este numarul mai mare");
        }
        System.out.println("");
        
        sc.close();
    }
}
