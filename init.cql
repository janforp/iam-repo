CREATE KEYSPACE IF NOT EXISTS iam WITH REPLICATION = {'class': 'SimpleStrategy','replication_factor':1};

CREATE TABLE IF NOT EXISTS iam.user (
	id text,
    name text,
	user_name text,
	password text,
	addresses text,
	email text,
	phone_numbers text,
	photo text,
	role text,
	creation_time timestamp,
	modification_time timestamp,
	create_user_id text,
	last_update_user_id text,
	status text,
	description text,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS iam.thing (
	id text,
    name text,
	type text,
	status text,
	description text,
	creation_time timestamp,
	modification_time timestamp,
	create_user_id text,
	last_update_user_id text,
	extension text,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS iam.relationship (
	id text,
    user_id text,
	target_id text,
	target_type text,
	description text,
	creation_time timestamp,
	PRIMARY KEY (id)
);