package org.example.dataModels;

public interface DataModel {

    default String getPath() {
        return this.getClass()
                .getPackageName()
                .replace(DataModel.class.getPackageName(), "") // Remove top level packages
                .replace(".", "/"); // Convert to file path
    }

    String getOutputName();

    default String getTemplateName() {
        return this.getClass().getSimpleName() + ".ftl";
    }
}
