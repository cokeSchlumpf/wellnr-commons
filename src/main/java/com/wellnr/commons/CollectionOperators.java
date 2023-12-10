/*
 * (C) Copyright 2023. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons;

import java.util.*;

public final class CollectionOperators {

    private CollectionOperators() {}

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
}
