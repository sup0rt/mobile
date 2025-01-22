import 'dart:math';

void main(){
  print(findSquareRoot(16));
}

double findSquareRoot(double number) {
  if (number < 0) {
    throw ArgumentError("Cannot find square root of a negative number.");
  }
  return sqrt(number);
}