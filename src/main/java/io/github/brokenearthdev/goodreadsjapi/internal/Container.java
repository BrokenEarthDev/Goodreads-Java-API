package io.github.brokenearthdev.goodreadsjapi.internal;

import java.util.LinkedList;
import java.util.List;

/**
 * A container contains different elements that will be retrieved when finding a
 * {@link io.github.brokenearthdev.goodreadsjapi.response.ResponseSection} within a
 * {@link io.github.brokenearthdev.goodreadsjapi.response.GoodreadsResponse} or a
 * {@link io.github.brokenearthdev.goodreadsjapi.response.ResponseSection}
 *
 * @param <T> The type
 */
public interface Container<T> {

    /**
     * This method returns a {@link List} that could be modified as it is a copy
     * from the original list.
     *
     * @return The list of {@link T}s stored within the container.
     */
    List<T> getElements();

    /**
     * Adds an element to this container. This element is then stored in the list retrievable by
     * calling {@link #getElements()}. The element will be in the last index in the list unless
     * another element is added or it is removed).
     *
     * @param element The element of type {@link T}
     */
    void addElement(T element);

    /**
     * Adds different element to this container. This is equivalent to invoking {@link #addElement(Object)} for
     * every given element.
     *
     * @param elements The elements of type {@link T}
     */
    default void addElements(T... elements) {
        for (T element : elements)
            addElement(element);
    }

    /**
     * Removes an element from this container
     *
     * @param index An index in the list where an element is found
     */
    void removeElement(int index);

    /**
     * Removes the element from this container. If there are elements where invoking {@code #equals()}
     * returns true, these elements will also return true.
     *
     * @param element The element of type {@link T}
     * @return The indexes the had occupied in this container prior to removal
     */
    default int[] removeElements(T element) {
        List<Integer> indexes = new LinkedList<>();
        List<T> elements = getElements();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(element))
                indexes.add(i);
        }
        indexes.forEach(this::removeElement);
        int[] array = new int[indexes.size()];
        indexes.forEach(i -> array[i] = i);
        return array;
    }

    /**
     * @return The amount of elements present in the object
     */
    default int size() {
        return getElements().size();
    }

    /**
     * Finds a sub container given the start index and the end index.
     *
     * @param start The start index
     * @param end The end index
     * @return A sub container
     */
    Container<T> subContainer(int start, int end);

    /**
     * Combines another container with this container. The changes will occur in this
     * container object (ie. the object where this method is called).
     *
     * @param container Another container of type {@link T}
     */
    void combine(Container<T> container);

}
