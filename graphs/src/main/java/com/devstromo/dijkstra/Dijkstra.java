package com.devstromo.dijkstra;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {
    public void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Queue<Node> unsettledNodes = new PriorityBlockingQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = unsettledNodes.poll();
            currentNode.getAdjacentNodes()
                .entrySet()
                .stream()
                .filter(entry -> !settledNodes.contains(entry.getKey()))
                .forEach(entry -> {
                    evaluateDistanceAndPtah(entry.getKey(), entry.getValue(), currentNode);
                    unsettledNodes.add(entry.getKey());
                });
            settledNodes.add(currentNode);
        }
    }

    public void printPath(List<Node> nodes) {
        nodes.forEach(node -> {
            String path = node.getShortestPath()
                .stream()
                .map(Node::getName)
                .collect(Collectors.joining(" -> "));
            System.out.println(
                (path.isBlank() ? "%s %s".formatted(node.getName(), node.getDistance()) : "%s -> %s : %s".formatted(path, node.getName(), node.getDistance())));
        });
    }

    private void evaluateDistanceAndPtah(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance()) {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath()
                    .stream(), Stream.of(sourceNode))
                .toList());
        }
    }
}
