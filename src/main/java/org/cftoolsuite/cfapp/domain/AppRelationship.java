package org.cftoolsuite.cfapp.domain;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@NoArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@EqualsAndHashCode
@ToString
@Table("application_relationship")
public class AppRelationship {

	private String foundation;
	private String organization;
	private String space;
	private String appId;
	private String appName;
	private String serviceInstanceId;
	private String serviceName;
	private String servicePlan;
	private String serviceType;
	@Column("collection_time")
	private LocalDateTime collectionDateTime;

	public String toCsv() {
		return String.join(",", wrap(getFoundation()), wrap(getOrganization()), wrap(getSpace()), wrap(getAppId()), wrap(getAppName()),
				wrap(getServiceInstanceId()), wrap(getServiceName()), wrap(getServicePlan()), wrap(getServiceType()),
				wrap(getCollectionDateTime() != null ? getCollectionDateTime().toString() : ""));
	}

	private static String wrap(String value) {
		return value != null ? StringUtils.wrap(value, '"') : StringUtils.wrap("", '"');
	}

	public static String headers() {
        return String.join(",", "foundation", "organization", "space", "application id",
                "application name", "service instance id", "service name", "service plan", "service type", "collection date/time");
	}

	public static AppRelationshipBuilder from(AppRelationship rel) {
		return AppRelationship
				.builder()
					.foundation(rel.getFoundation())
					.organization(rel.getOrganization())
					.space(rel.getSpace())
					.appId(rel.getAppId())
					.appName(rel.getAppName())
					.serviceInstanceId(rel.getServiceInstanceId())
					.serviceName(rel.getServiceName())
					.servicePlan(rel.getServicePlan())
					.serviceType(rel.getServiceType())
					.collectionDateTime(rel.getCollectionDateTime());
	}

}
