/*
Practice how declaration and assignments of primitives works.

This program will first create two variables to store how many bags and how many boxes we have. Then, it will create three additional different variables:
one to store the weight of each box, one to store the weight of each bag, and one to store the total weight of all our boxes and bags combined.
 
This total weight will be shown on the terminal as a result.
*/
public class Primitives
{
  
  public static void main(String[] args)
  {
    
    // Declaration of variables to store: amount of boxes, amount of bags, weight of each bag, weight of each box, and total weight
    // We have two different weights, both of which we want to use later
    int numBoxes = 1;
    int numBags = 2;
    float weightBox = 10.1f;
    float weightBag = 3.7f;
    double totalWeight = 0.0;
    
    // Calculation of total weight
    totalWeight = (numBoxes * weightBox) + (numBags * weightBag);
    
    // Display the combined weight of all our bags and boxes together in two different ways:
    System.out.println("The number of boxes times the weight of the boxes plus the number of bags times the weight of the bags is: ");
    System.out.println((numBoxes * weightBox) + (numBags * weightBag));
    System.out.println(totalWeight);
  }
}
