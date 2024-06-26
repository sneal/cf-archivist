package org.cftoolsuite.cfapp.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import org.cftoolsuite.cfapp.domain.ServiceInstanceDetail;
import org.cftoolsuite.cfapp.event.ServiceInstanceDetailRetrievedEvent;
import org.cftoolsuite.cfapp.event.SnapshotDetailRetrievedEvent;
import org.cftoolsuite.cfapp.service.ServiceInstanceDetailService;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class ServiceInstanceDetailTask implements ApplicationListener<SnapshotDetailRetrievedEvent> {

    private ServiceInstanceDetailService service;
    private ApplicationEventPublisher publisher;

    @Autowired
    public ServiceInstanceDetailTask(
            ServiceInstanceDetailService service,
            ApplicationEventPublisher publisher
            ) {
        this.service = service;
        this.publisher = publisher;
    }

    public void collect(List<ServiceInstanceDetail> serviceInstances) {
        log.info("ServiceInstanceDetailTask started");
        Flux
            .fromIterable(serviceInstances)
            .flatMap(service::save)
            .thenMany(service.findAll())
            .collectList()
            .subscribe(
                result -> {
                    publisher.publishEvent(new ServiceInstanceDetailRetrievedEvent(this).detail(result));
                    log.info("ServiceInstanceDetailTask completed");
                },
                error -> {
                    log.error("ServiceInstanceDetailTask terminated with error", error);
                }
            );
    }


    @Override
    public void onApplicationEvent(SnapshotDetailRetrievedEvent event) {
        collect(List.copyOf(event.getServiceInstances()));
    }
}
