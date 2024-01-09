package ${basePackage}.server.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import jakarta.persistence.Entity;

@Entity
public class ${primaryName}CourseDB extends AbstractDBRelation<${primaryName}DB, ${secondaryName}DB> {
}