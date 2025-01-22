void main(){
  print(truncateString("aaaaa", 4));
}

String truncateString(String str, int length) {
  if (length > str.length) {
    throw ArgumentError("Truncation length is greater than string length");
  }
  return str.substring(0, length);
}