
package Tp1;

public class somme {
    public static void main(String[] args) {
        double va=0;
        for (String elem : args) {
            try {
                va+=Double.parseDouble(elem);
            } 
            catch (NumberFormatException e) {
            System.out.println("Error '"+ elem+"' is not a number");
            }
        }  
        System.out.println("Somme = "+va); 
    }
}
