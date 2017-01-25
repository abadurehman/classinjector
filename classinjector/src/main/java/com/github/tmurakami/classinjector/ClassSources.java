package com.github.tmurakami.classinjector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class sources is an object representing a group of {@link ClassSource} objects.
 */
@SuppressWarnings("WeakerAccess")
public final class ClassSources implements ClassSource {

    private final List<ClassSource> sources;

    /**
     * Instantiates a new instance.
     *
     * @param sources the collection of non-null {@link ClassSource} objects
     */
    public ClassSources(Iterable<? extends ClassSource> sources) {
        if (sources == null) {
            throw new IllegalArgumentException("'sources' is null");
        }
        List<ClassSource> list = new ArrayList<>();
        for (ClassSource s : sources) {
            if (s == null) {
                throw new IllegalArgumentException("'sources' contains null");
            }
            list.add(s);
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("'sources' is empty");
        }
        this.sources = list;
    }

    @Override
    public ClassFile getClassFile(String className) throws IOException {
        if (className == null) {
            throw new IllegalArgumentException("'className' is null");
        }
        if (className.isEmpty()) {
            throw new IllegalArgumentException("'className' is empty");
        }
        for (ClassSource b : sources) {
            ClassFile f = b.getClassFile(className);
            if (f != null) {
                return f;
            }
        }
        return null;
    }

}
