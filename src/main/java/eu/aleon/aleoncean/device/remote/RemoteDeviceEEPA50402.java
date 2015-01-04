package eu.aleon.aleoncean.device.remote;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import eu.aleon.aleoncean.device.DeviceParameter;
import eu.aleon.aleoncean.device.DeviceParameterUpdatedInitiation;
import eu.aleon.aleoncean.device.IllegalDeviceParameterException;
import eu.aleon.aleoncean.device.RemoteDevice;
import eu.aleon.aleoncean.device.StandardDevice;
import eu.aleon.aleoncean.packet.EnOceanId;
import eu.aleon.aleoncean.packet.RadioPacket;
import eu.aleon.aleoncean.packet.radio.RadioPacket4BS;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataEEPA50402;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataScaleValueException;
import eu.aleon.aleoncean.rxtx.ESP3Connector;

/**
 *
 * @author Stephan Meyer <smeyersdev@gmail.com>
 */
public class RemoteDeviceEEPA50402 extends RemoteDeviceEEPA50401 {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDeviceEEPA50402.class);
  
    public RemoteDeviceEEPA50402(final ESP3Connector conn,
                                 final EnOceanId addressRemote,
                                 final EnOceanId addressLocal) {
        super(conn, addressRemote, addressLocal);
    }

    private void parseRadioPacket4BS(final RadioPacket4BS packet) {
        if (packet.isTeachIn()) {
            LOGGER.debug("Ignore teach-in packets.");
            return;
        }

        final UserDataEEPA50402 userData = new UserDataEEPA50402(packet.getUserDataRaw());
        try {
            setHumidity(DeviceParameterUpdatedInitiation.RADIO_PACKET, userData.getHumidity());
        } catch (final UserDataScaleValueException ex) {
            LOGGER.warn("Received humidity is invalid.");
        }

        try {
            setTemperature(DeviceParameterUpdatedInitiation.RADIO_PACKET, userData.getTemperature());
        } catch (final UserDataScaleValueException ex) {
            LOGGER.warn("Received temperature is invalid.");
        }
    }

    @Override
    public void parseRadioPacket(final RadioPacket packet) {
        if (packet instanceof RadioPacket4BS) {
            parseRadioPacket4BS((RadioPacket4BS) packet);
        } else {
            LOGGER.warn("Don't know how to handle radio choice 0x%02X.", packet.getChoice());
        }
    }
}
