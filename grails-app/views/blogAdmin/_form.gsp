<%@ page import="org.springframework.validation.FieldError; com.sourcerefinery.cms.Post" %>
<g:form action="save" class="edit-post-form" id="${post.id}">
    <div class="markdown-blog-admin-form">
        <div class="form-row">
            <label for="title">Title</label>
            <g:textField name="title" value="${post.title}"/>
        </div>
        <div class="form-row">
            <label for="content">Content</label>
            <g:textArea name="content" value="${post.content}"/>
        </div>
        <div class="form-row clearfix">
            <div class="form-half">
                <label for="layout">Author</label>
                <g:textField name="author" value="${post.author}"/>
            </div>
            <div class="form-half">
                <label for="layout">Permalink</label>
                <g:textField name="permalink" value="${post.permalink}"/>
            </div>
        </div>
        <div class="form-row clearfix">
            <div class="form-half">
                <label for="date">Date</label>
                <g:datePicker name="date" value="${post.date}" precision="day" relativeYears="[-5..1]"/>
            </div>
            <div class="form-half">
                <label for="layout">Layout</label>
                <g:textField name="layout" value="${post.layout}"/>
            </div>
        </div>
        <div class="form-row clearfix">
            <div class="form-half">
                <label for="status">Status</label>
                <g:radioGroup name="status" values="['draft', 'published', 'deleted']" value="${post.status ?: 'draft'}" labels="['Draft', 'Published', 'Deleted']">
                    <label class="radio">${it.radio} ${it.label}</label>
                </g:radioGroup>
            </div>
            <div class="form-half">
                <label for="type">Type</label>
                <g:radioGroup name="type" values="['post', 'page']" value="${post.type}" labels="['Post', 'Page']">
                    <label class="radio">${it.radio} ${it.label}</label>
                </g:radioGroup>
            </div>
        </div>
        <g:hasErrors bean="${post}">
            <ul class="errors" role="alert">
                <g:eachError bean="${post}" var="error">
                    <li><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
    </div>
    <div class="markdown-blog-admin-footer">
        <div class="edit-post-buttons">
            <g:submitButton name="save" value="Save"/>
            <g:link action="index">Cancel</g:link>
        </div>
    </div>
</g:form>
