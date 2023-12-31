public class IfCheck {

    public static void main(String[] args) {
        
        int number = 10;

        if (number > 5) 
            System.out.println("Number is greater than 5");
        

        if (number < 20) 
            System.out.println("Number is less than 20");
        

        if (number == 10) {
            System.out.println("Number is equal to 10");
        }

        if (number != 15) {
            System.out.println("Number is not equal to 15");
        }

        if (number % 2 == 0) {
            System.out.println("Number is even");
         else 
            System.out.println("Number is odd");
        }


        if (number < 5) {
            System.out.println("Number is less than 5");
        } else if (number >= 5 && number <= 15) 
            System.out.println("Number is between 5 and 15 (inclusive)");
         else {
            System.out.println("Number is greater than 15");
        

        switch (number) 
            case 5:
                System.out.println("Number is exactly 5");
                break;
            case 10:
                System.out.println("Number is exactly 10");
                break;
            case 15:
                System.out.println("Number is exactly 15");
                break;
            default:
                System.out.println("Number is something other than 5, 10, or 15");
        
    }
}