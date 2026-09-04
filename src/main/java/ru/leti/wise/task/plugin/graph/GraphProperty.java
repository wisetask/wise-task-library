package ru.leti.wise.task.plugin.graph;

import ru.leti.wise.task.graph.model.Graph;

public interface GraphProperty extends GraphPlugin {
    boolean run(Graph graph);
}
