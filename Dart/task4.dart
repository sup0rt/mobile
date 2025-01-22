import 'dart:io';

void main(){
  verifyAge(70);
}

void verifyAge(int age) {
  if (age < 0 || age > 150) {
    throw ArgumentError("Age must be between 0 and 150");
  }
  else{
    stdout.write("Age is ok");
  }
}