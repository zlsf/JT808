
package model;

import java.util.Timer;
import java.util.TimerTask;

import JT808Data.DataServer;
import message.handler.TerminalLocationQueryReq;

/**
 * 定时执行的任务
 * @author Administrator
 *
 */
public class Task {

	public void doTask() {
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				DataServer.getInstance().sendMessageToAllDevice();
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 60 * 1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}
}
