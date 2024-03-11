package org.example;

import org.example.dataModels.MetaModel;
import org.glassfish.jersey.linking.InjectLink;

public class Config {

    public static String RESOURCE_PATH = "src/main/resources";
    public static String TEMPLATE_PATH = RESOURCE_PATH + "/templates/";
    public static String OUTPUT_PATH = "D:\\Uni\\Projects\\Bachelorarbeit\\SuttonCreatorTesting\\Sutton\\src\\main\\java";
    //public static String OUTPUT_PATH = RESOURCE_PATH + "/output";
    public static final MetaModel META_MODEL = new MetaModel()
            .setBasePackage("implementation")
            .setBaseContextPath("test")
            .addResource(new Resource()
                    .setName("Student")
                    .addAttribute("String" , "firstName")
                    .addAttribute("String", "lastName")
                    .addQuery(new Query()
                            .addQueryParameter(new QueryParameter()
                                    .setType("String")
                                    .setName("firstName")
                                    .setPathParameter(true)
                                    .setComparisonType(QueryParameter.ComparisonType.likeIgnoreCase)
                                    .setDefaultValue(""))
                            .addQueryParameter(new QueryParameter()
                                    .setType("String")
                                    .setName("lastName")
                                    .setPathParameter(true)
                                    .setComparisonType(QueryParameter.ComparisonType.likeIgnoreCase)
                                    .setDefaultValue(""))
                            .setSubPathElement("test")
                    )
                    .addLink("selfLink", Link.SelfLinkOnPrimary("students"))
                    .addLink("courses", new Link(InjectLink.Style.ABSOLUTE, "students/${instance.id}/courses", "getCoursesOfStudents", "courses", "true"))
                    .setPathElement("students")
                    .addSubResource(new Resource()
                            .setName("Course")
                            .addAttribute("String", "name")
                            .addAttribute("int", "roomNumber")
                            .addQuery(new Query()
                                    .addQueryParameter(new QueryParameter()
                                            .setType("String")
                                            .setName("name")
                                            .setPathParameter(true)
                                            .setComparisonType(QueryParameter.ComparisonType.likeIgnoreCase)
                                            .setDefaultValue(""))
                                    .addQueryParameter(new QueryParameter()
                                            .setType("int")
                                            .setName("roomNumber")
                                            .setPathParameter(true)
                                            .setComparisonType(QueryParameter.ComparisonType.equals)
                                            .setDefaultValue("0"))
                                    .setSubPathElement("")
                            )
                            .addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("students", "courses"))
                            .addLink("selfLink", Link.SelfLinkOnPrimary("courses"))
                            .setPathElement("courses")
                    )
            );

}
