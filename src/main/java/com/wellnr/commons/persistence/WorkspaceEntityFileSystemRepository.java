package com.wellnr.commons.persistence;

import com.wellnr.commons.StringOperators;
import com.wellnr.commons.functions.Function1;
import com.wellnr.commons.functions.Procedure2;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * A generic file system repository implementation.
 *
 * @param <T> the type of the entity managed by the repository.
 */
@Slf4j
public class WorkspaceEntityFileSystemRepository<T> {

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
     * The name of the parent directory where the entities are stored.
     */
    private final String directoryName;

    /**
     * Repositories mapped for each workspace.
     */
    private final Map<String, FileSystemRepository<T>> repositories;

    /**
     * Creates a new file system repository.
     *
     * @param entityClass      the type of the entity managed by the repository.
     * @param getId            the function that returns the id of an entity.
     * @param workingDirectory The working directory where the entities are stored.
     * @param readValue        The function that reads an entity from a file.
     * @param writeValue       The function that writes an entity to a file.
     * @param entityName       The name of the entity type. This value is used to create file-name extensions.
     * @param directoryName    The name of the directory where the entities are stored (within working directory).
     */
    public WorkspaceEntityFileSystemRepository(
        Class<T> entityClass,
        Function1<T, String> getId,
        Path workingDirectory,
        Function1<Path, T> readValue,
        Procedure2<Path, T> writeValue,
        String entityName,
        String directoryName) {

        this.getId = getId;
        this.workingDirectory = workingDirectory.resolve("workspaces");
        this.readValue = readValue;
        this.writeValue = writeValue;
        this.entityName = entityName;
        this.directoryName = directoryName;
        this.repositories = new HashMap<>();
    }

    /**
     * Creates a new file system repository.
     *
     * @param entityClass      the type of the entity managed by the repository.
     * @param getId            the function that returns the id of an entity.
     * @param workingDirectory The working directory where the entities are stored.
     * @param readValue        The function that reads an entity from a file.
     * @param writeValue       The function that writes an entity to a file.
     * @param entityName       The name of the entity type. This value is used to create file-name extensions.
     */
    public WorkspaceEntityFileSystemRepository(
        Class<T> entityClass,
        Function1<T, String> getId,
        Path workingDirectory,
        Function1<Path, T> readValue,
        Procedure2<Path, T> writeValue,
        String entityName
    ) {
        this(
            entityClass, getId, workingDirectory, readValue, writeValue, entityName,
            StringOperators.pluralize(entityName.toLowerCase())
        );
    }

    /**
     * Creates a new file system repository.
     *
     * @param entityClass      the type of the entity managed by the repository.
     * @param getId            the function that returns the id of an entity.
     * @param workingDirectory The working directory where the entities are stored.
     * @param readValue        The function that reads an entity from a file.
     * @param writeValue       The function that writes an entity to a file.
     */
    public WorkspaceEntityFileSystemRepository(
        Class<T> entityClass,
        Function1<T, String> getId,
        Path workingDirectory,
        Function1<Path, T> readValue,
        Procedure2<Path, T> writeValue
    ) {
        this(
            entityClass, getId, workingDirectory, readValue, writeValue,
            StringOperators.camelCaseToKebabCase(entityClass.getSimpleName())
        );
    }

    public void delete(String workspaceId, String id) {
        getRepository(workspaceId).delete(id);
    }

    public void delete(String workspaceId, T entity) {
        getRepository(workspaceId);
    }

    public List<T> findAll(String workspaceId) {
        return getRepository(workspaceId).findAll();
    }

    public List<T> findAllBy(String workspaceId, Predicate<T> predicate) {
        return getRepository(workspaceId).findAllBy(predicate);
    }

    public Stream<T> findAllByAsStream(String workspaceId, Predicate<T> predicate) {
        return getRepository(workspaceId).findAll().stream().filter(predicate);
    }

    public Optional<T> findOneBy(String workspaceId, Predicate<T> predicate) {
        return getRepository(workspaceId).findAllByAsStream(predicate).findFirst();
    }

    public Optional<T> findById(String workspaceId, String id) {
        return getRepository(workspaceId).findById(id);
    }

    public void save(String workspaceId, T entity) {
        getRepository(workspaceId).save(entity);
    }

    private FileSystemRepository<T> getRepository(String workspaceId) {
        if (!repositories.containsKey(workspaceId)) {
            var repository = new FileSystemRepository<>(
                getId, workingDirectory.resolve(workspaceId), readValue, writeValue, entityName, directoryName
            );

            repositories.put(workspaceId, repository);
        }

        return repositories.get(workspaceId);
    }

}
