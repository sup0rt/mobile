void main(){
  print(checkIfDivisible(13, 2));
}

bool checkIfDivisible(int dividend, int divisor) {
  if (divisor == 0) {
    print("Cannot divide by zero");
  }
  return dividend % divisor == 0;
}