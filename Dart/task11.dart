void main(){
  print(decimalToBinary(2));
}

String decimalToBinary(int number) {
  if (number < 0) {
    throw ArgumentError("Number cannot be negative");
  }
  return number.toRadixString(2);
}