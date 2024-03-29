package org.example;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.example.dataModels.*;
import org.example.dataModels.api.models.Model;
import org.example.dataModels.api.queries.ReadAllQuery;
import org.example.dataModels.api.queries.RelationQuery;
import org.example.dataModels.api.queries.RelationReadAllQuery;
import org.example.dataModels.api.rateLimiting.AnyApiKeyRateLimiter;
import org.example.dataModels.api.security.NoAuthNeededAuthenticationProvider;
import org.example.dataModels.api.services.DispatcherService;
import org.example.dataModels.api.services.Service;
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
import java.util.List;
import java.util.stream.Collectors;

public class DataManager {

    public static List<DataModel> getAllDataModels(MetaModel metaModel) {
        List<DataModel> dataModels = new ArrayList<>();

        dataModels.add(new DaoFactory(metaModel.getBasePackage(), metaModel.usesInMemory(), metaModel.getResources()));
        dataModels.add(new GetDispatcherState(metaModel.getResources(), metaModel.getBasePackage()));
        dataModels.add(new DispatcherService(metaModel.getBasePackage()));
        dataModels.add(new Application(metaModel.getResources(), metaModel.getBasePackage()));
        dataModels.add(new Start(metaModel.getBaseContextPath(), metaModel.getBasePackage()));
        dataModels.add(new DatabaseInstaller(metaModel.getBasePackage()));
        dataModels.add(new NoAuthNeededAuthenticationProvider(metaModel.getBasePackage()));
        dataModels.add(new AnyApiKeyRateLimiter(metaModel.getBasePackage()));

        for (Resource resource : metaModel.getResources()) {
            dataModels.add(new Model(resource.getName(), resource.getAttributes(), resource.getLinks(), metaModel.getBasePackage()));
            dataModels.add(new Dao(resource.getName(), resource.getQueries(), metaModel.getBasePackage()));

            dataModels.add(new DeleteState(resource.getName(), metaModel.getResources(), metaModel.getBasePackage()));
            dataModels.add(new GetCollectionState(resource.getName(), metaModel.getBasePackage()));
            dataModels.add(new GetState(resource.getName(), resource.isUseEtags(), resource.getCacheControl(), metaModel.getBasePackage()));
            dataModels.add(new PostState(resource.getName(), metaModel.getBasePackage()));
            dataModels.add(new PutState(resource.getName(), resource.isUseEtags(), metaModel.getBasePackage()));
            dataModels.add(new RelTypes(resource.getName(), metaModel.getBasePackage()));
            dataModels.add(new Uri(resource.getName(), resource.getPathElement(), metaModel.getBasePackage()));

            dataModels.add(new Service(resource, metaModel.getBasePackage()));
            dataModels.add(new ReadAllQuery(resource.getName(), metaModel.getBasePackage()));

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
            }

            for (Query query : resource.getQueries()) {
                if(!metaModel.usesInMemory()) {
                    dataModels.add(new QueryOperation(resource.getName(), query, metaModel.getBasePackage()));
                }
                dataModels.add(new org.example.dataModels.api.queries.Query(resource.getName(), query, metaModel.getBasePackage()));
            }

            for (Resource subResource : resource.getSubResources()) {
                dataModels.add(new RelationDao(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getQueries()));

                dataModels.add(new DeleteRelationState(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                dataModels.add(new GetRelationCollectionState(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                dataModels.add(new GetRelationState(resource.getName(), subResource.getName(), subResource.isUseEtags(), subResource.getCacheControl(), metaModel.getBasePackage()));
                dataModels.add(new PostRelationState(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                dataModels.add(new PutRelationState(resource.getName(), subResource.getName(), subResource.isUseEtags(), metaModel.getBasePackage()));
                dataModels.add(new RelationRelTypes(resource.getName(), subResource.getName(), metaModel.getBasePackage()));
                dataModels.add(new RelationUri(resource.getName(), subResource.getName(), resource.getPathElement(), subResource.getPathElement(), metaModel.getBasePackage()));

                dataModels.add(new RelationReadAllQuery(resource.getName(), subResource.getName(), metaModel.getBasePackage()));

                if(metaModel.usesInMemory()) {
                    dataModels.add(new RelationDaoImpl(resource.getName(), subResource.getName(), metaModel.getBasePackage(), subResource.getQueries()));
                }
                else {
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
                }

                for (Query query : subResource.getQueries()) {
                    if(!metaModel.usesInMemory()) {
                        dataModels.add(new RelationQueryOperation(resource.getName(), subResource.getName(), metaModel.getBasePackage(), query));
                    }
                    dataModels.add(new RelationQuery(resource.getName(), subResource.getName(), query, metaModel.getBasePackage()));
                }
            }
        }

        return dataModels;
    }

    public static List<DaoFactory> getAllDaoFactories(MetaModel metaModel) {
        return List.of(new DaoFactory(metaModel.getBasePackage(), metaModel.usesInMemory(), metaModel.getResources()));
    }

    public static List<Model> getAllModels(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new Model(resource.getName(), resource.getAttributes(), resource.getLinks(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<Dao> getAllDaos(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new Dao(resource.getName(), resource.getQueries(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<DaoImpl> getAllDaoImpls(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new DaoImpl(resource.getName(), resource.getQueries(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    public static List<ModelDB> getAllModelDBs(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new ModelDB(resource.getName(), resource.getAttributes(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<DaoHibernate> getAllDaoHibernates(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new DaoHibernate(resource.getName(), resource.getQueries(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<DaoHibernateImpl> getAllDaoHibernateImpls(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new DaoHibernateImpl(resource.getName(), resource.getQueries(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<DaoAdapter> getAllDaoAdapters(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new DaoAdapter(resource.getName(), resource.getAttributes(), resource.getQueries(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<DeleteByIdOperation> getAllDeleteByIdOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new DeleteByIdOperation(resource.getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<PersistOperation> getAllPersistOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new PersistOperation(resource.getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<ReadAllOperation> getAllReadAllOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new ReadAllOperation(resource.getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<ReadByIdOperation> getAllReadByIdOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new ReadByIdOperation(resource.getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<UpdateOperation> getAllUpdateOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .map(resource -> new UpdateOperation(resource.getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<QueryOperation> getAllQueryOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getQueries()
                            .stream()
                            .map(query -> Pair.of(resource.getName(), query))
                    )
                    .map(queryPair -> new QueryOperation(queryPair.getKey(), queryPair.getValue(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDao> getAllRelationDaos(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource.getName(), subResource))
                )
                .map(subResourcePair -> new RelationDao(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage(), subResourcePair.getValue().getQueries()))
                .collect(Collectors.toList());
    }

    public static List<RelationDaoImpl> getAllRelationDaoImpls(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDaoImpl(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage(), subResourcePair.getValue().getQueries()))
                    .collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    public static List<RelationDB> getAllRelationDBs(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDB(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDaoHibernate> getAllRelationDaoHibernate(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDaoHibernate(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage(), subResourcePair.getValue().getQueries()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDaoHibernateImpl> getAllRelationDaoHibernateImpl(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDaoHibernateImpl(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage(), subResourcePair.getValue().getQueries()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDaoAdapter> getAllRelationDaoAdapter(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDaoAdapter(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage(), subResourcePair.getValue().getAttributes(), subResourcePair.getValue().getQueries()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDeleteByIdOperation> getAllRelationDeleteByIdOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDeleteByIdOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDeleteByPrimaryIdOperation> getAllRelationDeleteByPrimaryIdOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDeleteByPrimaryIdOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationDeleteBySecondaryIdOperation> getAllRelationDeleteBySecondaryIdOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationDeleteBySecondaryIdOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationPersistOperation> getAllRelationPersistOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationPersistOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationReadAllOperation> getAllRelationReadAllOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationReadAllOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationReadByIdOperation> getAllRelationReadByIdOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationReadByIdOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationUpdateOperation> getAllRelationUpdateOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                    )
                    .map(subResourcePair -> new RelationUpdateOperation(subResourcePair.getKey(), subResourcePair.getValue().getName(), metaModel.getBasePackage()))
                    .collect(Collectors.toList());
        }
    }

    public static List<RelationQueryOperation> getAllRelationQueryOperations(MetaModel metaModel) {
        if(metaModel.usesInMemory()) {
            return List.of();
        } else {
            return metaModel.getResources()
                    .stream()
                    .flatMap(resource -> resource.getSubResources()
                            .stream()
                            .map(subResource -> Pair.of(resource.getName(), subResource))
                            .flatMap(subResourcePair -> subResourcePair.getValue()
                                    .getQueries()
                                    .stream()
                                    .map(query -> Triple.of(subResourcePair.getKey(), subResourcePair.getValue().getName(), query))
                            )
                    )
                    .map(queryTriple -> new RelationQueryOperation(queryTriple.getLeft(), queryTriple.getMiddle(), metaModel.getBasePackage(), queryTriple.getRight()))
                    .collect(Collectors.toList());
        }
    }

    public static List<DeleteState> getAllDeleteStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new DeleteState(resource.getName(), metaModel.getResources(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<GetCollectionState> getAllGetCollectionStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new GetCollectionState(resource.getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<GetDispatcherState> getAllDispatcherStates(MetaModel metaModel) {
        return List.of(new GetDispatcherState(metaModel.getResources(), metaModel.getBasePackage()));
    }

    public static List<GetState> getAllGetStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new GetState(resource.getName(), resource.isUseEtags(), resource.getCacheControl(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<PostState> getAllPostStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new PostState(resource.getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<PutState> getAllPutStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new PutState(resource.getName(), resource.isUseEtags(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<RelTypes> getAllRelTypes(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new RelTypes(resource.getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<Uri> getAllUris(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new Uri(resource.getName(), resource.getPathElement(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<DeleteRelationState> getAllDeleteRelationStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new DeleteRelationState(pair.getLeft().getName(), pair.getRight().getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<GetRelationCollectionState> getAllGetRelationCollectionStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new GetRelationCollectionState(pair.getLeft().getName(), pair.getRight().getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<GetRelationState> getAllGetRelationStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new GetRelationState(pair.getLeft().getName(), pair.getRight().getName(), pair.getRight().isUseEtags(), pair.getRight().getCacheControl(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<PostRelationState> getAllPostRelationStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new PostRelationState(pair.getLeft().getName(), pair.getRight().getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<PutRelationState> getAllPutRelationStates(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new PutRelationState(pair.getLeft().getName(), pair.getRight().getName(), pair.getRight().isUseEtags(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<RelationRelTypes> getAllRelationRelTypes(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new RelationRelTypes(pair.getLeft().getName(), pair.getRight().getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<RelationUri> getAllRelationUris(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource, subResource))
                )
                .map(pair -> new RelationUri(pair.getLeft().getName(), pair.getRight().getName(), pair.getLeft().getPathElement(), pair.getRight().getPathElement(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<Service> getAllServices(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new Service(resource, metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<DispatcherService> getAllDispatcherServices(MetaModel metaModel) {
        return List.of(new DispatcherService(metaModel.getBasePackage()));
    }

    public static List<org.example.dataModels.api.queries.Query> getAllQueries(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getQueries()
                        .stream()
                        .map(query -> Pair.of(resource.getName(), query))
                )
                .map(pair -> new org.example.dataModels.api.queries.Query(pair.getLeft(), pair.getRight(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<RelationQuery> getAllRelationQueries(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource.getName(), subResource))
                        .flatMap(subResourcePair -> subResourcePair.getValue()
                                .getQueries()
                                .stream()
                                .map(query -> Triple.of(subResourcePair.getKey(), subResourcePair.getValue().getName(), query))
                        )
                )
                .map(triple -> new RelationQuery(triple.getLeft(), triple.getMiddle(), triple.getRight(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<Application> getAllApplications(MetaModel metaModel) {
        return List.of(new Application(metaModel.getResources(), metaModel.getBasePackage()));
    }

    public static List<Start> getAllStarts(MetaModel metaModel) {
        return List.of(new Start(metaModel.getBaseContextPath(), metaModel.getBasePackage()));
    }

    public static List<DatabaseInstaller> getAllDatabaseInstallers(MetaModel metaModel) {
        return List.of(new DatabaseInstaller(metaModel.getBasePackage()));
    }

    public static List<NoAuthNeededAuthenticationProvider> getAllNoAuthNeededAuthenticationProviders(MetaModel metaModel) {
        return List.of(new NoAuthNeededAuthenticationProvider(metaModel.getBasePackage()));
    }

    public static List<AnyApiKeyRateLimiter> getAllAnyApiKeyRateLimiters(MetaModel metaModel) {
        return List.of(new AnyApiKeyRateLimiter(metaModel.getBasePackage()));
    }

    public static List<ReadAllQuery> getAllReadAllQueries(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .map(resource -> new ReadAllQuery(resource.getName(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }

    public static List<RelationReadAllQuery> getAllRelationReadAllQueries(MetaModel metaModel) {
        return metaModel.getResources()
                .stream()
                .flatMap(resource -> resource.getSubResources()
                        .stream()
                        .map(subResource -> Pair.of(resource.getName(), subResource.getName()))
                )
                .map(pair -> new RelationReadAllQuery(pair.getLeft(), pair.getRight(), metaModel.getBasePackage()))
                .collect(Collectors.toList());
    }
}
