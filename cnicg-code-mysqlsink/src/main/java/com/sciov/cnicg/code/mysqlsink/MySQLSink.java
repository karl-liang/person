package com.sciov.cnicg.code.mysqlsink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

public class MySQLSink extends AbstractSink implements Configurable {

	private Logger logger = LoggerFactory.getLogger(MySQLSink.class);
	private String dbUrl;
	private String user;
	private String password;
	private PreparedStatement preparedStatement;
	private int batchSize;
//	private String fieldSeperator;
	private String insertSql;
//	private int parameterSize;
	Pattern pattern = null;

	PooledDataSource poolDataSource;

	public MySQLSink() {
		logger.info("MysqlSink start...");
	}

	@Override
	public void configure(Context context) {
		dbUrl = context.getString("dburl");
		Preconditions.checkNotNull(dbUrl, "dburl must be set!!");
		user = context.getString("user");
		Preconditions.checkNotNull(user, "user must be set!!");
		password = context.getString("password");
		Preconditions.checkNotNull(password, "password must be set!!");
		batchSize = context.getInteger("batchsize", 100);
		Preconditions.checkNotNull(batchSize > 0, "batchsize must be a positive number!!");
//		parameterSize = context.getInteger("parametersize", 0);
//		Preconditions.checkNotNull(parameterSize > 0, "parametersize must be a positive number!!");
		insertSql = context.getString("insertsql");
		Preconditions.checkNotNull(insertSql, "insertsql must be set!!");
//		fieldSeperator = context.getString("fieldseperator");
//		Preconditions.checkNotNull(fieldSeperator, "fieldseperator must be set!!");
//		pattern = Pattern.compile(fieldSeperator);
		
	}

	@Override
	public void start() {
		super.start();
		poolDataSource = new PooledDataSource("com.mysql.jdbc.Driver", dbUrl, user, password);

	}

	@Override
	public void stop() {
		super.stop();
		poolDataSource.forceCloseAll();
	}

	@Override
	public Status process() throws EventDeliveryException {
		Status result = Status.READY;
		Channel channel = getChannel();
		Transaction transaction = channel.getTransaction();
		Event event;
		String content;

		List<String> actions = Lists.newArrayList();
		transaction.begin();
		Connection conn = null;
		try {
			conn = poolDataSource.getConnection();
			conn.setAutoCommit(false);
			preparedStatement = conn.prepareStatement(insertSql);
			logger.info("===preparedStatement Alive===");

			for (int i = 0; i < batchSize; i++) {
				event = channel.take();
				if (event != null) {
					content = new String(event.getBody());
					actions.add(content);
				} else {
					result = Status.BACKOFF;
					break;
				}
			}
			logger.info("===insert batch size===" + actions.size());
			if (actions.size() > 0) {
				preparedStatement.clearBatch();
				for (String temp : actions) {
//					String[] datas = {};
//					if (temp != null && temp.trim().length() != 0) {
//						datas = pattern.split(temp);
//					}
//					for (int i = 0; i < parameterSize; i++) {
//						preparedStatement.setString(i + 1, datas[i]);
//					}
					preparedStatement.setString(1, temp);
					preparedStatement.addBatch();
				}
				preparedStatement.executeBatch();

				conn.commit();
			}
			transaction.commit();
		} catch (Throwable e) {
			try {
				transaction.rollback();
			} catch (Exception e2) {
				logger.error("Exception in rollback. Rollback might not have been" + "successful.", e2);
			}
			logger.error("Failed to commit transaction." + "Transaction rolled back.", e);
			Throwables.propagate(e);
		} finally {
			transaction.close();
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Failed to close connection", e);
				}
			}
		}

		return result;
	}
}