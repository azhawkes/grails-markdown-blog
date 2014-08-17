class MarkdownBlogGrailsPlugin {
    // the plugin version
    def version = "0.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Markdown Blog Plugin" // Headline display name of the plugin
    def author = "Andy Hawkes"
    def authorEmail = "andy@andyhawkes.com"
    def description = '''\
This plugin adds a Markdown-powered blog and content editor to your Grails app. Edit your dynamic blog posts and pages with a client-side Markdown editor (EpicEditor). You can then customize the appearance with your own Grails views, map your posts/pages to whatever location you want with UrlMappings, and so on. Check out the project documentation for more info!
'''

    // URL to the plugin's documentation
//    def documentation = "http://grails.org/plugin/markdown-blog"
    def documentation = "https://github.com/azhawkes/grails-markdown-blog/blob/master/README.md"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "GitHub", url: "https://github.com/azhawkes/grails-markdown-blog/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/azhawkes/grails-markdown-blog" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { ctx ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
