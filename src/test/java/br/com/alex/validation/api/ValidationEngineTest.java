package br.com.alex.validation.api;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationEngineTest {

    private List<Validation> validations;

    @Before
    public void setup() {
        this.validations = new ArrayList<>();

        validations.add(ValidationBuilder
                .validation(toValidate -> ((String) toValidate).contains("A"))
                .continueIfFail(true)
                .ifFail(f -> new IllegalArgumentException())
                .build());
         validations.add(ValidationBuilder
                .validation(toValidate -> !toValidate.equals("Break!"))
                .continueIfFail(false)
                .ifFail(f -> new IllegalArgumentException())
                .build());
    }

    @Test
    public void shouldRunValidationEngineWithValidations() throws Throwable {
        List<Exception> exceptions = ValidationEngine
                .withValidations(validations)
                .validate("Alex");

        assertTrue(exceptions.isEmpty());
    }

    @Test
    public void shouldFailValidationEngineWithValidations() throws Throwable {
        List<Exception> exceptions = ValidationEngine
                .withValidations(validations)
                .validate("Should return error");

        assertFalse(exceptions.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionBecauseShouldNotContinue() throws Throwable {
        ValidationEngine.withValidations(validations).validate("Break!");
    }
}

