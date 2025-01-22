void main(){
  print(stringToInt("3"));
}

int stringToInt(String str) {
  try {
    return int.parse(str);
  } catch (e) {
    throw FormatException("Cannot convert string to integer");
  }
}