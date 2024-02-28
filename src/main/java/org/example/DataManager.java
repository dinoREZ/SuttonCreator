package org.example;

import org.example.dataModels.DataModel;
import org.example.dataModels.MetaModel;
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
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {

    public static List<DataModel> getAllDataModels(MetaModel metaModel) {
        List<DataModel> dataModels = new ArrayList<>();

        dataModels.add(new DaoFactory(
                metaModel.getBasePackage(),
                metaModel.usesInMemory(),
                metaModel.getResources()
                        .stream()
                        .map(Resource::getName)
                        .collect(Collectors.toList()),
                metaModel.getResources()
                        .stream()
                        .collect(Collectors.toMap(Resource::getName,
                                resource -> resource.getSubResources()
                                        .stream()
                                        .map(Resource::getName)
                                        .collect(Collectors.toList())
                        ))
                ));

        for (Resource resource : metaModel.getResources()) {
            dataModels.add(new Model(resource.getName(), resource.getAttributes(), resource.getLinks(), metaModel.getBasePackage()));
            dataModels.add(new Dao(resource.getName(), resource.getQueries(), metaModel.getBasePackage()));

            if(metaModel.usesInMemory()) {
                dataModels.add(new DaoImpl(resource.getName(), resource.getQueries(), metaModel.getBasePackage()));
            }
            else {
                dataModels.add(new ModelDB(resource.getName(), resource.getAttributes(), metaModel.getBasePackage()));
                dataModels.add(new DaoHibernate(resource.getName(), resource.getQueries(), metaModel.getBasePackage()));
                dataModels.add(new DaoHibernateImpl(resource.getName(), resource.getQueries(), metaModel.getBasePackage()));
                dataModels.add(new DaoAdapter(resource.getName(), resource.getAttributes(), resource.getQueries(), metaModel.getBasePackage()));

                dataModels.add(new DeleteByIdOperation(resource.getName(), metaModel.getBasePackage()));
                dataModels.add(new PersistOperation(resource.getName(), metaModel.getBasePackage()));
                dataModels.add(new ReadAllOperation(resource.getName(), metaModel.getBasePackage()));
                dataModels.add(new ReadByIdOperation(resource.getName(), metaModel.getBasePackage()));
                dataModels.add(new UpdateOperation(resource.getName(), metaModel.getBasePackage()));

                for (Query query : resource.getQueries()) {
                    dataModels.add(new QueryOperation(resource.getName(), query, metaModel.getBasePackage()));
                }
            }

            for (Resource subResource : resource.getSubResources()) {
                dataModels.add(new Model(subResource.getName(), subResource.getAttributes(), subResource.getLinks(), metaModel.getBasePackage()));
                dataModels.add(new RelationDao(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getQueries()));

                if(metaModel.usesInMemory()) {
                    dataModels.add(new RelationDaoImpl(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getQueries()));
                }
                else {
                    dataModels.add(new ModelDB(subResource.getName(), subResource.getAttributes(), metaModel.getBasePackage()));
                    dataModels.add(new RelationDB(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationDaoHibernate(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getQueries()));
                    dataModels.add(new RelationDaoHibernateImpl(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getQueries()));
                    dataModels.add(new RelationDaoAdapter(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getAttributes(), subResource.getQueries()));

                    dataModels.add(new RelationDeleteByIdOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationDeleteByPrimaryIdOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationDeleteBySecondaryIdOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationPersistOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationReadAllOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationReadByIdOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                    dataModels.add(new RelationUpdateOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage()));

                    for (Query query : subResource.getQueries()) {
                        dataModels.add(new RelationQueryOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage(), query));
                    }
                }
            }
        }

        return dataModels;
    }
}
