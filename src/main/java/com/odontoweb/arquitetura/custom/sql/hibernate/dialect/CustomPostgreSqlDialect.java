package com.odontoweb.arquitetura.custom.sql.hibernate.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class CustomPostgreSqlDialect extends PostgreSQL94Dialect{

	public CustomPostgreSqlDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
