package ru.leti.wise.task.graph.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ru.leti.wise.task.graph.model.Color;
import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Vertex;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

/**
 * @deprecated Use {@link JsonUtils} instead.
 * Этот класс устарел и будет удален в следующих версиях.
 */
@Deprecated(since = "1.0.9" )
public class FileLoader {

    private static final String NULL_VALUE = "null";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Graph loadGraphFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            List<Vertex> vertices = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();

            boolean isDirected = scanner.nextBoolean();
            int verticesCount = scanner.nextInt();
            int edgeCount = scanner.nextInt();

            readVertices(scanner, vertices, verticesCount);
            readEdges(scanner, edges, edgeCount, vertices);

            return Graph
                    .builder()
                    .vertexCount(verticesCount)
                    .edgeCount(edgeCount)
                    .isDirect(isDirected)
                    .vertexList(vertices)
                    .edgeList(edges)
                    .build();
        } catch (Exception e) {
            //TODO: придумать нормальные ошибки при ошибке чтения графа
            throw new RuntimeException("Error while reading graph from file");
        }
    }

    private static void readEdges(Scanner scanner, List<Edge> edges, int edgeCount, List<Vertex> vertices) {
        for (int i = 0; i < edgeCount; i++) {
            int source = scanner.nextInt();
            int target = scanner.nextInt();
            Color color = Color.valueOf(scanner.next());
            String weight = scanner.next();
            String label = scanner.next();
            Edge edge = Edge
                    .builder()
                    .source(source)
                    .target(target)
                    .color(color)
                    .weight(weight.equals(NULL_VALUE) ? null : parseInt(weight))
                    .label(label)
                    .build();
            edges.add(edge);
        }
    }

    private static void readVertices(Scanner scanner, List<Vertex> vertices, int verticesCount) {
        for (int i = 0; i < verticesCount; i++) {
            int id = scanner.nextInt();
            Color color = Color.valueOf(scanner.next());
            String weight = scanner.next();
            String label = scanner.next();
            double xCoordinate = scanner.nextDouble();
            double yCoordinate = scanner.nextDouble();
            vertices.add(Vertex
                    .builder()
                    .id(id)
                    .color(color)
                    .weight(weight.equals(NULL_VALUE) ? null : parseInt(weight))
                    .label(label)
                    .xCoordinate((int) xCoordinate)
                    .yCoordinate((int) yCoordinate)
                    .build());
        }
    }
    
    public static Graph loadGraphFromJson(String fileName) {
        try (InputStream is = new FileInputStream(fileName)) {
            JsonGraph jsonGraph = objectMapper.readValue(is, JsonGraph.class);

            List<Vertex> vertexList = jsonGraph.vertexList.stream()
                    .map(JsonVertex::toVertex)
                    .collect(Collectors.toList());

            return Graph.builder()
                    .vertexCount(jsonGraph.vertexCount)
                    .edgeCount(jsonGraph.edgeCount)
                    .isDirect(jsonGraph.isDirect)
                    .vertexList(vertexList)
                    .edgeList(jsonGraph.edgeList)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Error while reading graph from JSON file: " + e.getMessage(), e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class JsonGraph {
        public int vertexCount;
        public int edgeCount;
        public boolean isDirect;
        public List<JsonVertex> vertexList;
        public List<Edge> edgeList;
        public String id;
        public boolean isNamed;
        public String name;
    }

    private static class JsonVertex {
        public int id;
        public String label;
        public int weight;
        public Color color;
        public int xCoordinate;
        public int yCoordinate;

        public Vertex toVertex() {
            return Vertex.builder()
                    .id(id)
                    .label(label)
                    .weight(weight)
                    .color(color)
                    .xCoordinate(xCoordinate)
                    .yCoordinate(yCoordinate)
                    .build();
        }
    }
}
