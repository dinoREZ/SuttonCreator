package ${basePackage}.api.states.coursesOfStudent;

import de.fhws.fiw.fds.implementation.Start;

public interface ${primaryName}${secondaryName}Uri {
    String PATH_ELEMENT = "${primaryPathElement}/{id}/${secondaryPathElement}";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";
}