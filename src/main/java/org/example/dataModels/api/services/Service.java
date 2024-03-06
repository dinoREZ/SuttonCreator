package org.example.dataModels.api.services;

import org.example.Resource;
import org.example.dataModels.DataModel;

import java.util.Objects;

public class Service implements DataModel {

    Resource resource;
    String basePackage;

    public Service(Resource resource, String basePackage) {
        this.resource = resource;
        this.basePackage = basePackage;
    }

    public Resource getResource() {
        return resource;
    }

    public String getBasePackage() {
        return basePackage;
    }

    @Override
    public String getOutputName() {
        return resource.getName() + "Service.java";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(resource, service.resource) && Objects.equals(basePackage, service.basePackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resource, basePackage);
    }
}
