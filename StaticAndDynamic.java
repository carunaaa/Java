class StaticPoly {
    // Method overloading for static polymorphism
    void polymorphismExample(int x) {
        System.out.println("Using integer: " + x);
    }

    void polymorphismExample(double y) {
        System.out.println("Using double: " + y);
    }
}

// Base class for dynamic polymorphism
class Base {
    // Method for dynamic polymorphism
    void polymorphismExample() {
        System.out.println("Base class implementation");
    }
}

// Derived class overriding the method for dynamic polymorphism
class Derived extends Base {
    @Override
    void polymorphismExample() {
        System.out.println("Derived class implementation");
    }
}

class StaticAndDynamic {
    public static void main(String[] args) {
        // Static Polymorphism
        StaticPoly staticPoly = new StaticPoly();
        staticPoly.polymorphismExample(5);
        staticPoly.polymorphismExample(3.14);

        // Dynamic Polymorphism
        Base baseObj = new Derived(); // Upcasting
        baseObj.polymorphismExample(); // Calls overridden method in Derived class
    }
}