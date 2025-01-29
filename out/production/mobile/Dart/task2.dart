import 'dart:math';

void main(){
  print(divideNumbers(10, 5));
}

double divideNumbers(double dividend, double divisor) {
  if (divisor == 0) {
    throw EqualNumberException("Cannot divide by zero");
  }
  return dividend / divisor;
}

class EqualNumberException implements Exception {
  String message;
  EqualNumberException(this.message);

  @override
  String toString() {
    return "EqualNumberException: $message";
  }
}