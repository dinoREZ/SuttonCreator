package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.glassfish.jersey.linking.InjectLink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public final static String RESOURCE_PATH = "src/main/resources";

    public static void main(String[] args) {
        try {
            DataModel model = new DataModel();
            model.setName("Student");
            model.addAttribute("String", "firstName");
            model.addAttribute("String", "lastName");
            model.setHasQuery(true);
            model.addQueryParameter("String", "firstName");
            model.addQueryParameter("String", "lastName");
            model.addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("courses", "students"));
            model.addLink("courses", new Link(InjectLink.Style.ABSOLUTE, "students/${instance.id}/courses", "getCoursesOfStudents", "courses", "true"));
            model.setBasePackage("de.fhws.fiw.fds.implementation");

            FileUtils.cleanDirectory(new File(RESOURCE_PATH + "/output"));

            processTemplates(model);

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static void processTemplates(DataModel model) throws IOException, TemplateException {
        processTemplate("Model.ftl", model, model.getName() + ".java", "api/models");

        processTemplate("Dao.ftl", model, model.getName() + "Dao.java", "database");
        processTemplate("DaoImpl.ftl", model, model.getName() + "DaoImpl.java", "database/inmemory");
        processTemplate("DaoAdapter.ftl", model, model.getName() + "DaoAdapter.java", "database/hibernate");
        processTemplate("DaoHibernate.ftl", model, model.getName() + "DaoHibernate.java", "database/hibernate/dao");
        processTemplate("DaoHibernateImpl.ftl", model, model.getName() + "DaoHibernateImpl.java", "database/hibernate/dao");

        processTemplate("ReadByIdOperation.ftl", model, model.getName() + "ReadByIdOperation.java", "database/hibernate/operations");
        processTemplate("PersistOperation.ftl", model, model.getName() + "PersistOperation.java", "database/hibernate/operations");
        processTemplate("DeleteByIdOperation.ftl", model, model.getName() + "DeleteByIdOperation.java", "database/hibernate/operations");
        processTemplate("UpdateOperation.ftl", model, model.getName() + "UpdateOperation.java", "database/hibernate/operations");
        processTemplate("QueryOperation.ftl", model, model.getName() + "QueryOperation.java", "database/hibernate/operations");
        processTemplate("ModelDB.ftl", model, model.getName() + "DB.java", "database/hibernate/models");
    }

    private static void processTemplate(String fileName, DataModel model, String outputName, String path) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(RESOURCE_PATH + "/templates/" + path));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);

        Template template = cfg.getTemplate(fileName);
        File file = new File(RESOURCE_PATH + "/output/" + path);
        file.mkdirs();
        template.process(model, new FileWriter(RESOURCE_PATH + "/output/" + path + "/" + outputName));
    }
}