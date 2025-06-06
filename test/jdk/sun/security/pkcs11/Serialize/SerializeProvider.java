/*
 * Copyright (c) 2003, 2025, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 4921802
 * @summary Test that the SunPKCS11 provider can be serialized
 * @author Andreas Sterbenz
 * @library /test/lib ..
 * @modules jdk.crypto.cryptoki
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Provider;
import java.security.Security;

public class SerializeProvider extends PKCS11Test {

    public void main(Provider p) throws Exception {

        if (Security.getProvider(p.getName()) != p) {
            Security.addProvider(p);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oout = new ObjectOutputStream(out);

        oout.writeObject(p);
        oout.close();

        byte[] data = out.toByteArray();

        InputStream in = new ByteArrayInputStream(data);
        ObjectInputStream oin = new ObjectInputStream(in);

        Provider p2 = (Provider) oin.readObject();

        System.out.println("Reconstituted: " + p2);

        if (p != p2) {
            throw new Exception("Provider object mismatch");
        }
    }

    public static void main(String[] args) throws Exception {
        main(new SerializeProvider());
    }

}
