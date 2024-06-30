/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons;

import com.wellnr.commons.functions.Function1;
import com.wellnr.commons.functions.Function2;
import com.wellnr.commons.markup.Tuple;
import com.wellnr.commons.markup.Tuple2;

import java.util.*;

public final class CollectionOperators {

    private CollectionOperators() {
    }

    /**
     * This method returns a mutable list. If the list is null, a new list is created.
     * If the list is not null, a copy of the list is returned.
     *
     * @param list The list which should be mutable.
     * @param <T>  The type of the elements in the list.
     * @return A mutable list.
     */
    public static <T> List<T> createMutableList(List<T> list) {
        if (Objects.isNull(list)) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(list);
        }
    }

    /**
     * This method returns a mutable map. If the map is null, a new map is created.
     * If the map is not null, a copy of the list is returned.
     *
     * @param map The map which should be mutable.
     * @param <K> The key type of the map.
     * @param <V> The value type of the map.
     * @return A mutable map.
     */
    public static <K, V> Map<K, V> createMutableMap(Map<K, V> map) {
        if (Objects.isNull(map)) {
            return new HashMap<>();
        } else {
            return new HashMap<>(map);
        }
    }

    /**
     * This method returns a mutable set. If the set is null, a new set is created.
     * If the set is not null, a copy of the set is returned.
     *
     * @param set The set which should be mutable.
     * @param <T> The type of the elements in the set.
     * @return A mutable set.
     */
    public static <T> Set<T> createMutableSet(Set<T> set) {
        if (Objects.isNull(set)) {
            return new HashSet<>();
        } else {
            return new HashSet<>(set);
        }
    }

    /**
     * Concat a set of lists into a single list.
     *
     * @param lists The lists to be concatenated.
     * @param <T>   The type of the elements in the lists.
     * @return The concatenated list.
     */
    @SafeVarargs
    public static <T> List<T> concat(Collection<T>... lists) {
        var result = new ArrayList<T>();

        for (var list : lists) {
            result.addAll(list);
        }

        return List.copyOf(result);
    }

    /**
     * Creates an immutable list from a collection. The collection can be null. If the collection is null,
     * an empty list is returned.
     *
     * @param source The source collection.
     * @param <T>    The type of the elements in the collection.
     * @return An immutable list.
     */
    public static <T> List<T> createImmutableList(Collection<T> source) {
        if (Objects.isNull(source)) {
            return List.of();
        } else {
            return List.copyOf(source);
        }
    }

    /**
     * Helper function to map alist with an index for each element.
     *
     * @param source The source list.
     * @param mapper The function to map the elements with an index.
     * @param <T>    The type of the elements in the source list.
     * @param <U>    The mapped type of the elements in the result list.
     * @return The mapped list.
     */
    public static <T, U> List<U> mapWithIndex(List<T> source, Function2<T, Integer, U> mapper) {
        var result = new ArrayList<U>();
        for (var i = 0; i < source.size(); i++) {
            result.add(mapper.get(source.get(i), i));
        }
        return List.copyOf(result);
    }

    /**
     * Syntactic sugar to map a list of values to a list of other values.
     *
     * @param source The source list.
     * @param mapper The mapper function.
     * @param <T>    The type of the elements in the source list.
     * @param <U>    The mapped type of the elements in the result list.
     * @return The mapped list.
     */
    public static <T, U> List<U> map(Collection<T> source, Function1<T, U> mapper) {
        return source.stream().map(mapper::get).toList();
    }

    /**
     * Creates a list index assigned to element from a list.
     *
     * @param source The source list.
     * @param <T>    The type of the elements in the source list.
     * @return The list index assigned to element from a list.
     */
    public static <T> List<Tuple2<Integer, T>> zipWithIndex(List<T> source) {
        return mapWithIndex(source, (item, idx) -> Tuple.apply(idx, item));
    }

}
