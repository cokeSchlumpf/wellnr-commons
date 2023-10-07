package com.wellnr.commons.markup;

import com.wellnr.commons.functions.Function1;
import com.wellnr.commons.functions.Procedure1;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Optional;

@Value
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(staticName = "apply")
class Left<L, R> extends Either<L, R> {

    L value;

    @Override
    public Optional<L> getLeft() {
        return Optional.of(value);
    }

    @Override
    public Optional<R> getRight() {
        return Optional.empty();
    }

    @Override
    public Either<L, R> ifLeft(Procedure1<L> ifLeft) {
        ifLeft.run(value);
        return this;
    }

    @Override
    public Either<L, R> ifRight(Procedure1<R> ifRight) {
        return this;
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public boolean isRight() {
        return false;
    }

    @Override
    public <T> T map(Function1<L, T> mapLeft, Function1<R, T> mapRight) {
        return mapLeft.get(value);
    }

}
