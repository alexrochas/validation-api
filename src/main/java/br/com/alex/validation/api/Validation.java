package br.com.alex.validation.api;

import java.util.function.Function;
import java.util.function.Predicate;

class Validation<T> {

    private final Predicate predicate;
    private final Boolean continueIfFail;
    private final Function<T, ? extends Exception> ifFail;

    protected Validation(Predicate predicate,
                      Boolean continueIfFail,
                      Function<T, ? extends Exception> ifFail) {
        this.predicate = predicate;
        this.continueIfFail = continueIfFail;
        this.ifFail = ifFail;
    }

    public Predicate getPredicate() {
        return predicate;
    }

    public Function<T, ? extends Exception> getIfFail() {
        return ifFail;
    }

    public Boolean getContinueIfFail() {
        return continueIfFail;
    }
}
