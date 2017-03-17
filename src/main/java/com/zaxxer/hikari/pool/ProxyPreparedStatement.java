/*
 * Copyright (C) 2013 Brett Wooldridge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zaxxer.hikari.pool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import lombok.extern.slf4j.Slf4j;

/**
 * This is the proxy class for java.sql.PreparedStatement.
 *
 * @author Brett Wooldridge
 */
@Slf4j
public abstract class ProxyPreparedStatement extends ProxyStatement implements PreparedStatement {
	protected ProxyPreparedStatement(ProxyConnection connection, PreparedStatement statement) {
		super(connection, statement);
	}

	long WARN_TIME = 1000L;
	// **********************************************************************
	// Overridden java.sql.PreparedStatement Methods
	// **********************************************************************

	private void printSql(long t, String s) {
		if (t > WARN_TIME) {
			log.warn("/*fullsql slow " + t + "ms */" + s.substring(s.indexOf(": ") + 1));
		} else {
			log.debug("/*fullsql " + t + "ms */" + s.substring(s.indexOf(": ") + 1));
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean execute() throws SQLException {
		long t1 = System.currentTimeMillis();

		connection.markCommitStateDirty();
		try {
			boolean b = ((PreparedStatement) delegate).execute();
			return b;
		} finally {
			printSql(System.currentTimeMillis() - t1, delegate.toString());
		}

	}

	/** {@inheritDoc} */
	@Override
	public ResultSet executeQuery() throws SQLException {
		long t1 = System.currentTimeMillis();

		connection.markCommitStateDirty();
		try {
			ResultSet resultSet = ((PreparedStatement) delegate).executeQuery();
			ResultSet result = ProxyFactory.getProxyResultSet(connection, this, resultSet);
			return result;
		} finally {
			printSql(System.currentTimeMillis() - t1, delegate.toString());
		}

	}

	/** {@inheritDoc} */
	@Override
	public int executeUpdate() throws SQLException {
		long t1 = System.currentTimeMillis();

		connection.markCommitStateDirty();
		try {
			int n = ((PreparedStatement) delegate).executeUpdate();

			return n;
		} finally {
			printSql(System.currentTimeMillis() - t1, delegate.toString());
		}

	}

	/** {@inheritDoc} */
	@Override
	public long executeLargeUpdate() throws SQLException {
		long t1 = System.currentTimeMillis();

		connection.markCommitStateDirty();
		try {
			long n = ((PreparedStatement) delegate).executeLargeUpdate();
			return n;
		} finally {
			printSql(System.currentTimeMillis() - t1, delegate.toString());
		}

	}
}
