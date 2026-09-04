package ru.leti.wise.task.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vertex {
    private int id;
    private Color color;
    private Integer weight;
    private String label;
    private int xCoordinate;
    private int yCoordinate;
}
