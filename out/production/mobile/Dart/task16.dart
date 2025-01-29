void main(){
  print(concatenateStrings("hel", "lo"));
}

String concatenateStrings(String? str1, String? str2) {
  if (str1 == null || str2 == null) {
    throw ArgumentError("Strings cannot be null"); // Throw an exception
  }
  return str1 + str2;
}