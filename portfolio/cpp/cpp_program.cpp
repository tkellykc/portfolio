// Timothy Kelly
// This program simulates a cylinder container painting and shipping service. The program prompts the user to input the dimensions of a cylinder and the shipping price per liter of volume. The program then outputs the total shipping price for the cylinder. The program asks the user if they wish to have the container painted. If so, the program outputs the painting price and the final total of service. If painting is not requested, the program outputs the final total without painting.

#include <iostream>
#include <iomanip>

using namespace std;

class cylinderType // declares cylinderType class
{
  public: // initializes public variables for class
    void radiusVal(double rad);
    double getRadius();
    void heightVal(double ht);
    double getHeight();
    double volume();
    double surfaceArea();
    cylinderType(double rad, double ht);
    cylinderType();

  private: // initializes private variables for class
    double radius;
    double height;
};

void cylinderType::radiusVal(double rad) // this function receives and stores the dimension of the cylinder base radius
{
    if (rad >= 0)
        radius = rad;
    else
        radius = 0;
}

double cylinderType::getRadius() // returns radius value
{
    return radius;
}

void cylinderType::heightVal(double ht) // this function receives and stores the dimension of the cylinder height
{
    if (ht >= 0)
        height = ht;
    else
        height = 0;
}

double cylinderType::getHeight() // returns height value
{
    return height;
}

double cylinderType::volume() // this function calculates the cylinder volume
{
    return 3.14 * (radius * radius) * height;
}

double cylinderType::surfaceArea() // this function calculates the cylinder surface area
{
    return (2 * 3.14 * radius * height) + (2 * 3.14 * (radius * radius));
}

cylinderType::cylinderType(double rad, double ht) // class variables
{
    radiusVal(rad);
    heightVal(ht);
}

cylinderType::cylinderType() // class variables
{
    radius = 0;
    height = 0;
}

int main() // program main function
{
    double radius; // cylinder radius value
    double height; // cylinder height value
    double shipPrice; // shipping price amount entered by user
    double shipTotal; // total shipping cost for cylinder
    double paintPrice; // paint price amount entered by user
    double paintTotal; // total pain price for cylinder
    double finalTotal; // final total amount (shipping cost + paint cost)
    char paintRequest; // 'yes' or 'no' for painting input by user

    cylinderType operation;

    cout << "--Welcome to the A & T Container Service Calculator--" << endl << endl; // welcome message
    cout << "Please enter the dimensions of the container (radius and height). " << endl; // explanation for input
    cout << "Radius (in feet): "; // prompts the user to enter radius value
    cin >> radius;
    cout << endl;
    cout << "Height (in feet): "; // prompts the user to enter height value
    cin >> height;
    cout << endl;

    operation.radiusVal(radius);
    operation.heightVal(height);

    cout << "Please enter the shipping price (per liter): "; // prompts user to enter shipping price
    cin >> shipPrice;
    cout << endl;
    shipTotal = shipPrice * operation.volume() * 28.32; // calculates shipping price according to volume
    cout << setprecision(2) << fixed << "Based on the information you provided, the total shipping cost is $" << shipTotal << endl << endl; // outputs shipping price

    while (paintRequest != 'Y' && paintRequest != 'N') // while loop for for painting answer
    {
        cout << "Would you like your cylinder to be painted? Y/N" << endl; // prompts user to enter 'yes' or 'no' for painting
        cin >> paintRequest;
        cout << endl;

        if (paintRequest == 'Y' || paintRequest == 'y') // result for 'yes' answer
        {
            cout << "Please enter the paint cost (per square foot): "; // prompts user to input paint cost
            cin >> paintPrice;
            cout << endl;

            paintTotal = paintPrice * operation.surfaceArea(); // calculates paint price according to surface area
            finalTotal = (shipTotal) + (paintTotal); // calculates final total (shipping + paint)

            cout << setprecision(2) << fixed << "The paint price is $" << paintTotal << endl; // outputs total paint price
            cout << setprecision(2) << fixed << "Your final total for paint and shipping is $" << finalTotal << endl << endl; // outputs final total
            cout << "--Thank you for choosing A & T Container Service--" << endl; // thank-you message
        }
        else if (paintRequest == 'N' || paintRequest == 'n') // result of 'no' answer
        {
            finalTotal = shipTotal; // initializes final total to reflect only shipping price (no painting)
            cout << setprecision(2) << fixed << "Your final total is $" << finalTotal << endl << endl; // outputs final total
            cout << "--Thank you for choosing A & T Container Service--" << endl; // thank-you message
        }

        break;
    }

    return 0;
}
