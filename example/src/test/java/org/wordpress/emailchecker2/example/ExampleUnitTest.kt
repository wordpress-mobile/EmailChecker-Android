package org.wordpress.emailchecker2.example

import org.junit.Assert
import org.junit.Test
import org.wordpress.emailchecker2.suggestDomainCorrection

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun identity_isCorrect() {
        val res = suggestDomainCorrection("test@gmail.com");
        Assert.assertEquals(res, "test@gmail.com");
    }
}
