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

package kr.jclab.javautils.commons.platform;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.SystemUtils;

public class PlatformDetection {
    public static final String OS_WINDOWS = "windows";
    public static final String OS_OSX = "osx";
    public static final String OS_SOLARIS = "solaris";
    public static final String OS_LINUX = "linux";
    public static final String ARCH_PPC = "ppc";
    public static final String ARCH_X86_32 = "x86_32";
    public static final String ARCH_X86_64 = "x86_64";

    private static PlatformDetection m_instance = new PlatformDetection();
    private String os = null;
    private String arch = null;

    public PlatformDetection() {
        // resolve OS
        if (SystemUtils.IS_OS_WINDOWS) {
            this.os = OS_WINDOWS;
        } else if (SystemUtils.IS_OS_MAC_OSX) {
            this.os = OS_OSX;
        } else if (SystemUtils.IS_OS_SOLARIS) {
            this.os = OS_SOLARIS;
        } else if (SystemUtils.IS_OS_LINUX) {
            this.os = OS_LINUX;
        } else {
            return ;
        }

        // resolve architecture
        Map<String, String> archMap = new HashMap<String, String>();
        archMap.put("x86_32", ARCH_X86_32);
        archMap.put("i386", ARCH_X86_32);
        archMap.put("i486", ARCH_X86_32);
        archMap.put("i586", ARCH_X86_32);
        archMap.put("i686", ARCH_X86_32);
        archMap.put("x86_64", ARCH_X86_64);
        archMap.put("amd64", ARCH_X86_64);
        archMap.put("powerpc", ARCH_PPC);
        this.arch = archMap.get(SystemUtils.OS_ARCH);
    }

    public static String getOs() {
        return m_instance.os;
    }

    public static String getArch() {
        return m_instance.arch;
    }

    public static String getLibraryExtension() {
        if(SystemUtils.IS_OS_WINDOWS)
            return "dll";
        else if(SystemUtils.IS_OS_LINUX)
            return "so";
        return null;
    }

    @Override
    public String toString() {
        if(this.os == null || this.arch == null)
            return null;
        return os + "_" + arch;
    }
}