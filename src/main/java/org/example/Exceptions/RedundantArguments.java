package org.example.Exceptions;

public class RedundantArguments extends RuntimeException {
    public RedundantArguments() { }
    public String printProblem(String[] args) {return "Символов после '" + args[0] + "' слишком много.";}
}
