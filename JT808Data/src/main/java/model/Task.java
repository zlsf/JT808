
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
				TerminalLocationQueryReq req = new TerminalLocationQueryReq(null);
				DataServer.getInstance().sendMessageToAllDevice(req);
			}
		};
		Timer timer = new Timer();
		long delay = 0;
		long intevalPeriod = 5 * 1000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}
}
