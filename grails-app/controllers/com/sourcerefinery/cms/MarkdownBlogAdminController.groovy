package com.sourcerefinery.cms

/**
 * Controller that handles the blog admin pages. We intentionally don't use Spring Security or
 * any other security framework to secure this, but very strongly recommend users protect it
 * with their framework of choice!
 */
class MarkdownBlogAdminController {
    static defaultAction = "index"

    def index() {
        [posts: Post.findAllByStatusNotEqual("deleted")]
    }

    def create() {
        [post: new Post()]
    }

    def edit(Long id) {
        [post: Post.get(id)]
    }

    def save() {
        Post post = Post.get(params.id as Long)

        if (post) {
            post.properties = params
        } else {
            post = new Post(params)
        }

        post.dateCreated = post.dateCreated ?: new Date()
        post.lastUpdated = new Date()

        if (post.save()) {
            redirect(action: "index")
        } else if (post.id) {
            render(view: "edit", model: [post: post])
        } else {
            render(view: "create", model: [post: post])
        }
    }
}
