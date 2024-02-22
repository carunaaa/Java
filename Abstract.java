/*
 create an abstract class Calculation which has one normal method int add(int x, int y) which will calculate the 
sum of x and y and return the results and one abstract method int mul (int x, int y) that performs multiplication.
create a class Demo that inherit abstract class that implement all the abstract method, and it have its own method 
in calcDiv (int x, int y) that calculates the division between x and y  and return the result. now create the object
of Demo and demonstrate the above scenario.  after this access the method of abstract class using reference of abstract
class.
 */

import java.util.Scanner;

// Abstract class Calculation
abstract class Calculation {

    // Normal method for addition
    public int add(int x, int y) {
        return x + y;
    }

    // Abstract method for multiplication
    public abstract int mul(int x, int y);
}

// Concrete class Demo inheriting from Calculation
class Demo extends Calculation {

    // Implementation of the abstract method for multiplication
    @Override
    public int mul(int x, int y) {
        return x * y;
    }

    // New method for division
    public int calcDiv(int x, int y) {
        if (y != 0) {
            return x / y;
        } else {
            System.out.println("Cannot divide by zero!");
            return 0;
        }
    }
}

public class Abstract {
    public static void main(String[] args) {

        // Creating an object of Demo class
        Demo demoObj = new Demo();

        Scanner scanner = new Scanner(System.in);

        // Getting user input for addition
        System.out.print("Enter first number for addition: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter second number for addition: ");
        int num2 = scanner.nextInt();
        System.out.println("Sum: " + demoObj.add(num1, num2));

        // Getting user input for multiplication
        System.out.print("Enter first number for multiplication: ");
        int num3 = scanner.nextInt();
        System.out.print("Enter second number for multiplication: ");
        int num4 = scanner.nextInt();
        System.out.println("Multiplication: " + demoObj.mul(num3, num4));

        // Getting user input for division
        System.out.print("Enter numerator for division: ");
        int numerator = scanner.nextInt();
        System.out.print("Enter denominator for division: ");
        int denominator = scanner.nextInt();
        System.out.println("Division: " + demoObj.calcDiv(numerator, denominator));

        // Closing the scanner
        scanner.close();
        
        // Accessing abstract class method using the reference of the abstract class
        Calculation calculationRef = demoObj;
        System.out.println("Using abstract class reference - Multiplication: " + calculationRef.mul(6, 7));
    }
}

