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
package eu.aleon.aleoncean.device;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import eu.aleon.aleoncean.values.RockerSwitchAction;
import eu.aleon.aleoncean.values.WindowHandlePosition;

/**
 * Collection of device parameters.
 *
 * To optimize for access speed and memory footprint, we use static objects.
 * For every parameter you add, you have to extend the "infos" member of the "Infos" class.
 *
 * - BUTTON_DIM_A/B: RockerSwitchAction
 * - ENERGY_WS: long, power value, unit watt seconds
 * - HUMIDITY_PERCENT: double, humidity, unit percent
 * - ILLUMINATION_LUX: double, illumination, unit lux
 * - MOTION: boolean, motion
 * - OCCUPANCY_BUTTON: boolean, true: pressed, false: released
 * - POSITION_PERCENT: integer, a position value, unit percent
 * - POWER_W: long, power value, unit watt
 * - SETPOINT_POSITION_PERCENT: integer, a set point position value, unit percent
 * - SETPOINT_TEMPERATURE_CELSIUS: double, a set point temperature value, unit celsius
 * - SUPPLY_VOLTAGE_V: double, unit volt
 * - SWITCH: boolean, true: on, false: off
 * - TEMPERATURE_CELSIUS: double, temperature, unit degree Celsius
 * - TEMPERATURE_CONTROL_ENABLE: boolean, flag if a temperature control algorithm is enabled
 * - TEMPERATURE_CONTROL_CUR_TEMP: double, the current temperature used for a temperature control algorithm
 * - WINDOW_HANDLE_POSITION: WindowHandlePosition
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public enum DeviceParameter {

    BUTTON_DIM_A,
    BUTTON_DIM_B,
    ENERGY_WS,
    HUMIDITY_PERCENT,
    ILLUMINATION_LUX,
    MOTION,
    OCCUPANCY_BUTTON,
    POSITION_PERCENT,
    POWER_W,
    SETPOINT_POSITION_PERCENT,
    SETPOINT_TEMPERATURE_CELSIUS,
    SUPPLY_VOLTAGE_V,
    SWITCH,
    TEMPERATURE_CELSIUS,
    TEMPERATURE_CONTROL_ENABLE,
    TEMPERATURE_CONTROL_CUR_TEMP,
    WINDOW_HANDLE_POSITION,

    TMP_RECV_SERVICE_ON,
    TMP_RECV_ENERGY_INPUT_ENABLED,
    TMP_RECV_ENERGY_STORAGE_SUFFICIENT,
    TMP_RECV_CHANGE_BATTERY,
    TMP_RECV_COVER_OPEN,
    TMP_RECV_TEMPERATURE_SENSOR_FAILURE,
    TMP_RECV_WINDOW_OPEN,
    TMP_RECV_ACTUATOR_OBSTRUCTED,
    TMP_SEND_RUN_INIT_SEQUENCE,
    TMP_SEND_LIFT_SET,
    TMP_SEND_VALVE_OPEN,
    TMP_SEND_VALVE_CLOSED,
    TMP_SEND_REDUCED_ENERGY_CONSUMPTION;

    private static class Info {

        private final DeviceParameter parameter;
        private final Class<?> clazz;
        private final String name;

        public Info(final DeviceParameter parameter,
                    final Class<?> clazz) {
            this(parameter, parameter.name(), clazz);
        }

        public Info(final DeviceParameter parameter,
                    final String name,
                    final Class<?> clazz) {
            this.parameter = parameter;
            this.name = name;
            this.clazz = clazz;
        }

        public DeviceParameter getParameter() {
            return parameter;
        }

        public Class<?> getSupportedClass() {
            return clazz;
        }

        public String getName() {
            return name;
        }
    }

    private static class Infos {

        private static final List<Info> INFOS = Collections.unmodifiableList(Arrays
                .asList(
                        new Info(DeviceParameter.BUTTON_DIM_A, "BUTTON_DIM_A", RockerSwitchAction.class),
                        new Info(DeviceParameter.BUTTON_DIM_B, "BUTTON_DIM_B", RockerSwitchAction.class),
                        new Info(DeviceParameter.ENERGY_WS, "ENERGY_WS", Long.class),
                        new Info(DeviceParameter.HUMIDITY_PERCENT, "HUMIDITY_PERCENT", Double.class),
                        new Info(DeviceParameter.ILLUMINATION_LUX, "ILLUMINATION_LUX", Double.class),
                        new Info(DeviceParameter.MOTION, "MOTION", Boolean.class),
                        new Info(DeviceParameter.OCCUPANCY_BUTTON, "OCCUPANCY_BUTTON", Boolean.class),
                        new Info(DeviceParameter.POSITION_PERCENT, Integer.class),
                        new Info(DeviceParameter.POWER_W, "POWER_W", Long.class),
                        new Info(DeviceParameter.SETPOINT_POSITION_PERCENT, Integer.class),
                        new Info(DeviceParameter.SETPOINT_TEMPERATURE_CELSIUS, Double.class),
                        new Info(DeviceParameter.SUPPLY_VOLTAGE_V, "SUPPLY_VOLTAGE_V", Double.class),
                        new Info(DeviceParameter.SWITCH, "SWITCH", Boolean.class),
                        new Info(DeviceParameter.TEMPERATURE_CELSIUS, "TEMPERATURE_CELSIUS", Double.class),
                        new Info(DeviceParameter.TEMPERATURE_CONTROL_CUR_TEMP, Double.class),
                        new Info(DeviceParameter.TEMPERATURE_CONTROL_ENABLE, Boolean.class),
                        new Info(DeviceParameter.WINDOW_HANDLE_POSITION, "WINDOW_HANDLE_POSITION", WindowHandlePosition.class),
                        new Info(DeviceParameter.TMP_RECV_SERVICE_ON, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_ENERGY_INPUT_ENABLED, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_ENERGY_STORAGE_SUFFICIENT, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_CHANGE_BATTERY, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_COVER_OPEN, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_TEMPERATURE_SENSOR_FAILURE, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_WINDOW_OPEN, Boolean.class),
                        new Info(DeviceParameter.TMP_RECV_ACTUATOR_OBSTRUCTED, Boolean.class),
                        new Info(DeviceParameter.TMP_SEND_RUN_INIT_SEQUENCE, Boolean.class),
                        new Info(DeviceParameter.TMP_SEND_LIFT_SET, Boolean.class),
                        new Info(DeviceParameter.TMP_SEND_VALVE_OPEN, Boolean.class),
                        new Info(DeviceParameter.TMP_SEND_VALVE_CLOSED, Boolean.class),
                        new Info(DeviceParameter.TMP_SEND_REDUCED_ENERGY_CONSUMPTION, Boolean.class)
                ));

        public static Info getByParameter(final DeviceParameter parameter) {
            for (final Info info : INFOS) {
                if (info.getParameter().equals(parameter)) {
                    return info;
                }
            }
            return null;
        }

        public static Info getByName(final String name) {
            for (final Info info : INFOS) {
                if (info.getName().equals(name)) {
                    return info;
                }
            }
            return null;
        }

        private Infos() {
        }

    }

    /**
     * Get device parameter by the correspondent string representation.
     *
     * @param parameter The string that identifies the parameter.
     * @return Return a device parameter if the string is known, null if it is unknown.
     */
    public static DeviceParameter fromString(final String parameter) {
        final Info info = Infos.getByName(parameter);
        if (info != null) {
            return info.getParameter();
        } else {
            return null;
        }
    }

    public static Class<?> getSupportedClass(final DeviceParameter parameter) throws IllegalDeviceParameterException {
        final Info info = Infos.getByParameter(parameter);
        if (info != null) {
            return info.getSupportedClass();
        } else {
            throw new IllegalDeviceParameterException(String.format("Unknown device parameter: %s", parameter));
        }
    }

}
