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
class Right<L, R> extends Either<L, R> {

    R value;

    @Override
    public Optional<L> getLeft() {
        return Optional.empty();
    }

    @Override
    public Optional<R> getRight() {
        return Optional.of(value);
    }

    @Override
    public Either<L, R> ifLeft(Procedure1<L> ifLeft) {
        return this;
    }

    @Override
    public Either<L, R> ifRight(Procedure1<R> ifRight) {
        ifRight.run(value);
        return this;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public boolean isRight() {
        return true;
    }

    @Override
    public <T> T map(Function1<L, T> mapLeft, Function1<R, T> mapRight) {
        return mapRight.get(value);
    }

}
