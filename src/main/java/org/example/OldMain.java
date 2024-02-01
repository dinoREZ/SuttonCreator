package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.example.dataModels.api.models.Model;
import org.example.dataModels.database.Dao;
import org.example.dataModels.database.DaoFactory;
import org.example.dataModels.database.RelationDao;
import org.example.dataModels.database.hibernate.DaoAdapter;
import org.example.dataModels.database.hibernate.RelationDaoAdapter;
import org.example.dataModels.database.hibernate.dao.DaoHibernate;
import org.example.dataModels.database.hibernate.dao.DaoHibernateImpl;
import org.example.dataModels.database.hibernate.dao.RelationDaoHibernate;
import org.example.dataModels.database.hibernate.dao.RelationDaoHibernateImpl;
import org.example.dataModels.database.hibernate.models.ModelDB;
import org.example.dataModels.database.hibernate.models.RelationDB;
import org.example.dataModels.database.hibernate.operations.*;
import org.example.dataModels.database.inmemory.DaoImpl;
import org.example.dataModels.database.inmemory.RelationDaoImpl;
import org.glassfish.jersey.linking.InjectLink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OldMain {

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
    public static ReadAllOperation readAllOperation;
    public static UpdateOperation updateOperation;
    public static DaoAdapter daoAdapter;
    public static Dao dao;
    public static RelationDB relationDB;
    public static RelationDao relationDao;
    public static RelationDaoAdapter relationDaoAdapter;
    public static RelationDaoHibernate relationDaoHibernate;
    public static RelationDaoHibernateImpl relationDaoHibernateImpl;
    public static RelationReadByIdOperation relationReadByIdOperation;
    public static RelationReadAllOperation relationReadAllOperation;
    public static RelationUpdateOperation relationUpdateOperation;
    public static RelationQueryOperation relationQueryOperation;
    public static RelationDeleteBySecondaryIdOperation relationDeleteBySecondaryIdOperation;
    public static RelationDeleteByPrimaryIdOperation relationDeleteByPrimaryIdOperation;
    public static RelationPersistOperation relationPersistOperation;
    public static RelationDeleteByIdOperation relationDeleteByIdOperation;
    public static RelationDaoImpl relationDaoImpl;
    public static DaoFactory daoFactory;

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
            daoImpl.addQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            daoImpl.addQuery(new Query()
                    .addAttribute("String", "firstName")
            );
            daoImpl.setBasePackage("de.fhws.fiw.fds.implementation");

            daoHibernate = new DaoHibernate();
            daoHibernate.setName("Student");
            daoHibernate.addQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            daoHibernate.addQuery(new Query()
                    .addAttribute("String", "firstName")
            );
            daoHibernate.setBasePackage("de.fhws.fiw.fds.implementation");

            daoHibernateImpl = new DaoHibernateImpl();
            daoHibernateImpl.setName("Student");
            daoHibernateImpl.addQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            daoHibernateImpl.addQuery(new Query()
                    .addAttribute("String", "firstName")
            );
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
            queryOperation.setQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            queryOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            readByIdOperation = new ReadByIdOperation();
            readByIdOperation.setName("Student");
            readByIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            readAllOperation = new ReadAllOperation();
            readAllOperation.setName("Student");
            readAllOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            updateOperation = new UpdateOperation();
            updateOperation.setName("Student");
            updateOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            daoAdapter = new DaoAdapter();
            daoAdapter.setName("Student");
            daoAdapter.addAttribute("String", "firstName");
            daoAdapter.addAttribute("String", "lastName");
            daoAdapter.addQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            daoAdapter.addQuery(new Query()
                    .addAttribute("String", "firstName")
            );
            daoAdapter.setBasePackage("de.fhws.fiw.fds.implementation");

            dao = new Dao();
            dao.setName("Student");
            dao.addQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            dao.addQuery(new Query()
                    .addAttribute("String", "firstName")
            );
            dao.setBasePackage("de.fhws.fiw.fds.implementation");

            relationDB = new RelationDB();
            relationDB.setPrimaryName("Student");
            relationDB.setSecondaryName("Course");
            relationDB.setBasePackage("de.fhws.fiw.fds.implementation");

            relationDao = new RelationDao();
            relationDao.setPrimaryName("Student");
            relationDao.setSecondaryName("Course");
            relationDao.setBasePackage("de.fhws.fiw.fds.implementation");
            relationDao.addQuery(new Query()
                    .addAttribute("String", "firstName")
                    .addAttribute("String", "lastName")
            );
            relationDao.addQuery(new Query()
                    .addAttribute("String", "firstName")
            );

            relationDaoAdapter = new RelationDaoAdapter();
            relationDaoAdapter.setPrimaryName("Student");
            relationDaoAdapter.setSecondaryName("Course");
            relationDaoAdapter.setBasePackage("de.fhws.fiw.fds.implementation");
            relationDaoAdapter.addSecondaryAttribute("String", "name");
            relationDaoAdapter.addQuery(new Query()
                    .addAttribute("String", "name")
                    .addAttribute("String", "test")
            );
            relationDaoAdapter.addQuery(new Query()
                    .addAttribute("String", "name")
            );

            relationDaoHibernate = new RelationDaoHibernate();
            relationDaoHibernate.setPrimaryName("Student");
            relationDaoHibernate.setSecondaryName("Course");
            relationDaoHibernate.setBasePackage("de.fhws.fiw.fds.implementation");
            relationDaoHibernate.addQuery(new Query()
                    .addAttribute("String", "name")
                    .addAttribute("String", "test")
            );
            relationDaoHibernate.addQuery(new Query()
                    .addAttribute("String", "name")
            );

            relationDaoHibernateImpl = new RelationDaoHibernateImpl();
            relationDaoHibernateImpl.setPrimaryName("Student");
            relationDaoHibernateImpl.setSecondaryName("Course");
            relationDaoHibernateImpl.setBasePackage("de.fhws.fiw.fds.implementation");
            relationDaoHibernateImpl.addQuery(new Query()
                    .addAttribute("String", "name")
                    .addAttribute("String", "test")
            );
            relationDaoHibernateImpl.addQuery(new Query()
                    .addAttribute("String", "name")
            );

            relationReadByIdOperation = new RelationReadByIdOperation();
            relationReadByIdOperation.setPrimaryName("Student");
            relationReadByIdOperation.setSecondaryName("Course");
            relationReadByIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationReadAllOperation = new RelationReadAllOperation();
            relationReadAllOperation.setPrimaryName("Student");
            relationReadAllOperation.setSecondaryName("Course");
            relationReadAllOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationUpdateOperation = new RelationUpdateOperation();
            relationUpdateOperation.setPrimaryName("Student");
            relationUpdateOperation.setSecondaryName("Course");
            relationUpdateOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationQueryOperation = new RelationQueryOperation();
            relationQueryOperation.setPrimaryName("Student");
            relationQueryOperation.setSecondaryName("Course");
            relationQueryOperation.setBasePackage("de.fhws.fiw.fds.implementation");
            relationQueryOperation.setQuery(new Query().addAttribute("String", "name"));

            relationDeleteBySecondaryIdOperation = new RelationDeleteBySecondaryIdOperation();
            relationDeleteBySecondaryIdOperation.setPrimaryName("Student");
            relationDeleteBySecondaryIdOperation.setSecondaryName("Course");
            relationDeleteBySecondaryIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationDeleteByPrimaryIdOperation = new RelationDeleteByPrimaryIdOperation();
            relationDeleteByPrimaryIdOperation.setPrimaryName("Student");
            relationDeleteByPrimaryIdOperation.setSecondaryName("Course");
            relationDeleteByPrimaryIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationPersistOperation = new RelationPersistOperation();
            relationPersistOperation.setPrimaryName("Student");
            relationPersistOperation.setSecondaryName("Course");
            relationPersistOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationDeleteByIdOperation = new RelationDeleteByIdOperation();
            relationDeleteByIdOperation.setPrimaryName("Student");
            relationDeleteByIdOperation.setSecondaryName("Course");
            relationDeleteByIdOperation.setBasePackage("de.fhws.fiw.fds.implementation");

            relationDaoImpl = new RelationDaoImpl();
            relationDaoImpl.setPrimaryName("Student");
            relationDaoImpl.setSecondaryName("Course");
            relationDaoImpl.setBasePackage("de.fhws.fiw.fds.implementation");
            relationDaoImpl.addQuery(new Query()
                    .addAttribute("String", "name")
            );
            relationDaoImpl.addQuery(new Query()
                    .addAttribute("String", "name")
                    .addAttribute("String", "test")
            );

            daoFactory = new DaoFactory();
            daoFactory.setBasePackage("de.fhws.fiw.fds.implementation");
            daoFactory.setUsesInMemory(false);
            daoFactory.addResource("Student");
            daoFactory.addSubResource("Student", List.of("Course", "Test"));

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
        processTemplate("ReadAllOperation.ftl", readAllOperation, readAllOperation.getName() + "ReadAllOperation.java", "database/hibernate/operations");
        processTemplate("PersistOperation.ftl", persistOperation, persistOperation.getName() + "PersistOperation.java", "database/hibernate/operations");
        processTemplate("DeleteByIdOperation.ftl", deleteByIdOperation, deleteByIdOperation.getName() + "DeleteByIdOperation.java", "database/hibernate/operations");
        processTemplate("UpdateOperation.ftl", updateOperation, updateOperation.getName() + "UpdateOperation.java", "database/hibernate/operations");
        processTemplate("QueryOperation.ftl", queryOperation, queryOperation.getName() + "QueryOperation.java", "database/hibernate/operations");
        processTemplate("ModelDB.ftl", modelDB, modelDB.getName() + "DB.java", "database/hibernate/models");

        processTemplate("RelationDB.ftl", relationDB, relationDB.getPrimaryName() + relationDB.getSecondaryName() + "DB.java", "database/hibernate/models");
        processTemplate("RelationDao.ftl", relationDao, relationDao.getSecondaryName() + "sOf" + relationDao.getPrimaryName() + "Dao.java", "database");
        processTemplate("RelationDaoAdapter.ftl", relationDaoAdapter, relationDaoAdapter.getSecondaryName() + "sOf" + relationDaoAdapter.getPrimaryName() + "DaoAdapter.java", "database/hibernate");
        processTemplate("RelationDaoHibernate.ftl", relationDaoHibernate, relationDaoHibernate.getSecondaryName() + "sOf" + relationDaoHibernate.getPrimaryName() + "DaoHibernate.java", "database/hibernate/dao");
        processTemplate("RelationDaoHibernateImpl.ftl", relationDaoHibernateImpl, relationDaoHibernateImpl.getSecondaryName() + "sOf" + relationDaoHibernateImpl.getPrimaryName() + "DaoHibernateImpl.java", "database/hibernate/dao");
        processTemplate("RelationReadByIdOperation.ftl", relationReadByIdOperation, relationReadByIdOperation.getSecondaryName() + "sOf" + relationReadByIdOperation.getPrimaryName() + "ReadByIdOperation.java", "database/hibernate/operations");
        processTemplate("RelationReadAllOperation.ftl", relationReadAllOperation, relationReadAllOperation.getSecondaryName() + "sOf" + relationReadAllOperation.getPrimaryName() + "ReadAllOperation.java", "database/hibernate/operations");
        processTemplate("RelationUpdateOperation.ftl", relationUpdateOperation, relationUpdateOperation.getSecondaryName() + "sOf" + relationUpdateOperation.getPrimaryName() + "UpdateOperation.java", "database/hibernate/operations");
        processTemplate("RelationQueryOperation.ftl", relationQueryOperation, relationQueryOperation.getSecondaryName() + "sOf" + relationQueryOperation.getPrimaryName() + "QueryOperation.java", "database/hibernate/operations");
        processTemplate("RelationDeleteBySecondaryIdOperation.ftl", relationDeleteBySecondaryIdOperation, relationDeleteBySecondaryIdOperation.getSecondaryName() + "sOf" + relationDeleteBySecondaryIdOperation.getPrimaryName() + "DeleteBySecondaryIdOperation.java", "database/hibernate/operations");
        processTemplate("RelationDeleteByPrimaryIdOperation.ftl", relationDeleteByPrimaryIdOperation, relationDeleteByPrimaryIdOperation.getSecondaryName() + "sOf" + relationDeleteByPrimaryIdOperation.getPrimaryName() + "DeleteByPrimaryIdOperation.java", "database/hibernate/operations");
        processTemplate("RelationPersistOperation.ftl", relationPersistOperation, relationPersistOperation.getSecondaryName() + "sOf" + relationPersistOperation.getPrimaryName() + "PersistOperation.java", "database/hibernate/operations");
        processTemplate("RelationDeleteByIdOperation.ftl", relationDeleteByIdOperation, relationDeleteByIdOperation.getSecondaryName() + "sOf" + relationDeleteByIdOperation.getPrimaryName() + "DeleteByIdOperation.java", "database/hibernate/operations");
        processTemplate("RelationDaoImpl.ftl", relationDaoImpl, relationDaoImpl.getSecondaryName() + "sOf" + relationDaoImpl.getPrimaryName() + "DaoImpl.java", "database/inmemory");
        processTemplate("DaoFactory.ftl", daoFactory, "DaoFactory.java", "database");
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