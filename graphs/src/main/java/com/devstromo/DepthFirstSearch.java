package com.devstromo;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch<T> {

    public void traverse(Vertex<T> startVertex) {
        Deque<Vertex<T>> stack = new LinkedList<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            Vertex<T> current = stack.pop();
            if (!current.isVisited()) {
                current.setVisited(true);
                System.out.println(current);
                current.getNeighbors()
                    .forEach(stack::push);
            }
        }
    }

    public void traverseRecursively(Vertex<T> startVertex) {
        if (startVertex.isVisited()) {
            return;
        }
        startVertex.setVisited(true);
        System.out.println(startVertex);
        startVertex.getNeighbors()
            .stream()
            .filter(neighbor -> !neighbor.isVisited())
            .forEach(this::traverseRecursively);
    }
}
