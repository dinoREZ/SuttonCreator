package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.example.api.models.Model;
import org.example.database.Dao;
import org.example.database.hibernate.DaoAdapter;
import org.example.database.hibernate.dao.DaoHibernate;
import org.example.database.hibernate.dao.DaoHibernateImpl;
import org.example.database.hibernate.models.ModelDB;
import org.example.database.hibernate.operations.*;
import org.example.database.inmemory.DaoImpl;
import org.glassfish.jersey.linking.InjectLink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public final static String RESOURCE_PATH = "src/main/resources";
    public static Model model;
    public static DaoImpl daoImpl;
    public static DaoHibernate daoHibernate;
    public static DaoHibernateImpl daoHibernateImpl;
    public static ModelDB modelDB;
    public static DeleteByIdOperation deleteByIdOperation;
    public static PersistOperation persistOperation;
    public static QueryOperation queryOperation;
    public static ReadByIdOperation readByIdOperation;
    public static UpdateOperation updateOperation;
    public static DaoAdapter daoAdapter;
    public static Dao dao;

    public static void main(String[] args) {
        try {
            model = new Model();
            model.setName("Student");
            model.addAttribute("String", "firstName");
            model.addAttribute("String", "lastName");
            model.addLink("selfLinkOnSecond", Link.SelfLinkOnSecondary("courses", "students"));
            model.addLink("courses", new Link(InjectLink.Style.ABSOLUTE, "students/${instance.id}/courses", "getCoursesOfStudents", "courses", "true"));
            model.setBasePackage("de.fhws.fiw.fds.implementation");

            daoImpl = new DaoImpl();
            daoImpl.setName("Student");
            daoImpl.setHasQuery(true);
            daoImpl.addQueryParameter("String", "firstName");
            daoImpl.addQueryParameter("String", "lastName");
            daoImpl.setBasePackage("de.fhws.fiw.fds.implementation");

            daoHibernate = new DaoHibernate();
            daoHibernate.setName("Student");
            daoHibernate.setHasQuery(true);
            daoHibernate.addQueryParameter("String", "firstName");
            daoHibernate.addQueryParameter("String", "lastName");
            daoHibernate.setBasePackage("de.fhws.fiw.fds.implementation");

            daoHibernateImpl = new DaoHibernateImpl();
            daoHibernateImpl.setName("Student");
            daoHibernateImpl.setHasQuery(true);
            daoHibernateImpl.addQueryParameter("String", "firstName");
            daoHibernateImpl.addQueryParameter("String", "lastName");
            daoHibernateImpl.setBasePackage("de.fhws.fiw.fds.implementation");

            modelDB = new ModelDB();
            modelDB.setName("Student");
            modelDB.addAttribute("String", "firstName");
            modelDB.addAttribute("String", "lastName");
            modelDB.setBasePackage("de.fhws.fiw.fds.implementation");

            deleteByIdOperation = new DeleteByIdOperation();
            deleteByIdOperation.setName("Student");
            deleteByIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            persistOperation = new PersistOperation();
            persistOperation.setName("Student");
            persistOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            queryOperation = new QueryOperation();
            queryOperation.setName("Student");
            queryOperation.addQueryParameter("String", "firstName");
            queryOperation.addQueryParameter("String", "lastName");
            queryOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            readByIdOperation = new ReadByIdOperation();
            readByIdOperation.setName("Student");
            readByIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            updateOperation = new UpdateOperation();
            updateOperation.setName("Student");
            updateOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            daoAdapter = new DaoAdapter();
            daoAdapter.setName("Student");
            daoAdapter.addAttribute("String", "firstName");
            daoAdapter.addAttribute("String", "lastName");
            daoAdapter.setHasQuery(true);
            daoAdapter.addQueryParameter("String", "firstName");
            daoAdapter.addQueryParameter("String", "lastName");
            daoAdapter.setBasePackage("de.fhws.fiw.fds.implementation");

            dao = new Dao();
            dao.setName("Student");
            dao.setHasQuery(true);
            dao.addQueryParameter("String", "firstName");
            dao.addQueryParameter("String", "lastName");
            dao.setBasePackage("de.fhws.fiw.fds.implementation");

            FileUtils.cleanDirectory(new File(RESOURCE_PATH + "/output"));

            processTemplates();

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private static void processTemplates() throws IOException, TemplateException {
        processTemplate("Model.ftl", model, model.getName() + ".java", "api/models");

        processTemplate("Dao.ftl", dao, dao.getName() + "Dao.java", "database");
        processTemplate("DaoImpl.ftl", daoImpl, daoImpl.getName() + "DaoImpl.java", "database/inmemory");
        processTemplate("DaoAdapter.ftl", daoAdapter, daoAdapter.getName() + "DaoAdapter.java", "database/hibernate");
        processTemplate("DaoHibernate.ftl", daoHibernate, daoHibernate.getName() + "DaoHibernate.java", "database/hibernate/dao");
        processTemplate("DaoHibernateImpl.ftl", daoHibernateImpl, daoHibernateImpl.getName() + "DaoHibernateImpl.java", "database/hibernate/dao");

        processTemplate("ReadByIdOperation.ftl", readByIdOperation, readByIdOperation.getName() + "ReadByIdOperation.java", "database/hibernate/operations");
        processTemplate("PersistOperation.ftl", persistOperation, persistOperation.getName() + "PersistOperation.java", "database/hibernate/operations");
        processTemplate("DeleteByIdOperation.ftl", deleteByIdOperation, deleteByIdOperation.getName() + "DeleteByIdOperation.java", "database/hibernate/operations");
        processTemplate("UpdateOperation.ftl", updateOperation, updateOperation.getName() + "UpdateOperation.java", "database/hibernate/operations");
        processTemplate("QueryOperation.ftl", queryOperation, queryOperation.getName() + "QueryOperation.java", "database/hibernate/operations");
        processTemplate("ModelDB.ftl", modelDB, modelDB.getName() + "DB.java", "database/hibernate/models");
    }

    private static void processTemplate(String fileName, Object dataModel, String outputName, String path) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(RESOURCE_PATH + "/templates/" + path));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);

        Template template = cfg.getTemplate(fileName);
        File file = new File(RESOURCE_PATH + "/output/" + path);
        file.mkdirs();
        template.process(dataModel, new FileWriter(RESOURCE_PATH + "/output/" + path + "/" + outputName));
    }
}