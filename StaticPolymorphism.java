//Static Polymorphism (Method Overloading)
import java.util.Scanner;

public class StaticPolymorphism {

    // Method with two int parameters
    public int add(int a, int b) {
        return a + b;
    }

    // Method with three int parameters
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method with two double parameters
    public double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StaticPolymorphism example = new StaticPolymorphism();

        System.out.print("Enter two integers (space-separated): ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        System.out.println("Sum of two integers: " + example.add(num1, num2));

        System.out.print("Enter three integers (space-separated): ");
        int num3 = scanner.nextInt();
        int num4 = scanner.nextInt();
        int num5 = scanner.nextInt();
        System.out.println("Sum of three integers: " + example.add(num3, num4, num5));

        System.out.print("Enter two doubles (space-separated): ");
        double double1 = scanner.nextDouble();
        double double2 = scanner.nextDouble();
        System.out.println("Sum of two doubles: " + example.add(double1, double2));

        scanner.close();
    }
}
