package dev.emmily.oersted.hierarchy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a tree structure for hierarchical elements, where each element has a parent and a list of children.
 * This class provides methods to add, remove, and query elements in the hierarchy, as well as methods for retrieving
 * the parent and children of an element.
 *
 * @param <T> the type of elements that are part of the hierarchy, which must implement the {@link Hierarchical} interface.
 */
public class HierarchicalTree<T extends Hierarchical> {

  private final Map<String, T> tree;

  /**
   * Constructs a new {@code HierarchicalTree} with the specified map of elements.
   *
   * @param tree a map that holds the hierarchical elements, keyed by their unique ID.
   */
  public HierarchicalTree(Map<String, T> tree) {
    this.tree = tree;
  }

  /**
   * Constructs a new {@code HierarchicalTree} with an empty map.
   */
  public HierarchicalTree() {
    this(new HashMap<>());
  }

  /**
   * Adds a new element to the hierarchy.
   *
   * @param object the element to be added.
   */
  public void add(T object) {
    tree.put(object.id(), object);
  }

  /**
   * Removes an element from the hierarchy by its ID.
   *
   * @param id the unique ID of the element to be removed.
   */
  public void remove(String id) {
    tree.remove(id);
  }

  /**
   * Removes an element from the hierarchy.
   *
   * @param object the element to be removed.
   */
  public void remove(T object) {
    this.remove(object.id());
  }

  /**
   * Retrieves an element from the hierarchy by its unique ID.
   *
   * @param id the unique ID of the element to be retrieved.
   * @return the element with the specified ID.
   * @throws IllegalArgumentException if the element with the specified ID does not exist in the tree.
   */
  public T get(String id) {
    T object = tree.get(id);

    if (object == null) {
      throw new IllegalArgumentException("the element " + id + " is not part of the tree.");
    }

    return object;
  }

  /**
   * Retrieves the parent element of a given element by its ID.
   * If the element is the root (i.e., it has no parent), the element itself is returned.
   *
   * @param id the unique ID of the element whose parent is to be retrieved.
   * @return the parent element, or the element itself if it is the root.
   * @throws IllegalArgumentException if the element with the specified ID does not exist in the tree.
   */
  public T getParent(String id) {
    T object = get(id);
    String parent = object.parent();

    if (parent.equals(Hierarchical.PARENT)) {
      return object;
    }

    return get(parent);
  }

  /**
   * Retrieves the parent element of a given element.
   * If the element is the root (i.e., it has no parent), the element itself is returned.
   *
   * @param object the element whose parent is to be retrieved.
   * @return the parent element, or the element itself if it is the root.
   */
  public T getParent(T object) {
    String parent = object.parent();

    if (parent.equals(Hierarchical.PARENT)) {
      return object;
    }

    return get(parent);
  }

  /**
   * Retrieves the list of children of an element identified by its unique ID.
   *
   * @param id the unique ID of the element whose children are to be retrieved.
   * @return a list of child elements.
   */
  public List<T> getChildren(String id) {
    return tree
      .values()
      .stream()
      .filter(object -> object.parent().equals(id))
      .toList();
  }

  /**
   * Retrieves the list of children of a given element.
   *
   * @param object the element whose children are to be retrieved.
   * @return a list of child elements.
   */
  public List<T> getChildren(T object) {
    return this.getChildren(object.id());
  }
}
