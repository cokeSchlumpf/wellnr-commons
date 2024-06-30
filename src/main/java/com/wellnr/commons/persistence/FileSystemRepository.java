/*
 * (C) Copyright 2024. Licensed under the Apache License, Version 2.0.
 * Author: Michael Wellner (https://github.com/cokeSchlumpf/).
 */
package com.wellnr.commons.persistence;

import com.wellnr.commons.Operators;
import com.wellnr.commons.StringOperators;
import com.wellnr.commons.functions.Function1;
import com.wellnr.commons.functions.Procedure2;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 * A generic file system repository implementation.
 *
 * @param <T> the type of the entity managed by the repository.
 */
@Slf4j
public class FileSystemRepository<T> {

    /**
     * The function that returns the id of an entity.
     */
    private final Function1<T, String> getId;

    /**
     * The working directory where the entities are stored.
     */
    private final Path workingDirectory;

    /**
     * The function that reads an entity from a file.
     */
    private final Function1<Path, T> readValue;

    /**
     * The function that writes an entity to a file.
     */
    private final Procedure2<Path, T> writeValue;

    /**
     * The name of the entity type. This value is used to create file-name extensions.
     */
    private final String entityName;

    /**
     * Creates a new file system repository.
     *
     * @param getId            the function that returns the id of an entity.
     * @param workingDirectory The working directory where the entities are stored.
     * @param readValue        The function that reads an entity from a file.
     * @param writeValue       The function that writes an entity to a file.
     * @param entityName       The name of the entity type. This value is used to create file-name extensions.
     * @param directoryName    The name of the directory where the entities are stored (within working directory).
     */
    public FileSystemRepository(
            Function1<T, String> getId,
            Path workingDirectory,
            Function1<Path, T> readValue,
            Procedure2<Path, T> writeValue,
            String entityName,
            String directoryName) {

        this.getId = getId;
        this.workingDirectory = workingDirectory.resolve(directoryName.toLowerCase());
        this.readValue = readValue;
        this.writeValue = writeValue;
        this.entityName = entityName.toLowerCase();

        Operators.suppressExceptions(
                () -> {
                    Files.createDirectories(this.workingDirectory);
                });
    }

    /**
     * Creates a new file system repository.
     *
     * @param getId            the function that returns the id of an entity.
     * @param workingDirectory The working directory where the entities are stored.
     * @param readValue        The function that reads an entity from a file.
     * @param writeValue       The function that writes an entity to a file.
     * @param entityName       The name of the entity type. This value is used to create file-name extensions.
     */
    public FileSystemRepository(
            Function1<T, String> getId,
            Path workingDirectory,
            Function1<Path, T> readValue,
            Procedure2<Path, T> writeValue,
            String entityName) {
        this(
                getId,
                workingDirectory,
                readValue,
                writeValue,
                entityName,
                StringOperators.pluralize(entityName.toLowerCase()));
    }

    /**
     * Creates a new file system repository.
     *
     * @param getId            the function that returns the id of an entity.
     * @param workingDirectory The working directory where the entities are stored.
     * @param readValue        The function that reads an entity from a file.
     * @param writeValue       The function that writes an entity to a file.
     */
    public FileSystemRepository(
            Function1<T, String> getId,
            Path workingDirectory,
            Function1<Path, T> readValue,
            Procedure2<Path, T> writeValue,
            Class<T> entityClass) {
        this(
                getId,
                workingDirectory,
                readValue,
                writeValue,
                StringOperators.camelCaseToKebabCase(entityClass.getSimpleName()));
    }

    public void delete(String id) {
        var file = getEntityPath(id);

        if (Files.exists(file)) {
            Operators.suppressExceptions(() -> Files.delete(file));
        }
    }

    public void delete(T entity) {
        delete(getId.get(entity));
    }

    public List<T> findAll() {
        return Operators.suppressExceptions(() -> Files.list(workingDirectory))
                .filter(p -> p.getFileName().toString().endsWith("." + entityName + ".json"))
                .map(
                        p ->
                                Operators.ignoreExceptionsToOptional(
                                        () -> this.readValue.get(p),
                                        ex ->
                                                log.warn(
                                                        "An exception occurred while reading entity"
                                                                + " form file `{}`.",
                                                        p.getFileName(),
                                                        ex)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<T> findAllBy(Predicate<T> predicate) {
        return findAllByAsStream(predicate).toList();
    }

    public Stream<T> findAllByAsStream(Predicate<T> predicate) {
        return findAll().stream().filter(predicate);
    }

    public Optional<T> findOneBy(Predicate<T> predicate) {
        return findAllByAsStream(predicate).findFirst();
    }

    public Optional<T> findById(String id) {
        var file = getEntityPath(id);

        if (Files.exists(file)) {
            return Operators.ignoreExceptionsToOptional(
                    () -> this.readValue.get(file),
                    ex -> log.warn("An exception occurred while reading entity `{}`.", id, ex));
        } else {
            return Optional.empty();
        }
    }

    public void save(T entity) {
        var file = getEntityPath(getId.get(entity));
        Operators.suppressExceptions(
                () -> writeValue.run(file, entity),
                "An exception occurred while writing entity `" + file + "`.");
    }

    private Path getEntityPath(String id) {
        return workingDirectory.resolve(id + "." + entityName + ".json");
    }
}
