package org.example.dataModels;

import org.example.Query;
import org.example.Resource;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visitor implements IVisitor {

    private final List<Object> dataModels;

    private String basePackage;
    private boolean usesInMemory;
    private String resourceName;
    private String subResourceName;
    private final List<String> resourceNames;
    private final Map<String, List<String>> subResourceNames;


    public Visitor() {
        this.dataModels = new ArrayList<>();
        this.resourceNames = new ArrayList<>();
        this.subResourceNames = new HashMap<>();
    }

    public List<Object> getDataModels() {
        return dataModels;
    }

    @Override
    public void enterMetaModel(MetaModel metaModel) {
        this.basePackage = metaModel.getBasePackage();
        this.usesInMemory = metaModel.usesInMemory();
    }

    @Override
    public void exitMetaModel(MetaModel metaModel) {
        dataModels.add(new DaoFactory(basePackage, usesInMemory, resourceNames, subResourceNames));
    }

    @Override
    public void enterResource(Resource resource) {
        this.resourceName = resource.getName();
        this.resourceNames.add(resource.getName());
    }

    @Override
    public void exitResource(Resource resource) {
        dataModels.add(new Model(resource.getName(), resource.getAttributes(), resource.getLinks(), basePackage));
        dataModels.add(new Dao(resource.getName(), resource.getQueries(), basePackage));

        if(usesInMemory) {
            dataModels.add(new DaoImpl(resource.getName(), resource.getQueries(), basePackage));
        } else {
            dataModels.add(new ModelDB(resource.getName(), resource.getAttributes(), basePackage));
            dataModels.add(new DaoHibernate(resource.getName(), resource.getQueries(), basePackage));
            dataModels.add(new DaoHibernateImpl(resource.getName(), resource.getQueries(), basePackage));
            dataModels.add(new DaoAdapter(resource.getName(), resource.getAttributes(), resource.getQueries(), basePackage));

            dataModels.add(new DeleteByIdOperation(resource.getName(), basePackage));
            dataModels.add(new PersistOperation(resource.getName(), basePackage));
            dataModels.add(new ReadAllOperation(resource.getName(), basePackage));
            dataModels.add(new ReadByIdOperation(resource.getName(), basePackage));
            dataModels.add(new UpdateOperation(resource.getName(), basePackage));
        }
    }

    @Override
    public void enterQuery(Query query) {

    }

    @Override
    public void exitQuery(Query query) {
        dataModels.add(new QueryOperation(resourceName, query, basePackage));
    }

    @Override
    public void enterSubResource(Resource subResource) {
        this.subResourceName = subResource.getName();
        this.subResourceNames.putIfAbsent(resourceName, new ArrayList<>());
        this.subResourceNames.get(resourceName).add(subResource.getName());
    }

    @Override
    public void exitSubResource(Resource subResource) {
        dataModels.add(new Model(subResource.getName(), subResource.getAttributes(), subResource.getLinks(), basePackage));
        dataModels.add(new RelationDao(resourceName, subResource.getName(), basePackage, subResource.getQueries()));

        if(usesInMemory) {
            dataModels.add(new RelationDaoImpl(resourceName, subResource.getName(), basePackage, subResource.getQueries()));
        } else {
            dataModels.add(new ModelDB(subResource.getName(), subResource.getAttributes(), basePackage));
            dataModels.add(new RelationDB(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationDaoHibernate(resourceName, subResource.getName(), basePackage, subResource.getQueries()));
            dataModels.add(new RelationDaoHibernateImpl(resourceName, subResource.getName(), basePackage, subResource.getQueries()));
            dataModels.add(new RelationDaoAdapter(resourceName, subResource.getName(), basePackage, subResource.getAttributes(), subResource.getQueries()));

            dataModels.add(new RelationDeleteByIdOperation(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationDeleteByPrimaryIdOperation(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationDeleteBySecondaryIdOperation(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationPersistOperation(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationReadAllOperation(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationReadByIdOperation(resourceName, subResource.getName(), basePackage));
            dataModels.add(new RelationUpdateOperation(resourceName, subResource.getName(), basePackage));
        }
    }

    @Override
    public void enterSubQuery(Query subQuery) {

    }

    @Override
    public void exitSubQuery(Query subQuery) {
        dataModels.add(new RelationQueryOperation(resourceName, subResourceName, basePackage, subQuery));
    }

}
