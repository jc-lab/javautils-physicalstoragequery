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

package kr.jclab.javautils.physicalstoragequery.internal;

public class Libphystorqueryjni {
    /**
     * @param handle object handle
     * */
    public native void freeObject(long handle);

    /**
     * @return handle. Must be free by freeObject.
     * */
    public native long getVolumeList();

    /**
     * @param listHandle volumeListHandle by getVolumeList
     * @return array size
     */
    public native int getVolumeListCount(long listHandle);

    /**
     * @param listHandle volumeListHandle by getVolumeList
     * @param index volume index
     * @return Device Name (e.g. sdb or PhysicalDrive1)
     */
    public native String getVolumePhyDeviceName(long listHandle, int index);

    /**
     * @param listHandle volumeListHandle by getVolumeList
     * @param index volume index
     * @return Mount path (e.g. /media/AAAA-BBBB or E:\)
     */
    public native String getVolumeMountPath(long listHandle, int index);

    /**
     * @param deviceName (e.g. sdb or PhysicalDrive1)
     * @return deviceHandle. Must be free by freeObject.
     */
    public native long openPhyDevice(String deviceName);

    /**
     * @param deviceHandle deviceHandle
     * @return vendor number
     */
    public native String getPhyDeviceVendorName(long deviceHandle);

    /**
     * @param deviceHandle deviceHandle
     * @return product number
     */
    public native String getPhyDeviceProductName(long deviceHandle);

    /**
     * @param deviceHandle deviceHandle
     * @return serial number
     */
    public native String getPhyDeviceSerialNumber(long deviceHandle);
}
