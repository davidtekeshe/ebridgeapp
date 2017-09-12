package com.ebridgevas.android.ebridgeapp.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// TODO Implement proper graph algorithm
public class TreeNode<T> implements Iterable<TreeNode<T>> {

    public T data;
    public TreeNode<T> parent;
    public List<TreeNode<T>> children;

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    private List<TreeNode<T>> elementsIndex;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();

        // TODO Drop if not required
        this.elementsIndex = new LinkedList<TreeNode<T>>();
        this.elementsIndex.add(this);
    }

    // TODO Revise
    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        this.registerChildForSearch(childNode);
        return childNode;
    }

    // TODO Drop if not required otherwise implement recursive algorithm
    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    // TODO Drop if not necessary
    private void registerChildForSearch(TreeNode<T> node) {
        elementsIndex.add(node);
        if (parent != null)
            parent.registerChildForSearch(node);
    }

    // TODO Drop if not necessary
    public TreeNode<T> findNode(Comparable<T> cmp) {
        for (TreeNode<T> element : this.elementsIndex) {
            T elData = element.data;
            if (cmp.compareTo(elData) == 0)
                return element;
        }

        return null;
    }

    public TreeNode<T> getNodeAt(int index) {
        return elementsIndex.get(index);
    }

    public TreeNode<T> getChildAt(int index) {
        if (hasChildren() && this.children.size() > index) {
            TreeNode<T> result = this.children.get(index);
            return result;
        } else {
            return getNodeAt(index);
//            throw new IllegalArgumentException();
        }
    }

    public List<T> childrenData() {
        List<T> list = new ArrayList<>();
        for (TreeNode<T> child : children) {
            list.add(child.data);
        }

        return list;
    }

    public boolean hasChildren(){
        return this.children.size() > 0;
    }

    public int count() {
        return children.size();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", parent=" + parent +
                ", children=" + children +
                ", elementsIndex=" + elementsIndex +
                '}';
    }

    // TODO Drop if not required
    @Override
    public Iterator<TreeNode<T>> iterator() {
        return new TreeNodeIterator<T>(this);
    }
}

