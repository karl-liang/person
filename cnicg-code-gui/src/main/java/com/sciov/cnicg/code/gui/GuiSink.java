package com.sciov.cnicg.code.gui;

import java.awt.EventQueue;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.sciov.cnicg.code.gui.view.CnicgProducer;

public class GuiSink extends AbstractSink implements Configurable {

	private Logger logger = LoggerFactory.getLogger(GuiSink.class);
	
	static CnicgProducer producerWindows ;
	public GuiSink() {
		logger.info("GuiSink start...");
	}

	@Override
	public void configure(Context context) {
		String deviceFile = context.getString("devicefile");
		if(producerWindows == null) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						producerWindows = new CnicgProducer(deviceFile);
						producerWindows.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void start() {
		super.start();

	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public Status process() throws EventDeliveryException {
		Status result = Status.READY;
		Channel channel = getChannel();
		Transaction transaction = channel.getTransaction();
		Event event;

		transaction.begin();
		try {
			event = channel.take();
			if (event != null) {
				producerWindows.appendCodeContent(new String(event.getBody()));
			} else {
				result = Status.BACKOFF;
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
		}

		return result;
	}
}