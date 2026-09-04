package ru.leti.wise.task.plugin.graph;

import ru.leti.wise.task.graph.model.Graph;

public interface NewGraphConstruction extends GraphPlugin {
    boolean run(Graph graphQuestion, Graph graphAnswer);
}
