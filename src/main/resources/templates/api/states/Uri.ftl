package ${basePackage}.server.api.states;

import ${basePackage}.Start;

public interface ${name}Uri {
    String PATH_ELEMENT = "${pathElement}";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}
