package com.sourcerefinery.markdownblog

import markdown.blog.MarkdownBlogTagLib

/**
 * Public-facing controller that serves up blog posts and pages. It's probably a good idea to map
 * these actions individually in UrlMappings.groovy to whatever URLs suit your style.
 */
class MarkdownBlogController {
    def grailsApplication
    def markdownBlogTagLib = new MarkdownBlogTagLib()

    /**
     * Renders a blog index page with the most recent posts.
     */
    def index() {
        def posts = MarkdownBlogPost.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])
        def recent = MarkdownBlogPost.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])

        [posts: posts, recent: recent]
    }

    /**
     * Renders a blog archive page with all posts, grouped historically by year and month.
     */
    def archive() {
        def posts = MarkdownBlogPost.findAllByStatusAndType("published", "post", [sort: "date", order: "desc"])
        def postsByMonth = posts.groupBy { g.formatDate(date: it.date, format: "MMM yyyy") }
        def recent = MarkdownBlogPost.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])

        [postsByMonth: postsByMonth, recent: recent]
    }

    /**
     * Returns an RSS feed of all published posts.
     */
    def rss = {
        def posts = MarkdownBlogPost.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])
        def blogTitle = grailsApplication.config.grails.plugin?.markdownblog?.title ?: "Untitled Blog"

        render(feedType: "rss", feedVersion: "2.0") {
            title = blogTitle
            link = g.createLink(controller: "markdownBlog", action: "rss", absolute: true)
            description = blogTitle

            posts.each { post ->
                entry(post.title) {
                    link = markdownBlogTagLib.createLink(post: post, absolute: true)
                    post.html
                }
            }
        }
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
            def recent = MarkdownBlogPost.findAllByStatusAndType("published", "post", [sort: "date", order: "desc", max: 5])

            render(view: grailsApplication.config.grails.plugin?.markdownblog?.postView ?: "post", model: [post: p, recent: recent])
        } else {
            response.sendError(404)
        }
    }

    private MarkdownBlogPost getPost(params) {
        def post = MarkdownBlogPost.get(params.id as Long)

        if (!post && params.permalink) {
            if (params.type) {
                post = MarkdownBlogPost.findByPermalinkAndType(params.permalink as String, params.type as String)
            } else {
                post = MarkdownBlogPost.findByPermalink(params.permalink as String)
            }
        }

        return post
    }
}
