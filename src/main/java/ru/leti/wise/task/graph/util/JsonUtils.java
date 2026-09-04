package ru.leti.wise.task.graph.util;

import ru.leti.wise.task.graph.model.Graph;
import tools.jackson.databind.json.JsonMapper;

public class JsonUtils {

    private static final JsonMapper jsonMapper = new JsonMapper();

    public static String serializeGraph(Graph graph) {
        return jsonMapper.writeValueAsString(graph);
    }

    public static Graph deserializeGraph(String graph) {
        return jsonMapper.readValue(graph, Graph.class);
    }
}
