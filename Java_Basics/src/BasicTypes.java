public class BasicTypes
{
    public void main (String[] args)
    {
        //using primitive types int and double
        int breadPrice = 5;
        double candyPrice = 10.5;

        //Operations on variables
        double result = breadPrice + candyPrice;

        System.out.println("Result for adding " + breadPrice + " + " + candyPrice + " = " + result);
        System.out.println("Result for multiplying " + breadPrice + " * " + candyPrice + " = " +  breadPrice * candyPrice);

        //Math functions

        int min = 10;
        int max = 100;
        double rnd = min + Math.random() * (max - min);
        System.out.printf("Random number between %d and  %d : %.2f \n", min, max, rnd);

        double radius = 6.4;
        System.out.printf("Area of a circle with radius %.2f: %.4f \n", radius, Math.PI * Math.pow(radius, 2));

        int number = 64;
        System.out.println("Square root of " + number + " : " + Math.sqrt(number) + " Cube root " + Math.cbrt(number));


        //using String
        String A = " MesSage to tHe goveRnmentA     ";
        String B = " Message to the governmentB ";
        String equalA = " MesSage to tHe goveRnmentA     ";

        if (A == B){
            System.out.println("No chance");
        }
        else if (A == equalA)
            System.out.println("This can be true because of String Pool");
        if (A.equals(equalA))
            System.out.println("Yeah, that works");

        //String methods
        int index = 5;
        System.out.println("Stripped string " + A.strip() + " of length " + A.length() + " has '" + A.charAt(index) + "' character at " + index + " position");
        System.out.println("To lower case: " + A.toLowerCase());
        System.out.println("To upper case: " + A.toUpperCase());
        int count = 3;
        System.out.println("Repeated string B 3 times" + B.repeat(count));
    }
}
