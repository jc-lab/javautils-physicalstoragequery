package kr.jclab.javautils.physicalstoragequery;

import kr.jclab.javautils.physicalstoragequery.internal.Libphystorqueryjni;

public class VolumeItem {
    private Libphystorqueryjni m_jni;
    private long m_handle;
    private int m_index;

    VolumeItem(Libphystorqueryjni jni, long handle, int index)
    {
        m_jni = jni;
        m_handle = handle;
        m_index = index;
    }

    @Override
    protected void finalize() throws Throwable {
        m_jni.freeObject(m_handle);
        super.finalize();
    }

    /**
     * @return Device Name (e.g. sdb or PhysicalDrive1)
     */
    public String getVolumePhyDeviceName()
    {
        return m_jni.getVolumePhyDeviceName(m_handle, m_index);
    }

    /**
     * @return Mount path (e.g. /media/AAAA-BBBB or E:\)
     */
    public String getVolumeMountPath()
    {
        return m_jni.getVolumeMountPath(m_handle, m_index);
    }

}
