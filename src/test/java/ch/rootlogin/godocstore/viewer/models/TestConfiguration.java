package ch.rootlogin.godocstore.viewer.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class TestConfiguration {

    @Test
    public void testToString() {
        var testCases = new ArrayList<TestCase>();

        for (var testCase : testCases) {
            Assert.assertEquals(testCase.getJson(), testCase.getConfiguration().toString());
        }
    }

    public class TestCase {
        private Configuration configuration;
        private String json;

        public TestCase(Configuration configuration, String json) {
            this.configuration = configuration;
            this.json = json;
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }
    }
}
