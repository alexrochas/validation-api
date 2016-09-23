package br.com.alex.validation.api;

import java.util.function.Function;
import java.util.function.Predicate;

class ValidationBuilder {
    private Predicate predicate;
    private Boolean continueIfFail;
    private Function ifFail;

    private ValidationBuilder(Predicate predicate){
        this.predicate = predicate;
        this.continueIfFail = false;
        this.ifFail = (fail) -> new RuntimeException();
    }

    public static ValidationBuilder validation(Predicate predicate) {
        return new ValidationBuilder(predicate);
    }

    public ValidationBuilder continueIfFail(Boolean continueIfFail) {
        this.continueIfFail = continueIfFail;
        return this;
    }

    public ValidationBuilder ifFail(Function ifFail) {
        this.ifFail = ifFail;
        return this;
    }

    public Validation build() {
        return new Validation(predicate, continueIfFail, ifFail);
    }
}
