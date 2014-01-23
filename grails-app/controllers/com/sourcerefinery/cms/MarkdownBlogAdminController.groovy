package com.sourcerefinery.cms

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
            println "*** content: ${params.content}"
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
