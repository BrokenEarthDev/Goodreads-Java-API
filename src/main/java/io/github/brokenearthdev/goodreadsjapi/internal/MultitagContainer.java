package io.github.brokenearthdev.goodreadsjapi.internal;

import com.google.gson.internal.$Gson$Preconditions;

import java.util.*;

/**
 * Functions similar to a {@link Map}, but the key-value pairs are {@link String}s and
 * {@code int}s (respectively). Tag names with the same name can be stored in the {@link MultitagContainer}
 * and can be retrieved either via an {@link Entry} set or via the getters.
 */
public class MultitagContainer {

    // Stores different tag names
    private List<String> tagNames = new LinkedList<>();

    // Stores the index position of the respective tag name
    private List<Integer> indexes = new LinkedList<>();


    public MultitagContainer(Map<String, Integer> map) {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.forEach(e -> {
            tagNames.add(e.getKey());
            indexes.add(e.getValue());
        });
    }

    public MultitagContainer(Collection<String> tagNames, Collection<Integer> indexes) {
        if (tagNames.size() != indexes.size()) return;
        this.tagNames = new LinkedList<>(tagNames);
        this.indexes = new LinkedList<>(indexes);
    }

    public MultitagContainer(MultitagContainer container) {
        tagNames = container.tagNames;
        indexes = container.indexes;
    }

    public MultitagContainer() {}

    public void append(String tagName, int index) {
        tagNames.add(tagName);
        indexes.add(index);
    }

    public void combine(MultitagContainer container) {
        for (Entry entry : container.getEntries()) {
            this.tagNames.add(entry.getTagName());
            this.indexes.add(entry.getIndex());
        }
    }

    public Set<Entry> getEntries() {
        List<Entry> list = new LinkedList<>();
        for (int i = 0; i < tagNames.size(); i++) {
            list.add(new Entry(tagNames.get(i), indexes.get(i)));
        }
        return new LinkedHashSet<>(list);
    }

    public Set<String> keySet() {
        return new LinkedHashSet<>(tagNames);
    }

    public int size() {
        return tagNames.size();
    }

    public MultitagContainer subContainer(int start, int end) {
        List<String> newList = this.tagNames.subList(start, end);
        List<Integer> newVals = this.indexes.subList(start, end);
        return new MultitagContainer(newList, newVals);
    }

    public Set<Integer> get(String tagName) {
        List<Integer> indexes = new LinkedList<>();
        for (int i = 0; i < tagNames.size(); i++) {
            if (tagNames.get(i).equals(tagName))
                indexes.add(i);
        }
        return new LinkedHashSet<>(indexes);
    }

    public Set<Integer> get(int index) {
        return get(tagNames.get(index));
    }

    public List<String> getTagNames() {
        return new LinkedList<>(tagNames);
    }

    public List<Integer> getIndexes() {
        return new LinkedList<>(indexes);
    }

    public static class Entry {

        private String tagName;
        private int index;

        Entry(String tagName, int index) {
            this.tagName = tagName;
            this.index = index;
        }

        public String getTagName() {
            return tagName;
        }

        public int getIndex() {
            return index;
        }

    }

}
