import 'dart:io';

void main(){
  print(celsiusToFahrenheit(1));
}

double celsiusToFahrenheit(double celsius) {
  if (celsius < -273.15) {
    throw ArgumentError("Temperature cannot be below absolute zero");
  }
  return (celsius * 9 / 5) + 32;
}