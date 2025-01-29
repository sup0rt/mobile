import 'dart:math';

void main(){
  print(power(9, 2));
}

double power(double base, int exponent) {
  if (exponent < 0) {
    throw ArgumentError("Exponent cannot be negative");
  }
  return pow(base, exponent).toDouble();
}