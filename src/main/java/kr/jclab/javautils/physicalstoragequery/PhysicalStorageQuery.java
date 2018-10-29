package kr.jclab.javautils.physicalstoragequery;

import kr.jclab.javautils.physicalstoragequery.internal.Libphystorqueryjni;

import java.util.ArrayList;
import java.util.List;

public class PhysicalStorageQuery {
    public Libphystorqueryjni m_jni;

    public PhysicalStorageQuery() {
        m_jni = new Libphystorqueryjni();
    }

    public boolean loadLibrary() throws PlatformNotSupportedException {
        return LibraryLoader.load();
    }

    public List<VolumeItem> getVolumeList()
    {
        int i, count;
        long handle = m_jni.getVolumeList();
        List<VolumeItem> list;
        if(handle == 0)
            return null;
        list = new ArrayList<VolumeItem>();
        for(i=0, count = m_jni.getVolumeListCount(handle); i < count; i++) {
            list.add(new VolumeItem(m_jni, handle, i));
        }
        return list;
    }

    public PhyDeviceItem openPhyDevice(String deviceName) {
        long handle = m_jni.openPhyDevice(deviceName);
        if(handle == 0)
            return null;
        return new PhyDeviceItem(m_jni, handle);
    }

}
