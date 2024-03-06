package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.example.dataModels.DataModel;
import org.example.dataModels.MetaModel;
import org.example.dataModels.api.states.*;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.CacheControl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.example.Config.RESOURCE_PATH;

public class Main {

    public static void main(String[] args) throws IOException, TemplateException {
        MetaModel metaModel = new MetaModel();
        metaModel.setBasePackage("de.fhws.fiw.fds.implementation");
        metaModel.addResource(new Resource()
                .setName("Student")
                .addAttribute("String" , "firstName")
                .addAttribute("String", "lastName")
                .addQuery(new Query()
                        .addAttribute("String", "firstName")
                        .addAttribute("String", "lastName")
                )
                .addLink("selfLink", Link.SelfLinkOnPrimary("students"))
                .addLink("courses", new Link(InjectLink.Style.ABSOLUTE, "students/${instance.id}/courses", "getCoursesOfStudents", "courses", "true"))
                .addSubResource(new Resource()
                        .setName("Course")
                        .addAttribute("String", "name")
                        .addAttribute("String", "room")
                        .addQuery(new Query()
                                .addAttribute("String", "name")
                                .addAttribute("String", "room")
                        )
                        .addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("students", "courses"))
                        .addLink("selfLink", Link.SelfLinkOnPrimary("courses"))
                )
        );

        List<DataModel> dataModels = DataManager.getAllDataModels(metaModel);

        dataModels = List.of(new GetDispatcherState(List.of(new Resource().setName("First").addState(new State().setStateType(StateType.GET_ALL)), new Resource().setName("Second").addState(new State().setStateType(StateType.GET_ALL))), "org.example"));

        try {
            FileUtils.cleanDirectory(new File(RESOURCE_PATH + "/output"));
        }
        catch(IllegalArgumentException ignored) {} // Don't care if the directory doesn't exist yet, we create it later anyway

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(RESOURCE_PATH + "/templates/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);

        for (DataModel dataModel : dataModels) {
            String basePath = dataModel.getPath();
            String templatePath = basePath + "/" + dataModel.getTemplateName();
            String outputDirectory = RESOURCE_PATH + "/output" + basePath;
            String outputPath = outputDirectory + "/" + dataModel.getOutputName();

            Template template = cfg.getTemplate(templatePath);
            File directory = new File(outputDirectory);
            directory.mkdirs();
            template.process(dataModel, new FileWriter(outputPath));
        }
    }
}
