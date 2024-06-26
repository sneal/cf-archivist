package org.cftoolsuite.cfapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;

import org.cftoolsuite.cfapp.domain.Organization;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class R2dbcOrganizationRepository {

    private final R2dbcEntityOperations client;

    @Autowired
    public R2dbcOrganizationRepository(R2dbcEntityOperations client) {
        this.client = client;
    }

    public Mono<Void> deleteAll() {
        return
                client
                .delete(Organization.class)
                .all()
                .then();
    }

    public Flux<Organization> findAll() {
        return
                client
                .select(Organization.class)
                .matching(Query.empty().sort(Sort.by(Order.asc("org_name"), Order.desc("collection_time"))))
                .all();
    }

    public Mono<Organization> save(Organization entity) {
        return
                client
                .insert(entity);
    }

}
