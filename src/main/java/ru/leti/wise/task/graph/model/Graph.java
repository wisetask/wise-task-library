package ru.leti.wise.task.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Graph {
    private int vertexCount;
    private int edgeCount;
    private boolean isDirect;
    private List<Edge> edgeList;
    private List<Vertex> vertexList;
}
