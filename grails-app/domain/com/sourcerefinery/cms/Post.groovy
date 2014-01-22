package com.sourcerefinery.cms

/**
 * A post or page.
 */
class Post {
    String layout = "markdown-blog-default"
    String title
    String type = "post"
    String status = "draft"
    String permalink
    String teaser
    String content
    String author
    Date date
    Date dateCreated // auto
    Date lastUpdated // auto

    static constraints = {
        layout(nullable: true, maxSize: 100)
        title(nullable: false, blank: false, maxSize: 500)
        type(nullable: false, inList: ["post", "page"])
        status(nullable: false, inList: ["draft", "published", "deleted"])
        permalink(nullable: true, maxSize: 200)
        teaser(nullable: true, blank: true, maxSize: 4096)
        content(nullable: true, blank: true)
        author(nullable: true, blank: true, maxSize: 200)
        date(nullable: true)
        dateCreated(nullable: false)
        lastUpdated(nullable: false)
    }

    static mapping = {
        table("cms_post")
        content(type: "text")
    }
}