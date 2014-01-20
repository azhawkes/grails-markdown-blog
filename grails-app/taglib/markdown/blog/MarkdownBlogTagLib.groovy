package markdown.blog

import com.sourcerefinery.cms.Post

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
        def post = attrs.post as Post

        if (!post) {
            // nothing
        } else if (post.type == "post") {
            if (grailsApplication.config.grails.plugin?.markdownblog?.postUrlShowYYYYMM && post.permalink) {
                def yyyy = g.formatDate(date: post.date, format: 'yyyy')
                def mm = g.formatDate(date: post.date, format: 'MM')

                out << g.createLink(controller: 'markdownBlog', action: 'post', params: [yyyy: yyyy, mm: mm, permalink: post.permalink])
            } else if (post.permalink) {
                out << g.createLink(controller: 'markdownBlog', action: 'post', params: [permalink: post.permalink])
            } else {
                out << g.createLink(controller: 'markdownBlog', action: 'post', id: post.id)
            }
        } else if (post.type == "page") {
            if (post.permalink) {
                out << g.createLink(controller: 'markdownBlog', action: 'page', params: [permalink: post.permalink])
            } else {
                out << g.createLink(controller: 'markdownBlog', action: 'page', id: post.id)
            }
        }
    }
}
