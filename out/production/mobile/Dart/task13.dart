void main(){
  List <List> list = [[1,2],[3,4]];
  int index = 1;

  print(getElementAtIndex(list, index));
}

dynamic getElementAtIndex(List list, int index) {
  if (index < 0 || index >= list.length) {
    throw RangeError("Index out of bounds");
  }
  return list[index];
}