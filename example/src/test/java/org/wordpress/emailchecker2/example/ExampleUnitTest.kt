package org.wordpress.emailchecker2.example

import org.junit.Assert
import org.junit.Test
import org.wordpress.emailchecker2.suggestDomainCorrection

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
class EmailCheckerUnitTest {
    @Test
    @Throws(Exception::class)
    fun identity() {
        val res = suggestDomainCorrection("test@gmail.com")
        Assert.assertEquals(res, "test@gmail.com")
    }

    @Test
    @Throws(Exception::class)
    fun insert1() {
        val res = suggestDomainCorrection("test@gail.com")
        Assert.assertEquals(res, "test@gmail.com")
    }

    @Test
    @Throws(Exception::class)
    fun insert2() {
        val res = suggestDomainCorrection("test@gil.com")
        Assert.assertEquals(res, "test@gil.com") // Not supported
    }

    @Test
    @Throws(Exception::class)
    fun insert3() {
        val res = suggestDomainCorrection("test@ahoo.com")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun insert4() {
        val res = suggestDomainCorrection("test@yahoo.co")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun delete1() {
        val res = suggestDomainCorrection("test@yahoo.comm")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun delete2() {
        val res = suggestDomainCorrection("test@yahooo.com")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun transpose1() {
        val res = suggestDomainCorrection("test@ayhoo.com")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun transpose2() {
        val res = suggestDomainCorrection("test@yahoo.cmo")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun replace1() {
        val res = suggestDomainCorrection("test@yahoo.coo")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun replace2() {
        val res = suggestDomainCorrection("test@yyhoo.com")
        Assert.assertEquals(res, "test@yahoo.com")
    }

    @Test
    @Throws(Exception::class)
    fun nope1() {
        val res = suggestDomainCorrection("hey@example.com")
        Assert.assertEquals(res, "hey@example.com")
    }

    @Test
    @Throws(Exception::class)
    fun dontcrash1() {
        val res = suggestDomainCorrection("")
        Assert.assertEquals(res, "")
    }

    @Test
    @Throws(Exception::class)
    fun dontcrash2() {
        val res = suggestDomainCorrection("hey")
        Assert.assertEquals(res, "hey")
    }
}
