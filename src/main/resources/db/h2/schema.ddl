CREATE TABLE IF NOT EXISTS time_keeper ( collection_time TIMESTAMP(9) PRIMARY KEY );

CREATE TABLE IF NOT EXISTS organizations ( foundation VARCHAR(50), id VARCHAR(50), org_name VARCHAR(250) NOT NULL, collection_time DATETIME NOT NULL, FOREIGN KEY (collection_time) REFERENCES time_keeper(collection_time), PRIMARY KEY (foundation, id, collection_time) );

CREATE TABLE IF NOT EXISTS spaces ( foundation VARCHAR(50), org_id VARCHAR(50) NOT NULL, space_id VARCHAR(50) NOT NULL, org_name VARCHAR(250) NOT NULL, space_name VARCHAR(250) NOT NULL, collection_time DATETIME NOT NULL, FOREIGN KEY (collection_time) REFERENCES time_keeper(collection_time), PRIMARY KEY (foundation, org_id, space_id, collection_time) );

CREATE TABLE IF NOT EXISTS application_detail ( foundation VARCHAR(50), organization VARCHAR(100), space VARCHAR(100), app_id VARCHAR(50), app_name VARCHAR(500), buildpack VARCHAR(500), buildpack_version VARCHAR(50), image VARCHAR(250), stack VARCHAR(25), running_instances INT, total_instances INT, memory_used BIGINT, disk_used BIGINT, memory_quota BIGINT, disk_quota BIGINT, urls VARCHAR(512000), last_pushed TIMESTAMP, last_event VARCHAR(50), last_event_actor VARCHAR(100), last_event_time TIMESTAMP, requested_state VARCHAR(25), buildpack_release_type VARCHAR(100), buildpack_release_date TIMESTAMP, buildpack_latest_version VARCHAR(50), buildpack_latest_url VARCHAR(500), collection_time DATETIME NOT NULL, FOREIGN KEY (collection_time) REFERENCES time_keeper(collection_time), PRIMARY KEY (foundation, app_id, collection_time) );

CREATE TABLE IF NOT EXISTS service_instance_detail ( foundation VARCHAR(50), organization VARCHAR(100), space VARCHAR(100), service_instance_id VARCHAR(50), service_name VARCHAR(100), service VARCHAR(100), description VARCHAR(1000), plan VARCHAR(50), type VARCHAR(30), bound_applications VARCHAR(512000), last_operation VARCHAR(50), last_updated TIMESTAMP, dashboard_url VARCHAR(250), requested_state VARCHAR(25), collection_time DATETIME NOT NULL, FOREIGN KEY (collection_time) REFERENCES time_keeper(collection_time), PRIMARY KEY (foundation, service_instance_id, collection_time) );

CREATE TABLE IF NOT EXISTS query_policy ( pk BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, id VARCHAR(50), description VARCHAR(1000), queries VARCHAR(512000), email_notification_template VARCHAR(512000) );

CREATE TABLE IF NOT EXISTS application_relationship ( foundation VARCHAR(50), organization VARCHAR(100), space VARCHAR(100), app_id VARCHAR(50), app_name VARCHAR(100), service_instance_id VARCHAR(50), service_name VARCHAR(100), service_offering VARCHAR(100), service_plan VARCHAR(50), service_type VARCHAR(30), collection_time DATETIME NOT NULL, FOREIGN KEY (collection_time) REFERENCES time_keeper(collection_time), PRIMARY KEY (foundation, app_id, service_instance_id, collection_time) );

CREATE TABLE IF NOT EXISTS space_users ( foundation VARCHAR(50), organization varchar(100), space varchar(100), auditors VARCHAR(512000), managers VARCHAR(512000), developers VARCHAR(512000), collection_time DATETIME NOT NULL, FOREIGN KEY (collection_time) REFERENCES time_keeper(collection_time), PRIMARY KEY (foundation, organization, space, collection_time) );
