package markdown.blog

import com.sourcerefinery.markdownblog.MarkdownBlogPost

class MarkdownBlogTagLib {
    static namespace = "blog"
    static defaultEncodeAs = 'html'
    //static encodeAsForTags = [tagName: 'raw']

    def grailsApplication

    def link = { attrs, body ->
        attrs.uri = createLink(attrs, body)

        out << g.link(attrs, body)
    }

    def createLink = { attrs, body ->
        def post = attrs.post as MarkdownBlogPost

        if (!post) {
            // nothing
        } else if (post.type == "post") {
            attrs.controller = "markdownBlog"

            if (grailsApplication.config.grails.plugin?.markdownblog?.postUrlShowYYYYMM && post.permalink) {
                def yyyy = g.formatDate(date: post.date, format: 'yyyy')
                def mm = g.formatDate(date: post.date, format: 'MM')

                attrs.action = "post"
                attrs.params = [yyyy: yyyy, mm: mm, permalink: post.permalink]
            } else if (post.permalink) {
                attrs.action = "post"
                attrs.params = [permalink: post.permalink]
            } else {
                attrs.action = "post"
                attrs.id = post.id
            }
        } else if (post.type == "page") {
            if (post.permalink) {
                attrs.action = "page"
                attrs.params = [permalink: post.permalink]
            } else {
                attrs.action = "page"
                attrs.id = post.id
            }
        }

        out << g.createLink(attrs)
    }
}
