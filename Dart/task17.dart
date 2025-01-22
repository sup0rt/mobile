void main(){
  print(calculateRemainder(12, 3));
}

int calculateRemainder(int dividend, int divisor) {
  if (divisor == 0) {
    print("Cannot divide by zero");
  }
  return dividend % divisor;
}