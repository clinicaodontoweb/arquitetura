package com.odontoweb.arquitetura.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;

public class MultiTenantProvider implements MultiTenantConnectionProvider{
	private static final long serialVersionUID = 1L;

    private DataSource dataSource;
	
	public MultiTenantProvider(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public boolean isUnwrappableAs(@SuppressWarnings("rawtypes") Class unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("SET SCHEMA 'public'");
		} catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [public]", e);
		}
		connection.close();	
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		
		try {
			connection.createStatement().execute("SET SCHEMA '" + tenantIdentifier + "'");
		} catch (SQLException e) {
			throw new HibernateException("Could not alter JDBC connection to specified schema [" + tenantIdentifier + "]", e);
		}
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}
