void main(){
  List<int> numbers = [1, 2, 3, 4, 5];

  checkForZeros(numbers);
}

void checkForZeros(List<int> list) {
  if (list.contains(0)) {
    throw Exception("Array contains zero");
  }
  else{
    print("No zeros found");
  }
}