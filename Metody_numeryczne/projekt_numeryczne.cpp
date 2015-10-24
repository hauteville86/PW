#include <cstdlib>
#include <cmath>
#include <iostream>

using namespace std;

double dividend(double x)
{
  return abs(x - 7.0);
}

double divisor(double x)
{
  return abs(x - 2.5) + x;
}

double y_from_x(double x)
{
  return dividend(x) / divisor(x);
}

double equidistant_nodes[] = {3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0};

double chebyshev_formula(double i, double n)
{
  return cos(M_PI * ((2*i+1)/(2*n+2)));
}

double chebyshev_node(double i, double n, double a, double b)
{
  double first_part = (b-a)/2;
  double last_part = (b+a)/2;
  return first_part * chebyshev_formula(i, n) + last_part;
}

int main()
{
  cout << "For equidistant nodes: " << endl;
  for (int i = 0; i < 10; i++)
  {
    double node = equidistant_nodes[i];
    cout << "Node: " << node  << " : " << y_from_x(node) << endl;
  }
  cout << "For Chebyshev nodes: " << endl;
  for (int i = 0; i < 10; i++)
  {
    double node = chebyshev_node(i, 9, equidistant_nodes[0], equidistant_nodes[9]);
    cout << "Node: " << node  << " : " << y_from_x(node) << endl;
  }
  return 0;
}
