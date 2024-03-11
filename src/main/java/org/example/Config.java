package org.example;

import org.example.dataModels.MetaModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.CacheControl;

public class Config {

    public static String RESOURCE_PATH = "src/main/resources";
    public static String TEMPLATE_PATH = RESOURCE_PATH + "/templates/";
    public static String OUTPUT_PATH = "D:\\Uni\\Projects\\Bachelorarbeit\\SuttonCreatorTesting\\Sutton\\src\\main\\java";
    //public static String OUTPUT_PATH = RESOURCE_PATH + "/output";
    public static final MetaModel META_MODEL = createMetaModel();

    private static MetaModel createMetaModel() {
        MetaModel metaModel = new MetaModel()
                .setBasePackage("implementation")
                .setBaseContextPath("test")
                .setUsesInMemory(false)
                .addResource(new Resource()
                        .setName("Student")
                        .addAttribute("String", "firstName")
                        .addAttribute("String", "lastName")
                        .addQuery(new Query()
                                .addQueryParameter(new QueryParameter()
                                        .setType("String")
                                        .setName("firstName")
                                        .setPathParameter(true)
                                        .setComparisonType(QueryParameter.ComparisonType.likeIgnoreCase)
                                        .setDefaultValueString(""))
                                .addQueryParameter(new QueryParameter()
                                        .setType("String")
                                        .setName("lastName")
                                        .setPathParameter(true)
                                        .setComparisonType(QueryParameter.ComparisonType.likeIgnoreCase)
                                        .setDefaultValueString(""))
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
                                                .setComparisonType(QueryParameter.ComparisonType.like)
                                                .setDefaultValueString(""))
                                        .addQueryParameter(new QueryParameter()
                                                .setType("int")
                                                .setName("roomNumber")
                                                .setPathParameter(false)
                                                .setComparisonType(QueryParameter.ComparisonType.lessThanOrEqualTo)
                                                .setDefaultValueExpression("1"))
                                        .setSubPathElement("aaa")
                                )
                                .addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("students", "courses"))
                                .addLink("selfLink", Link.SelfLinkOnPrimary("courses"))
                                .setPathElement("courses")
                        )
                );
        metaModel.getResources().forEach(resource -> {
            resource.setUseEtags(true);
            resource.setCacheControl(new CacheControl());
            resource.setDefaultSorting("");
            resource.setUsePaging(true);
            resource.setDefaultPagingOffset(0);
            resource.setDefaultPagingSize(15);
        });
        return metaModel;
    }
}
