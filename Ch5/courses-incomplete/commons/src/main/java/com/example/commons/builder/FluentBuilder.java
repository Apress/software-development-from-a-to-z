package com.example.commons.builder;

/**
 * Interface to build objects in a
 * fluent fashion.
 *
 * @param <T> The type of object being built.
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface FluentBuilder<T> {
    /**
     * Builds the object.
     *
     * @return The object.
     */
    T build();
}
