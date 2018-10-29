package kr.jclab.javautils.physicalstoragequery;

import kr.jclab.javautils.physicalstoragequery.internal.Libphystorqueryjni;

public class PhyDeviceItem {
    private Libphystorqueryjni m_jni;
    private long m_handle;

    PhyDeviceItem(Libphystorqueryjni jni, long handle)
    {
        m_jni = jni;
        m_handle = handle;
    }

    @Override
    protected void finalize() throws Throwable {
        m_jni.freeObject(m_handle);
        super.finalize();
    }

    public String getPhyDeviceVendorName()
    {
        return m_jni.getPhyDeviceVendorName(m_handle);
    }

    public String getPhyDeviceProductName()
    {
        return m_jni.getPhyDeviceProductName(m_handle);
    }

    public String getPhyDeviceSerialNumber()
    {
        return m_jni.getPhyDeviceSerialNumber(m_handle);
    }
}
