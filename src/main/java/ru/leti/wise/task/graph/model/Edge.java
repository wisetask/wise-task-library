package ru.leti.wise.task.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Edge {
    private int source;
    private int target;
    private Color color;
    private Integer weight;
    private String label;
}
