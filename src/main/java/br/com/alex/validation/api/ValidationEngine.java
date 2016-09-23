package br.com.alex.validation.api;

import java.util.ArrayList;
import java.util.List;

class ValidationEngine<T> {

    private List<Validation> validations;

    private ValidationEngine(List<Validation> validations) {
        this.validations = validations;
    }

    public static ValidationEngine withValidations(List<Validation> validations) {
        return new ValidationEngine<>(validations);
    }

    public List<Exception> validate(T toValidate) throws Throwable {
        List<Exception> exceptions = new ArrayList<>();

        for(Validation validation : validations) {
            if(!validation.getPredicate().test(toValidate) && validation.getContinueIfFail()) {
                exceptions.add((Exception) validation.getIfFail().apply(toValidate));
            } else if(!validation.getPredicate().test(toValidate) && !validation.getContinueIfFail()) {
                throw (Throwable) validation.getIfFail().apply(toValidate);
            }
        }

        return exceptions;
    }

}
