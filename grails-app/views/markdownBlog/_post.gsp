<div class="markdown-blog-post">
    <div class="markdown-blog-post-header">
        <h1 class="markdown-blog-post-title">${post.title?.encodeAsHTML()}</h1>
        <g:if test="${post.author}">
            <div class="markdown-blog-post-info">
                Posted by ${post.author.encodeAsHTML()} on
                <g:formatDate date="${post.date}" format="${grailsApplication.config.grails.plugin?.markdownblog?.dateFormat ?: 'yyyy-MM-dd'}"/>
            </div>
        </g:if>
        <g:else>
            <div class="markdown-blog-post-info">
                Posted on
                <g:formatDate date="${post.date}" format="${grailsApplication.config.grails.plugin?.markdownblog?.dateFormat ?: 'yyyy-MM-dd'}"/>
            </div>
        </g:else>
    </div>
    <div class="markdown-blog-post-body">
        ${post.content?.encodeAsHTML()}
    </div>
    <div class="markdown-blog-post-footer">

    </div>
</div>
