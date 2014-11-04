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
package eu.aleon.aleoncean.packet.radio.userdata.teachin4bs;

import eu.aleon.aleoncean.values.LearnType4BS;

/**
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class UserData4BSTeachInVariant2 extends UserData4BSTeachIn {

    public UserData4BSTeachInVariant2() {
        super();
        setLearnType(LearnType4BS.WITH_EEP_NUM_WITH_MANU_ID);
    }

    public UserData4BSTeachInVariant2(final byte[] data) {
        super(data);
    }

}
