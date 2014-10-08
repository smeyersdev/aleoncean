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
package eu.aleon.aleoncean.packet.radio.userdata.eepa53808;

/**
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class UserDataEEPA53808Factory {

    public static UserDataEEPA53808 createFromUserData(final byte[] raw) {
        final UserDataEEPA53808 userData;

        switch (UserDataEEPA53808.getCommandId(raw)) {
            case UserDataEEPA53808CMD02.CMD:
                userData = new UserDataEEPA53808CMD02(raw);
                break;
            default:
                userData = new UserDataEEPA53808(raw);
                break;
        }

        return userData;
    }

    private UserDataEEPA53808Factory() {
    }

}
