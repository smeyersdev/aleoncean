/*
 * Copyright (c) 2014 aleon GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Markus Rathgeb - initial API and implementation and/or initial documentation
 */
package eu.aleon.aleoncean.device.local;

import eu.aleon.aleoncean.device.DeviceParameter;
import eu.aleon.aleoncean.device.DeviceParameterUpdatedInitiation;
import eu.aleon.aleoncean.device.IllegalDeviceParameterException;
import eu.aleon.aleoncean.device.RemoteDevice;
import eu.aleon.aleoncean.device.StandardDevice;
import eu.aleon.aleoncean.packet.EnOceanId;
import eu.aleon.aleoncean.packet.RadioPacket;
import eu.aleon.aleoncean.packet.radio.userdata.UserDataScaleValueException;
import eu.aleon.aleoncean.packet.radio.userdata.eepa53808.DimmingRange;
import eu.aleon.aleoncean.packet.radio.userdata.eepa53808.UserDataEEPA53808CMD02;
import eu.aleon.aleoncean.rxtx.ESP3Connector;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class LocalDeviceEEPA53808CMD02 extends StandardDevice implements RemoteDevice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDeviceEEPA53808CMD02.class);

    private Integer dimmingValue;
    private Boolean on;

    public LocalDeviceEEPA53808CMD02(final ESP3Connector conn,
                                     final EnOceanId addressRemote,
                                     final EnOceanId addressLocal) {
        super(conn, addressRemote, addressLocal);
    }

    public Integer getDimmingValue() {
        return dimmingValue;
    }

    public void setDimmingValue(final DeviceParameterUpdatedInitiation initiation,
                                final Integer dimmingValue) {
        final Integer old = this.dimmingValue;
        this.dimmingValue = dimmingValue;
        fireParameterChanged(DeviceParameter.POSITION_PERCENT, initiation, old, dimmingValue);
    }

    public Boolean getOn() {
        return on;
    }

    public void setOn(final DeviceParameterUpdatedInitiation initiation,
                      final Boolean on) {
        final Boolean old = this.on;
        this.on = on;
        fireParameterChanged(DeviceParameter.SWITCH, initiation, old, dimmingValue);
    }

    @Override
    public void parseRadioPacket(RadioPacket packet) {
        LOGGER.warn("Ey, I should not receive any packets.");
    }

    private void sendPacket() {
        try {
            final UserDataEEPA53808CMD02 userData = new UserDataEEPA53808CMD02();
            userData.setTeachIn(false);

            userData.setDimmingValueRelative(getDimmingValue());

            userData.setRampingTime(0);
            userData.setDimmingRange(DimmingRange.ABSOLUTE_VALUE);
            userData.setStoreFinalValue(true);

            userData.setSwitchingCommand(getOn());

            send(userData);
        } catch (NullPointerException ex) {
            LOGGER.debug("Do not send data, caused by null value.");
        } catch (UserDataScaleValueException ex) {
            LOGGER.warn("User data value invalid.");
        }
    }

    @Override
    protected void fillParameters(Set<DeviceParameter> params) {
        params.add(DeviceParameter.POSITION_PERCENT);
        params.add(DeviceParameter.SWITCH);
    }

    @Override
    public Object getByParameter(DeviceParameter parameter) throws IllegalDeviceParameterException {
        switch (parameter) {
            case POSITION_PERCENT:
                return getDimmingValue();
            case SWITCH:
                return getOn();
            default:
                return super.getByParameter(parameter);
        }
    }

    @Override
    public void setByParameter(DeviceParameter parameter, Object value) throws IllegalDeviceParameterException {
        assert DeviceParameter.getSupportedClass(parameter).isAssignableFrom(value.getClass());
        switch (parameter) {
            case POSITION_PERCENT:
                setDimmingValue(DeviceParameterUpdatedInitiation.SET_PARAMETER, (Integer) value);
                sendPacket();
                break;
            case SWITCH:
                setOn(DeviceParameterUpdatedInitiation.SET_PARAMETER, (Boolean) value);
                sendPacket();
                break;
            default:
                super.setByParameter(parameter, value);
                break;
        }
    }

}
