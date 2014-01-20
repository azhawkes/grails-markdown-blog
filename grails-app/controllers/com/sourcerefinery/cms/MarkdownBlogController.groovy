package com.sourcerefinery.cms

class MarkdownBlogController {
    def grailsApplication

    def index() {
        def posts = Post.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])
        def recent = Post.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])

        [posts: posts, recent: recent]
    }

    def archive() {
        def posts = Post.findAllByStatusAndType("published", "post", [sort: "date", order: "desc"])
        def postsByMonth = posts.groupBy { g.formatDate(date: it.date, format: "MMM yyyy") }

        [postsByMonth: postsByMonth]
    }

    /**
     * Renders a post or page, regardless of its type.
     */
    def show() {
        def p = getPost(params)

        if (p?.type == "page") {
            redirect(action: "page")
        } else if (p?.type == "post") {
            redirect(action: "post")
        } else {
            response.sendError(404)
        }
    }

    /**
     * Renders a post, but only if its type is "page".
     */
    def page() {
        def p = getPost(params)

        if (p?.status == "published" && p?.type == "page") {
            render(view: grailsApplication.config.grails.plugin?.markdownblog?.pageView ?: "page", model: [post: p])
        } else {
            response.sendError(404)
        }
    }

    /**
     * Renders a post, but only if its type is "post".
     */
    def post() {
        def p = getPost(params)

        if (p?.status == "published" && p?.type == "post") {
            render(view: grailsApplication.config.grails.plugin?.markdownblog?.postView ?: "post", model: [post: p])
        } else {
            response.sendError(404)
        }
    }

    private Post getPost(params) {
        def post = Post.get(params.id as Long)

        if (!post && params.permalink) {
            if (params.type) {
                post = Post.findByPermalinkAndType(params.permalink as String, params.type as String)
            } else {
                post = Post.findByPermalink(params.permalink as String)
            }
        }

        return post
    }
}
