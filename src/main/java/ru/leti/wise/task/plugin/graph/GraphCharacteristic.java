package ru.leti.wise.task.plugin.graph;

import ru.leti.wise.task.graph.model.Graph;

public interface GraphCharacteristic extends GraphPlugin {
    int run(Graph graph);
}
