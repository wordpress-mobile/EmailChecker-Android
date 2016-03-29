package org.wordpress.emailchecker2.example;

import org.junit.Assert;
import org.junit.Test;
import org.wordpress.emailchecker2.EmailCheckerKt;

public class EmailCheckerUnitTest2 {
    @Test
    public void dontCrash3() {
        try {
            EmailCheckerKt.suggestDomainCorrection(null);
        } catch (IllegalArgumentException iae) {
            return;
        }
        Assert.fail("Expected an IllegalArgumentException");
    }
}
