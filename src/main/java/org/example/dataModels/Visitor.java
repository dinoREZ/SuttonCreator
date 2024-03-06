package org.example.dataModels;

import org.example.Query;
import org.example.Resource;
import org.example.dataModels.api.models.Model;
import org.example.dataModels.api.states.*;
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

    private final List<DataModel> dataModels;

    private String basePackage;
    private boolean usesInMemory;
    private Resource currentResource;
    private String subResourceName;
    private List<Resource> resources;


    public Visitor() {
        this.dataModels = new ArrayList<>();
        this.resources = new ArrayList<>();
    }

    public List<DataModel> getDataModels() {
        return dataModels;
    }

    @Override
    public void enterMetaModel(MetaModel metaModel) {
        this.basePackage = metaModel.getBasePackage();
        this.usesInMemory = metaModel.usesInMemory();
        this.resources = metaModel.getResources();
    }

    @Override
    public void exitMetaModel(MetaModel metaModel) {
        dataModels.add(new DaoFactory(basePackage, usesInMemory, resources));
        dataModels.add(new GetDispatcherState(resources, basePackage));
    }

    @Override
    public void enterResource(Resource resource) {
        this.currentResource = resource;
    }

    @Override
    public void exitResource(Resource resource) {
        dataModels.add(new Model(resource.getName(), resource.getAttributes(), resource.getLinks(), basePackage));
        dataModels.add(new Dao(resource.getName(), resource.getQueries(), basePackage));

        dataModels.add(new DeleteState(resource.getName(), resource.getStates(), resources, basePackage));
        dataModels.add(new GetCollectionState(resource.getName(), resource.getStates(), basePackage));
        dataModels.add(new GetState(resource.getName(), resource.isUseEtags(), resource.getCacheControl(), resource.getStates(), basePackage));
        dataModels.add(new PostState(resource.getName(), basePackage));
        dataModels.add(new PutState(resource.getName(), resource.isUseEtags(), resource.getStates(), basePackage));
        dataModels.add(new RelTypes(resource.getName(), basePackage));
        dataModels.add(new Uri(resource.getName(), resource.getPathElement(), basePackage));

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
        dataModels.add(new QueryOperation(currentResource.getName(), query, basePackage));
    }

    @Override
    public void enterSubResource(Resource subResource) {
        this.subResourceName = subResource.getName();
    }

    @Override
    public void exitSubResource(Resource subResource) {
        dataModels.add(new Model(subResource.getName(), subResource.getAttributes(), subResource.getLinks(), basePackage));
        dataModels.add(new RelationDao(currentResource.getName(), subResource.getName(), basePackage, subResource.getQueries()));

        dataModels.add(new DeleteRelationState(currentResource.getName(), subResource.getName(), subResource.getStates(), basePackage));
        dataModels.add(new GetRelationCollectionState(currentResource.getName(), subResource.getName(), subResource.getStates(), basePackage));
        dataModels.add(new GetRelationState(currentResource.getName(), subResource.getName(), subResource.isUseEtags(), subResource.getCacheControl(), subResource.getStates(), basePackage));
        dataModels.add(new PostRelationState(currentResource.getName(), subResource.getName(), basePackage));
        dataModels.add(new PutRelationState(currentResource.getName(), subResource.getName(), subResource.isUseEtags(), subResource.getStates(), basePackage));
        dataModels.add(new RelationRelTypes(currentResource.getName(), subResource.getName(), basePackage));
        dataModels.add(new RelationUri(currentResource.getName(), subResource.getName(), currentResource.getPathElement(), subResource.getPathElement(), basePackage));

        if(usesInMemory) {
            dataModels.add(new RelationDaoImpl(currentResource.getName(), subResource.getName(), basePackage, subResource.getQueries()));
        } else {
            dataModels.add(new ModelDB(subResource.getName(), subResource.getAttributes(), basePackage));
            dataModels.add(new RelationDB(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationDaoHibernate(currentResource.getName(), subResource.getName(), basePackage, subResource.getQueries()));
            dataModels.add(new RelationDaoHibernateImpl(currentResource.getName(), subResource.getName(), basePackage, subResource.getQueries()));
            dataModels.add(new RelationDaoAdapter(currentResource.getName(), subResource.getName(), basePackage, subResource.getAttributes(), subResource.getQueries()));

            dataModels.add(new RelationDeleteByIdOperation(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationDeleteByPrimaryIdOperation(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationDeleteBySecondaryIdOperation(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationPersistOperation(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationReadAllOperation(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationReadByIdOperation(currentResource.getName(), subResource.getName(), basePackage));
            dataModels.add(new RelationUpdateOperation(currentResource.getName(), subResource.getName(), basePackage));
        }
    }

    @Override
    public void enterSubQuery(Query subQuery) {

    }

    @Override
    public void exitSubQuery(Query subQuery) {
        dataModels.add(new RelationQueryOperation(currentResource.getName(), subResourceName, basePackage, subQuery));
    }

}
