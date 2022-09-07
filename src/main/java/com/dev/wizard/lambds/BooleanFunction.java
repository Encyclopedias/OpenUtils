package com.dev.wizard.lambds;

import java.util.function.Supplier;

@FunctionalInterface
public interface BooleanFunction<T> {
    T handleCondition(Supplier<? super T> ifFunction, Supplier<? super T> elseFunction);
}
