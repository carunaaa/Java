abstract class Calculation {
    int add(int x, int y) {
        return x + y;
    }

    abstract int mul(int x, int y);
}

class Demo extends Calculation {
    int mul(int x, int y) {
        return x * y;
    }

    int calcDiv(int x, int y) {
        if (y != 0) {
            return x / y;
        } else {
            System.out.println("Error: Division by zero");
            return 0;
        }
    }
}

public class Abstract {
    public static void main(String[] args) {
        Demo demo = new Demo();

        // Demonstrate add and mul methods from Demo class
        System.out.println("Addition: " + demo.add(5, 3)); // Output: 8
        System.out.println("Multiplication: " + demo.mul(5, 3)); // Output: 15
        System.out.println("Division: " + demo.calcDiv(15, 3)); // Output: 5

        // Access the add method from abstract class using reference of abstract class
        Calculation calculation = new Demo();
        System.out.println("Addition using abstract class reference: " + calculation.add(10, 20)); // Output: 30
    }
} 