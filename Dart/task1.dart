void main(){
  print(findMax(3,4));
}

int findMax(int a, int b) {
  if (a == b) {
    throw EqualNumberException("Numbers are equal");
    return 0;
  }
  return a > b ? a : b;
}

class EqualNumberException implements Exception {
  String message;
  EqualNumberException(this.message);

  @override
  String toString() {
    return "EqualNumberException: $message";
  }
}