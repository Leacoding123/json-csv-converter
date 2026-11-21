package com.lea.converter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataValidatorTest {

    @Test
    void validCsvShouldPass() {

        List<String[]> rows = new ArrayList<>();

        rows.add(new String[]{"name", "email", "age"});
        rows.add(new String[]{"Alice", "alice@test.com", "22"});
        rows.add(new String[]{"Bob", "bob@test.com", "30"});

        assertDoesNotThrow(
                () -> DataValidator.validateCsvRows(rows)
        );
    }

    @Test
    void inconsistentCsvShouldThrowException() {

        List<String[]> rows = new ArrayList<>();

        rows.add(new String[]{"name", "email", "age"});
        rows.add(new String[]{"Alice", "alice@test.com", "22"});
        rows.add(new String[]{"Bob", "bob@test.com"});

        assertThrows(
                ConversionException.class,
                () -> DataValidator.validateCsvRows(rows)
        );
    }

    @Test
    void emptyCsvShouldThrowException() {

        List<String[]> rows = new ArrayList<>();

        assertThrows(
                ConversionException.class,
                () -> DataValidator.validateCsvRows(rows)
        );
    }
}