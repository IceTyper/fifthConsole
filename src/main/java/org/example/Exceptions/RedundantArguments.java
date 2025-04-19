package org.example.Exceptions;

public class RedundantArguments extends RuntimeException {
    public RedundantArguments() { }
    public String printProblem(String[] args) {return "Что за бред вы вводите, "  +
            "я даже чисто из принципа это обрабатывать не буду, " +
            "даже если команду '" + args[0] + "' вы написали правильно.";}
}
