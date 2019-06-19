package customer.server.process.msg;

import customer.server.model.DeviceId;

/**
 * ·¢ÏûÏ¢
 *
 * @author
 * 
 *         <p>
 * 	Modification History:
 *         </p>
 *         <p>
 * 	Date Author Description
 *         </p>
 *         <p>
 * 	------------------------------------------------------------------
 *         </p>
 *         <p>
 *         </p>
 *         <p>
 *         </p>
 */
public interface SentMessage {

    /**
     * Gets the msg id.
     *
     * @return the msg id
     */
    int getMsgId();

    /**
     * Gets the stop id.
     *
     * @return the stop id
     */
    DeviceId getDeviceId();

    /**
     * Gets the msg body.
     *
     * @return the msg body
     */
    byte[] getMsgBody();
}
