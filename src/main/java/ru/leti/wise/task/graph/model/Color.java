package ru.leti.wise.task.graph.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Color {
    GRAY("GRAY"),
    RED("RED"),
    BLUE("BLUE"),
    GREEN("GREEN");
    private final String value;
}
