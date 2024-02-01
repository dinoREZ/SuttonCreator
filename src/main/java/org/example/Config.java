package org.example;

import org.apache.commons.lang3.StringUtils;
import org.example.dataModels.MetaModel;
import org.example.dataModels.models.Model;
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

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Config {

    public static String RESOURCE_PATH = "src/main/resources";


    public static String model_template = "Model.ftl";
    public static String dao_template = "Dao.ftl";
    public static String daoImpl_template = "DaoImpl.ftl";
    public static String daoAdapter_template = "DaoAdapter.ftl";
    public static String daoHibernate_template = "DaoHibernate.ftl";
    public static String daoHibernateImpl_template = "DaoHibernateImpl.ftl";
    public static String readByIdOperation_template = "ReadByIdOperation.ftl";
    public static String readAllOperation_template = "ReadAllOperation.ftl";
    public static String persistOperation_template = "PersistOperation.ftl";
    public static String deleteByIdOperation_template = "DeleteByIdOperation.ftl";
    public static String updateOperation_template = "UpdateOperation.ftl";
    public static String queryOperation_template = "QueryOperation.ftl";
    public static String modelDB_template = "ModelDB.ftl";
    public static String relationDB_template = "RelationDB.ftl";
    public static String relationDao_template = "RelationDao.ftl";
    public static String relationDaoAdapter_template = "RelationDaoAdapter.ftl";
    public static String relationDaoHibernate_template = "RelationDaoHibernate.ftl";
    public static String relationDaoHibernateImpl_template = "RelationDaoHibernateImpl.ftl";
    public static String relationReadByIdOperation_template = "RelationReadByIdOperation.ftl";
    public static String relationReadAllOperation_template = "RelationReadAllOperation.ftl";
    public static String relationUpdateOperation_template = "RelationUpdateOperation.ftl";
    public static String relationQueryOperation_template = "RelationQueryOperation.ftl";
    public static String relationDeleteBySecondaryIdOperation_template = "RelationDeleteBySecondaryIdOperation.ftl";
    public static String relationDeleteByPrimaryIdOperation_template = "RelationDeleteByPrimaryIdOperation.ftl";
    public static String relationPersistOperation_template = "RelationPersistOperation.ftl";
    public static String relationDeleteByIdOperation_template = "RelationDeleteByIdOperation.ftl";
    public static String relationDaoImpl_template = "RelationDaoImpl.ftl";
    public static String daoFactory_template = "DaoFactory.ftl";

    public static String model_path = "api/models";
    public static String dao_path = "database";
    public static String daoImpl_path = "database/inmemory";
    public static String daoAdapter_path = "database/hibernate";
    public static String daoHibernate_path = "database/hibernate/dao";
    public static String daoHibernateImpl_path = "database/hibernate/dao";
    public static String readByIdOperation_path = "database/hibernate/operations";
    public static String readAllOperation_path = "database/hibernate/operations";
    public static String persistOperation_path = "database/hibernate/operations";
    public static String deleteByIdOperation_path = "database/hibernate/operations";
    public static String updateOperation_path = "database/hibernate/operations";
    public static String queryOperation_path = "database/hibernate/operations";
    public static String modelDB_path = "database/hibernate/models";
    public static String relationDB_path = "database/hibernate/models";
    public static String relationDao_path = "database";
    public static String relationDaoAdapter_path = "database/hibernate";
    public static String relationDaoHibernate_path = "database/hibernate/dao";
    public static String relationDaoHibernateImpl_path = "database/hibernate/dao";
    public static String relationReadByIdOperation_path = "database/hibernate/operations";
    public static String relationReadAllOperation_path = "database/hibernate/operations";
    public static String relationUpdateOperation_path = "database/hibernate/operations";
    public static String relationQueryOperation_path = "database/hibernate/operations";
    public static String relationDeleteBySecondaryIdOperation_path = "database/hibernate/operations";
    public static String relationDeleteByPrimaryIdOperation_path = "database/hibernate/operations";
    public static String relationPersistOperation_path = "database/hibernate/operations";
    public static String relationDeleteByIdOperation_path = "database/hibernate/operations";
    public static String relationDaoImpl_path = "database/inmemory";
    public static String daoFactory_path = "database";


    public static String getPath(Object dataModel) {
        Map<Class<?>,String> paths = Map.ofEntries(
                Map.entry(Model.class, "api/models"),
                Map.entry(Dao.class, "database"),
                Map.entry(DaoImpl.class, "database/inmemory"),
                Map.entry(DaoAdapter.class, "database/hibernate"),
                Map.entry(DaoHibernate.class, "database/hibernate/dao"),
                Map.entry(DaoHibernateImpl.class, "database/hibernate/dao"),
                Map.entry(ReadByIdOperation.class, "database/hibernate/operations"),
                Map.entry(ReadAllOperation.class, "database/hibernate/operations"),
                Map.entry(PersistOperation.class, "database/hibernate/operations"),
                Map.entry(DeleteByIdOperation.class, "database/hibernate/operations"),
                Map.entry(UpdateOperation.class, "database/hibernate/operations"),
                Map.entry(QueryOperation.class, "database/hibernate/operations"),
                Map.entry(ModelDB.class, "database/hibernate/models"),
                Map.entry(RelationDB.class, "database/hibernate/models"),
                Map.entry(RelationDao.class, "database"),
                Map.entry(RelationDaoAdapter.class, "database/hibernate"),
                Map.entry(RelationDaoHibernate.class, "database/hibernate/dao"),
                Map.entry(RelationDaoHibernateImpl.class, "database/hibernate/dao"),
                Map.entry(RelationReadByIdOperation.class, "database/hibernate/operations"),
                Map.entry(RelationReadAllOperation.class, "database/hibernate/operations"),
                Map.entry(RelationUpdateOperation.class, "database/hibernate/operations"),
                Map.entry(RelationQueryOperation.class, "database/hibernate/operations"),
                Map.entry(RelationDeleteBySecondaryIdOperation.class, "database/hibernate/operations"),
                Map.entry(RelationDeleteByPrimaryIdOperation.class, "database/hibernate/operations"),
                Map.entry(RelationDeleteByIdOperation.class, "database/hibernate/operations"),
                Map.entry(RelationPersistOperation.class, "database/hibernate/operations"),
                Map.entry(RelationDaoImpl.class, "database/inmemory"),
                Map.entry(DaoFactory.class, "database")
        );

        return paths.get(dataModel.getClass());
    }

    public static String getTemplateName(Object dataModel) {
        return dataModel.getClass().getSimpleName() + ".ftl";
    }

    public static String getOutputName(Object dataModel) {
        Map<Class<?>, Function<Object, String>> outputNames = Map.ofEntries(
                Map.entry(Model.class, o -> ((Model) o).getName() + ".java"),
                Map.entry(Dao.class, o -> ((Dao) o).getName() + "Dao.java"),
                Map.entry(DaoImpl.class, o -> ((DaoImpl) o).getName() + "DaoImpl.java"),
                Map.entry(DaoAdapter.class, o -> ((DaoAdapter) o).getName() + "DaoAdapter.java"),
                Map.entry(DaoHibernate.class, o -> ((DaoHibernate) o).getName() + "DaoHibernate.java"),
                Map.entry(DaoHibernateImpl.class, o -> ((DaoHibernateImpl) o).getName() + "DaoHibernateImpl.java"),
                Map.entry(ReadByIdOperation.class, o -> ((ReadByIdOperation) o).getName() + "ReadByIdOperation.java"),
                Map.entry(ReadAllOperation.class, o -> ((ReadAllOperation) o).getName() + "ReadAllOperation.java"),
                Map.entry(PersistOperation.class, o -> ((PersistOperation) o).getName() + "PersistOperation.java"),
                Map.entry(DeleteByIdOperation.class, o -> ((DeleteByIdOperation) o).getName() + "DeleteByIdOperation.java"),
                Map.entry(UpdateOperation.class, o -> ((UpdateOperation) o).getName() + "UpdateOperation.java"),
                Map.entry(QueryOperation.class, o -> {
                            QueryOperation queryOperation = (QueryOperation) o;
                            return queryOperation.getName() + "QueryBy" +
                                    queryOperation.getQuery()
                                            .getAttributes()
                                            .keySet()
                                            .stream()
                                            .map(StringUtils::capitalize)
                                            .collect(Collectors.joining())
                                    + "Operation.java";
                        }),
                Map.entry(ModelDB.class, o -> ((ModelDB) o).getName() + "DB.java"),
                Map.entry(RelationDB.class, o -> {
                    RelationDB relationDB = (RelationDB) o;
                    return relationDB.getPrimaryName() + relationDB.getSecondaryName() + "DB.java";
                }),
                Map.entry(RelationDao.class, o -> {
                    RelationDao relationDao = (RelationDao) o;
                    return relationDao.getPrimaryName() + relationDao.getSecondaryName() + "Dao.java";
                }),
                Map.entry(RelationDaoAdapter.class, o -> {
                    RelationDaoAdapter relationDaoAdapter = (RelationDaoAdapter) o;
                    return relationDaoAdapter.getPrimaryName() + relationDaoAdapter.getSecondaryName() + "DaoAdapter.java";
                }),
                Map.entry(RelationDaoHibernate.class, o -> {
                    RelationDaoHibernate relationDaoHibernate = (RelationDaoHibernate) o;
                    return relationDaoHibernate.getPrimaryName() + relationDaoHibernate.getSecondaryName() + "DaoHibernate.java";
                }),
                Map.entry(RelationDaoHibernateImpl.class, o -> {
                    RelationDaoHibernateImpl relationDaoHibernateImpl = (RelationDaoHibernateImpl) o;
                    return relationDaoHibernateImpl.getPrimaryName() + relationDaoHibernateImpl.getSecondaryName() + "DaoHibernateImpl.java";
                }),
                Map.entry(RelationReadByIdOperation.class, o -> {
                    RelationReadByIdOperation relationReadByIdOperation = (RelationReadByIdOperation) o;
                    return relationReadByIdOperation.getPrimaryName() + relationReadByIdOperation.getSecondaryName() + "ReadByIdOperation.java";
                }),
                Map.entry(RelationReadAllOperation.class, o -> {
                    RelationReadAllOperation relationReadAllOperation = (RelationReadAllOperation) o;
                    return relationReadAllOperation.getPrimaryName() + relationReadAllOperation.getSecondaryName() + "ReadAllOperation.java";
                }),
                Map.entry(RelationUpdateOperation.class, o -> {
                    RelationUpdateOperation relationUpdateOperation = (RelationUpdateOperation) o;
                    return relationUpdateOperation.getPrimaryName() + relationUpdateOperation.getSecondaryName() + "UpdateOperation.java";
                }),
                Map.entry(RelationQueryOperation.class, o -> {
                    RelationQueryOperation relationQueryOperation = (RelationQueryOperation) o;
                    return relationQueryOperation.getPrimaryName() + relationQueryOperation.getSecondaryName() + "QueryBy" +
                            relationQueryOperation.getQuery()
                                    .getAttributes()
                                    .keySet()
                                    .stream()
                                    .map(StringUtils::capitalize)
                                    .collect(Collectors.joining())
                            + "Operation.java";
                }),
                Map.entry(RelationDeleteBySecondaryIdOperation.class, o -> {
                    RelationDeleteBySecondaryIdOperation relationDeleteBySecondaryIdOperation = (RelationDeleteBySecondaryIdOperation) o;
                    return relationDeleteBySecondaryIdOperation.getPrimaryName() + relationDeleteBySecondaryIdOperation.getSecondaryName() + "DeleteBySecondaryIdOperation.java";
                }),
                Map.entry(RelationDeleteByPrimaryIdOperation.class, o -> {
                    RelationDeleteByPrimaryIdOperation relationDeleteByPrimaryIdOperation = (RelationDeleteByPrimaryIdOperation) o;
                    return relationDeleteByPrimaryIdOperation.getPrimaryName() + relationDeleteByPrimaryIdOperation.getSecondaryName() + "DeleteByPrimaryIdOperation.java";
                }),
                Map.entry(RelationDeleteByIdOperation.class, o -> {
                    RelationDeleteByIdOperation relationDeleteByIdOperation = (RelationDeleteByIdOperation) o;
                    return relationDeleteByIdOperation.getPrimaryName() + relationDeleteByIdOperation.getSecondaryName() + "DeleteByIdOperation.java";
                }),
                Map.entry(RelationPersistOperation.class, o -> {
                    RelationPersistOperation relationPersistOperation = (RelationPersistOperation) o;
                    return relationPersistOperation.getPrimaryName() + relationPersistOperation.getSecondaryName() + "PersistOperation.java";
                }),
                Map.entry(RelationDaoImpl.class, o -> {
                    RelationDaoImpl relationDaoImpl = (RelationDaoImpl) o;
                    return relationDaoImpl.getPrimaryName() + relationDaoImpl.getSecondaryName() + "DaoImpl.java";
                }),
                Map.entry(DaoFactory.class, o -> "DaoFactory.java")
        );

        return outputNames.get(dataModel.getClass()).apply(dataModel);
    }

    public static void main(String[] args) {
        MetaModel model = new MetaModel();
        System.out.println(getTemplateName(model));
    }
}
