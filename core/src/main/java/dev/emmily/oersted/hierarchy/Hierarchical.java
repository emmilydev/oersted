package dev.emmily.oersted.hierarchy;

/**
 * Represents an element that belongs to a hierarchical structure.
 * <p>
 * This interface defines a single-parent relationship, allowing elements
 * to be organized in a tree-like model without enforcing knowledge of their children.
 * It's designed to keep implementing classes lightweight and focused on identity.
 */
public interface Hierarchical {
  /**
   * Default identifier used for elements at the root of the hierarchy.
   * <p>
   * This constant can be used to signify that the element has no parent,
   * typically when it's at the topmost level of the structure.
   */
  String PARENT = "parent";

  /**
   * Returns the unique identifier of this element.
   * This identifier is used to reference the element in the hierarchy.
   *
   * @return the unique id of this element.
   */
  String id();

  /**
   * Returns the identifier of the parent element in the hierarchy.
   * If the element is a root node, it may return {@link #PARENT} or another designated value.
   *
   * @return the unique identifier of the parent element.
   */
  String parent();
}
