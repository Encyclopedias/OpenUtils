package com.dev.wizard.lambds;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class BooleanUtils {
    public static BooleanFunction isAnyMatch(Boolean... booleans) {
        boolean result = Arrays.stream(booleans).filter(Objects::nonNull).anyMatch(Boolean::booleanValue);
        return getBooleanFunction(result);
    }

    public static BooleanFunction isAllMatch(Boolean... booleans){
        boolean result = Arrays.stream(booleans).filter(Objects::nonNull).allMatch(Boolean::booleanValue);
        return getBooleanFunction(result);
    }

    public static BooleanFunction isAllMatch(BooleanSupplier... booleanSuppliers){
        boolean result = Arrays.stream(booleanSuppliers).filter(Objects::nonNull).map(BooleanSupplier::getAsBoolean).allMatch(Boolean::booleanValue);
        return getBooleanFunction(result);
    }

    public static BooleanFunction isAnyMatch(BooleanSupplier... booleanSuppliers) {
        boolean result = Arrays.stream(booleanSuppliers).filter(Objects::nonNull).map(BooleanSupplier::getAsBoolean).anyMatch(Boolean::booleanValue);
        return getBooleanFunction(result);
    }

    private static BooleanFunction getBooleanFunction(boolean result){
        return (ifFunction, elseFunction) -> result ? ifFunction.get() : Optional.ofNullable(elseFunction).map(Supplier::get).orElse(null);
    }


    public static void main(String[] args) {

        String result =  (String)BooleanUtils.isAnyMatch(() ->"hh".equals("hh")).handleCondition(
                () -> "if function",
                null
        );

        System.out.println(result);

    }
}
