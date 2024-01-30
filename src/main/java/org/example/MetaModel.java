package org.example;

import java.util.ArrayList;
import java.util.List;

public class MetaModel {
    private final List<Resource> resources;
    private String basePackage;

    public MetaModel() {
        resources = new ArrayList<>();
    }
}
