void main(){
  checkPasswordComplexity("password");
}

void checkPasswordComplexity(String password) {
  if (password.length < 8) {
    throw WeakPasswordException("Password must be at least 8 characters long");
  }
}

class WeakPasswordException implements Exception {
  String message;
  WeakPasswordException(this.message);

  @override
  String toString() {
    return "WeakPasswordException: $message";
  }
}