package com.sourcerefinery.cms

class BlogController {
    def grailsApplication

    def index() {
        // show the most recent posts with titles and teasers
    }

    def post() {
        def post = Post.get(params.id as Long)

        if (!post && params.permalink) {
            if (params.type) {
                post = Post.findByPermalinkAndType(params.permalink as String, params.type as String)
            } else {
                post = Post.findByPermalink(params.permalink as String)
            }
        }

        if (post?.status != "published") {
            render(view: "notpublished", model: [post: post])
        } else if (post?.type == "page") {
            render(view: grailsApplication.config.grails.markdownblog?.pageView ?: "page", model: [post: post])
        } else if (post?.type == "post") {
            render(view: grailsApplication.config.grails.markdownblog?.postView ?: "post", model: [post: post])
        } else {
            response.sendError(404)
        }
    }

    def archive() {
        // show an archived list of all posts by year and month
    }
}
