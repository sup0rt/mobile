void main(){
  List <int> numbers = [1,2,3,4,5];
  int el= 2;

  print(findElement(numbers, el));
}

int findElement(List<int> list, int element) {
  int index = list.indexOf(element);
  if (index == -1) {
    throw StateError("Element not found in array");
  }
  return index;
}