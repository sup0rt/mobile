import 'dart:io';
import 'dart:math';

void main(){
  print(findSqrt(16));
}
double findSqrt(double num){
  if (num < 0) {
    throw ArgumentError("Cannot calculate square root of negative number");
  }
  return sqrt(num);
}