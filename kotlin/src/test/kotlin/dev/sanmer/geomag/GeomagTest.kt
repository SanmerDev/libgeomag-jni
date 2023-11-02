package dev.sanmer.geomag

import kotlin.test.Test
import kotlin.test.assertTrue

class GeomagTest {
    @Test
    fun wmm() {
        val t = Geomag.toDecimalYears(2023, 11, 1, 0, 0, 0)
        val m = Geomag.wmm(102.0, 24.0, 1.9, t)
        
        assertTrue(m.x - 37637.0 < 1.0)
        assertTrue(m.xDot - 4.0 < 1.0)
        assertTrue(m.y - (-1100.0) < 1.0)
        assertTrue(m.yDot - (-40.0) < 1.0)
        assertTrue(m.z - 28826.0 < 1.0)
        assertTrue(m.zDot - 106.0 < 1.0)
        assertTrue(m.h - 37653.0 < 1.0)
        assertTrue(m.hDot - 5.0 < 1.0)
        assertTrue(m.f - 47420.0 < 1.0)
        assertTrue(m.fDot - 69.0 < 1.0)
        assertTrue(m.d - (-0.02) < 0.01)
        assertTrue(m.dDot - (-0.001) < 0.001)
        assertTrue(m.i - 0.6 < 0.1)
        assertTrue(m.iDot - 0.001 < 0.001)
    }

    @Test
    fun igrf() {
        val t = Geomag.toDecimalYears(2023, 11, 1, 0, 0, 0)
        val m = Geomag.igrf(102.0, 24.0, 1.9, t)

        assertTrue(m.x - 37634.0 < 1.0)
        assertTrue(m.xDot - 1.0 < 1.0)
        assertTrue(m.y - (-1103.0) < 1.0)
        assertTrue(m.yDot - (-40.0) < 1.0)
        assertTrue(m.z - 28846.0 < 1.0)
        assertTrue(m.zDot - 112.0 < 1.0)
        assertTrue(m.h - 37650.0 < 1.0)
        assertTrue(m.hDot - 2.0 < 1.0)
        assertTrue(m.f - 47430.0 < 1.0)
        assertTrue(m.fDot - 70.0 < 1.0)
        assertTrue(m.d - (-0.02) < 0.01)
        assertTrue(m.dDot - (-0.001) < 0.001)
        assertTrue(m.i - 0.6 < 0.1)
        assertTrue(m.iDot - 0.001 < 0.001)
    }

    @Test
    fun wmmAtPole() {
        val t = Geomag.toDecimalYears(2023, 11, 1, 0, 0, 0)
        val m = Geomag.wmm(0.0, 90.0, 1.9, t)

        assertTrue(m.x - 1717.0 < 1.0)
        assertTrue(m.xDot - (-27.0) < 1.0)
        assertTrue(m.y - 358.0 < 1.0)
        assertTrue(m.yDot - 63.0 < 1.0)
        assertTrue(m.z - 56776.0 < 1.0)
        assertTrue(m.zDot - 24.0 < 1.0)
        assertTrue(m.h - 1754.0 < 1.0)
        assertTrue(m.hDot - (-14.0) < 1.0)
        assertTrue(m.f - 56803.0 < 1.0)
        assertTrue(m.fDot - 23.0 < 1.0)
        assertTrue(m.d - 0.2 < 0.1)
        assertTrue(m.dDot - 0.03 < 0.01)
        assertTrue(m.i - 1.0 < 1.0)
        assertTrue(m.iDot - 0.0002 < 0.0001)
    }

    @Test
    fun igrfAtPole() {
        val t = Geomag.toDecimalYears(2023, 11, 1, 0, 0, 0)
        val m = Geomag.igrf(0.0, 90.0, 1.9, t)

        assertTrue(m.x - 1711.0 < 1.0)
        assertTrue(m.xDot - (-24.0) < 1.0)
        assertTrue(m.y - 364.0 < 1.0)
        assertTrue(m.yDot - 62.0 < 1.0)
        assertTrue(m.z - 56778.0 < 1.0)
        assertTrue(m.zDot - 25.0 < 1.0)
        assertTrue(m.h - 1749.0 < 1.0)
        assertTrue(m.hDot - (-10.0) < 1.0)
        assertTrue(m.f - 56805.0 < 1.0)
        assertTrue(m.fDot - 24.0 < 1.0)
        assertTrue(m.d - 0.2 < 0.1)
        assertTrue(m.dDot - 0.03 < 0.01)
        assertTrue(m.i - 1.0 < 1.0)
        assertTrue(m.iDot - 0.0002 < 0.0001)
    }
}