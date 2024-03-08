package org.example;

import org.example.dataModels.MetaModel;
import org.glassfish.jersey.linking.InjectLink;

public class Config {

    public static String RESOURCE_PATH = "src/main/resources";
    public static String TEMPLATE_PATH = RESOURCE_PATH + "/templates/";
    public static String OUTPUT_PATH = "D:\\Uni\\Projects\\Bachelorarbeit\\SuttonCreatorTesting\\Sutton\\src\\main\\java\\de\\fhws\\fiw\\fds\\implementation\\server";
    public static final MetaModel META_MODEL = new MetaModel()
            .setBasePackage("de.fhws.fiw.fds.implementation")
            .setBaseContextPath("test")
            .addResource(new Resource()
                    .setName("Student")
                    .addAttribute("String" , "firstName")
                    .addAttribute("String", "lastName")
                    .addQuery(new Query()
                            .addAttribute("String", "firstName", "")
                            .addAttribute("String", "lastName", "")
                            .setSubPathElement("test")
                    )
                    .addLink("selfLink", Link.SelfLinkOnPrimary("students"))
                    .addLink("courses", new Link(InjectLink.Style.ABSOLUTE, "students/${instance.id}/courses", "getCoursesOfStudents", "courses", "true"))
                    .setPathElement("students")
                    .addSubResource(new Resource()
                            .setName("Course")
                            .addAttribute("String", "name")
                            .addAttribute("String", "room")
                            .addQuery(new Query()
                                    .addAttribute("String", "name", "")
                                    .addAttribute("String", "room", "")
                                    .setSubPathElement("")
                            )
                            .addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("students", "courses"))
                            .addLink("selfLink", Link.SelfLinkOnPrimary("courses"))
                            .setPathElement("courses")
                    )
            );

}
