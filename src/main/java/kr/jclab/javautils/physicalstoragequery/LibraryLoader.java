/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.jclab.javautils.physicalstoragequery;

import kr.jclab.javautils.commons.platform.PlatformDetection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LibraryLoader {
    private static WrappedBoolean m_inited = new WrappedBoolean();

    private static class WrappedBoolean {
        public boolean value = false;
    }

    public static boolean load() throws PlatformNotSupportedException {
        String platform_os = PlatformDetection.getOs();
        String platform_arch = PlatformDetection.getArch();
        String platform_libext = PlatformDetection.getLibraryExtension();
        InputStream ris;
        byte[] buffer;

        synchronized (m_inited) {

            if(m_inited.value)
                return true;

            if (platform_os == null || platform_arch == null) {
                // Not support OS
                throw new PlatformNotSupportedException();
            }

            ris = LibraryLoader.class.getResourceAsStream("/lib/" + platform_os + "/" + platform_arch + "/libphystorqueryjni." + platform_libext);
            if (ris == null) {
                throw new PlatformNotSupportedException();
            }

            try {
                int readlen;
                File tempFile = File.createTempFile("lpsqj", "." + platform_libext);
                FileOutputStream fos = new FileOutputStream(tempFile);
                buffer = new byte[1024];
                while ((readlen = ris.read(buffer)) != -1) {
                    fos.write(buffer, 0, readlen);
                }
                fos.close();
                ris.close();
                tempFile.deleteOnExit();
                System.load(tempFile.getAbsolutePath());

                m_inited.value = true;

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
