package io.github.brokenearthdev.goodreadsjapi.internal;

import java.util.*;

/**
 * An implementation of the container class
 */
public class TagIndexContainer implements Container<TagIndexContainer.Entry> {

    /**
     * A list containing elements
     */
    private List<Entry> elements = new LinkedList<>();

    public TagIndexContainer() {}

    public TagIndexContainer(Map<String, Integer> map) {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.forEach(e -> {
            elements.add(new Entry(e.getKey(), e.getValue()));
        });
    }

    public TagIndexContainer(Collection<String> tagNames, Collection<Integer> indexes) {
        if (tagNames.size() != indexes.size()) return;
        List<String> tagList = new LinkedList<>(tagNames);
        List<Integer> indexList = new LinkedList<>(indexes);
        for (int i = 0; i < tagList.size(); i++)
            elements.add(new Entry(tagList.get(i), indexList.get(i)));
    }

    public TagIndexContainer(TagIndexContainer container) {
        combine(container);
    }

    @Override
    public List<Entry> getElements() {
        return new LinkedList<>(elements);
    }

    @Override
    public void addElement(Entry element) {
        elements.add(element);
    }

    @Override
    public void removeElement(int index) {
        elements.remove(index);
    }

    @Override
    public Container<Entry> subContainer(int start, int end) {
        List<Entry> list =  elements.subList(start, end);
        TagIndexContainer container = new TagIndexContainer();
        list.forEach(container::addElement);
        return container;
    }

    @Override
    public void combine(Container<Entry> container) {
        if (container instanceof TagIndexContainer) {
            TagIndexContainer tagIndexContainer = (TagIndexContainer) container;
            elements.addAll(tagIndexContainer.elements);
        }
    }

    public void addElement(String tag, int index) {
        addElement(new Entry(tag, index));
    }

    public int[] removeElements(String tag, int index) {
        Entry entry = new Entry(tag, index);
        return removeElements(entry);
    }

    public List<Integer> getIndexes() {
        List<Integer> indexes = new LinkedList<>();
        elements.forEach(i -> indexes.add(i.getIndex()));
        return indexes;
    }

    public List<String> getTagNames() {
        List<String> tagNames = new LinkedList<>();
        elements.forEach(i -> tagNames.add(i.getTagName()));
        return tagNames;
    }

    /**
     * An immutable class that stores tag and index
     */
    public static class Entry {

        private String tag;
        private int index;

        public Entry(String tag, int index) {
            this.tag = tag;
            this.index = index;
        }

        public String getTagName() {
            return tag;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public boolean equals(Object o2) {
            if (!(o2 instanceof Entry))
                return false;
            Entry entry = (Entry) o2;
            return entry.tag.equals(tag) && entry.index == index;
        }

    }

}
