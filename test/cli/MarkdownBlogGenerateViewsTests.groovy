import grails.test.AbstractCliTestCase

class MarkdownBlogGenerateViewsTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testMarkdownBlogGenerateViews() {

        execute(["markdown-blog-generate-views"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
