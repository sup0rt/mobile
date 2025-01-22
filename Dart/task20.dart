void main(){
  checkStringNotEmpty(" ");
}

void checkStringNotEmpty(String? str) {
  if (str == null || str.isEmpty) {
    throw ArgumentError("String cannot be null or empty");
  }
}