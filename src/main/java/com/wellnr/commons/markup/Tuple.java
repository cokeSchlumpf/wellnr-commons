package com.wellnr.commons.markup;

public final class Tuple {

    private Tuple() {

    }

    public static <T1, T2> Tuple2<T1, T2> apply(T1 _1, T2 _2) {
        return Tuple2.apply(_1, _2);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> apply(T1 _1, T2 _2, T3 _3) {
        return Tuple3.apply(_1, _2, _3);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> apply(T1 _1, T2 _2, T3 _3, T4 _4) {
        return Tuple4.apply(_1, _2, _3, _4);
    }

    public static <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> apply(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5) {
        return Tuple5.apply(_1, _2, _3, _4, _5);
    }

    public static <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> apply(T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6) {
        return Tuple6.apply(_1, _2, _3, _4, _5, _6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> apply(
        T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7
    ) {
        return Tuple7.apply(_1, _2, _3, _4, _5, _6, _7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> apply(
        T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8
    ) {
        return Tuple8.apply(_1, _2, _3, _4, _5, _6, _7, _8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> apply(
        T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9
    ) {
        return Tuple9.apply(_1, _2, _3, _4, _5, _6, _7, _8, _9);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> apply(
        T1 _1, T2 _2, T3 _3, T4 _4, T5 _5, T6 _6, T7 _7, T8 _8, T9 _9, T10 _10
    ) {
        return Tuple10.apply(_1, _2, _3, _4, _5, _6, _7, _8, _9, _10);
    }

}
