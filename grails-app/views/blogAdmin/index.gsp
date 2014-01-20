<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Blog Admin</title>
    <meta name="layout" content="markdown-blog-admin"/>
</head>

<body>
    <div class="markdown-blog-admin-form">
        <g:if test="${posts}">
            <table class="markdown-blog-posts-table" cellspacing="0">
                <thead>
                    <tr>
                        <th>Post</th>
                        <th>Location</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each var="post" in="${posts}">
                        <tr>
                            <td>
                                <g:link action="edit" id="${post.id}">${post.title?.encodeAsHTML()}</g:link>
                            </td>
                            <td>
                                <g:if test="${grailsApplication.config.grails.markdownblog.postUrlShowYYYYMM && post.permalink}">
                                    <g:set var="yyyy" value="${formatDate(date: post.date, format: 'yyyy')}"/>
                                    <g:set var="mm" value="${formatDate(date: post.date, format: 'MM')}"/>
                                    <g:set var="link" value="${createLink(controller: 'blog', action: 'post', params: [yyyy: yyyy, mm: mm, permalink: post.permalink])}"/>
                                </g:if>
                                <g:elseif test="${post.permalink}">
                                    <g:set var="link" value="${createLink(controller: 'blog', action: 'post', params: [permalink: post.permalink])}"/>
                                </g:elseif>
                                <g:else>
                                    <g:set var="link" value="${createLink(controller: 'blog', action: 'post', id: post.id)}"/>
                                </g:else>
                                <a target="preview" href="${link}">${link.encodeAsHTML()}</a>
                            </td>
                            <td>
                                <g:message code="post.status.${post.status}"/>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:link action="create">+ Create a post</g:link>
    </div>
</body>
</html>