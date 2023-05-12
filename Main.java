import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);

        // Taking folder as input and printing Avogadro's Number
        System.out.println("PLease Enter Folder Name : ");
        String file = input.next();

        double Avogadro = new Avogadro().getAvogadro(file);
        System.out.println("My Avogadro Number is : "+Avogadro);
}
}