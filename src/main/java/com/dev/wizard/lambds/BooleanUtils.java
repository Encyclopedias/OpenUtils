package com.dev.wizard.lambds;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class BooleanUtils<T> {
    private final boolean result;

    public BooleanUtils(boolean result) {
        this.result = result;
    }

    public static <T> BooleanUtils<T> isAnyMatch(Boolean... booleans) {
        boolean result = Arrays.stream(booleans).filter(Objects::nonNull).anyMatch(Boolean::booleanValue);
        return new BooleanUtils<>(result);
    }

    public static <T> BooleanUtils<T> isAllMatch(Boolean... booleans) {
        boolean result = Arrays.stream(booleans).filter(Objects::nonNull).allMatch(Boolean::booleanValue);
        return new BooleanUtils<>(result);
    }

    public static <T> BooleanUtils<T> isAllMatch(BooleanSupplier... booleanSuppliers) {
        boolean result = Arrays.stream(booleanSuppliers).filter(Objects::nonNull).map(BooleanSupplier::getAsBoolean).allMatch(Boolean::booleanValue);
        return new BooleanUtils<>(result);
    }

    public static <T> BooleanUtils<T> isAnyMatch(BooleanSupplier... booleanSuppliers) {
        boolean result = Arrays.stream(booleanSuppliers).filter(Objects::nonNull).map(BooleanSupplier::getAsBoolean).anyMatch(Boolean::booleanValue);
        return new BooleanUtils<>(result);
    }
    public T handleConditionWithReturn(Supplier<T> ifSupplier, Supplier<T> elseSupplier) {
        if (this.result && null != ifSupplier) {
            return ifSupplier.get();
        }
        if (null == elseSupplier) {
            return null;
        }
        return elseSupplier.get();
    }

    public T handleConditionWithReturn(Supplier<T> ifSupplier) {
        if (this.result && null != ifSupplier) {
            return ifSupplier.get();
        }
        return null;
    }

    public void handleCondition(Runnable ifRunnable, Runnable elseConsumer) {
        if (this.result && null != ifRunnable) {
            ifRunnable.run();
            return;
        }
        if (null == elseConsumer) {
            return;
        }
        elseConsumer.run();
    }

    public void handleCondition(Runnable ifRunnable) {
        if (this.result && null != ifRunnable) {
            ifRunnable.run();
        }
    }

    public static void main(String[] args) {
//
//        BooleanUtils.isAnyMatch(true, false)
//                .handleCondition(() -> System.out.println("true return"));
//        BooleanUtils.isAnyMatch(false)
//                .handleCondition(()-> System.out.println("true return"), ()-> System.out.println("false return"));
//
//        String result1 = BooleanUtils.<String>isAllMatch(true).handleConditionWithReturn(()-> "true with return");
//        System.out.println(result1);
//        String result2 = BooleanUtils.<String>isAllMatch(false).handleConditionWithReturn(()-> "true with return");
//        System.out.println(result2);
//
//        String result3 = BooleanUtils.<String>isAllMatch(false)
//                .handleConditionWithReturn(()-> "true with return", ()-> "false with return");
//        System.out.println(result3);


        BooleanUtils.isAnyMatch(() -> true, ()-> {
            System.out.println("I am booleanSuppliers");
            return false;
        }).handleCondition(()-> System.out.println("hhhh"));

        BooleanUtils.isAnyMatch(true, getBooleanResult()).handleCondition(() -> System.out.println("tttt"));

    }

    private static boolean getBooleanResult(){
        System.out.println("getBooleanResult");
        return false;
    }
}
