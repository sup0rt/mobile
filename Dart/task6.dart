void main(){
  print(factorial(5));
}

int factorial(int n) {
  if (n < 0) {
    throw ArgumentError("Cannot calculate factorial of a negative number");
  }
  if(n == 0 || n == 1){
    return 1;
  }
  int result = 1;
  for (int i = 2; i <= n; i++){
    result *= i;
  }
  return result;
}