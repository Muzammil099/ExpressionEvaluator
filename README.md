# Expression Calculator

This project is an Expression Calculator implemented in Java. It supports basic arithmetic operations such as addition, subtraction, multiplication, division, and more.

## Overview

The calculator evaluates mathematical expressions provided in infix notation. It includes a flexible configuration mechanism for defining the precedence and associativity of operators. The configuration is loaded from an external file to allow easy adjustments without modifying the code.

## Development

1- If there any new operation need to be added, it just require to create a new class than inherits Binary or Unary operator class as per operator nature and change in operator factory for defining approiate symbol for that operator.
2- If there is any change required in setting the precedence or associativity of any defined operator then it can be set from operator-config.properties file
   Example:
      a.precedence=1
      a.associativity=LEFT
      s.precedence=2
      s.associativity=LEFT

