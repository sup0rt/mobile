void main(){
  checkDate("22.12.2025");
}

class DateTimeParseException implements Exception {
  final String message;
  DateTimeParseException(this.message);

  @override
  String toString() {
    return "DateTimeParseException: $message";
  }
}

void checkDate(String date) {
  final parts = date.split('.');
  if(parts.length != 3) {
    throw DateTimeParseException("Invalid date format");
  }

  try {
    final day = int.parse(parts[0]);
    final month = int.parse(parts[1]);
    final year = int.parse(parts[2]);

    if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1000 || year > 9999) {
      throw DateTimeParseException("Invalid date format or value");
    }
  } on FormatException {
    throw DateTimeParseException("Invalid date format");
  }
}