Grails Markdown Blog
====================

This Grails plugin embeds a simple Markdown blogging and content management system into your Grails app.

Once the plugin is installed, it provides a user interface for dynamically creating posts and pages using a slick embedded JavaScript Markdown editor (http://epiceditor.com/). You can then map the blog controllers to whatever URLs you want using the standard UrlMappings in Grails. You can also override the default templates with your own Grails views (recommended).

## When is this plugin a good choice?
If you've got an existing, public-facing Grails application and would like to add dynamic sections or a blog, this plugin might be a good start. If you're starting from scratch and your sole purpose is to write a blog, go with something more full-featured like Wordpress or Jekyll.

## Installation
Install the plugin the usual way, in ```grails-app/conf/BuildConfig.groovy```. *This plugin is not yet in Grails Central, so you'll have to do a ```git clone``` and install it locally for now.*

## Security
**Important!** - This plugin intentionally does not include any security mechanism to protect the blog admin controller! You should protect it with Spring Security, Shiro, or your method of choice.

## URL Mappings
By default, this plugin will put your public-facing posts and pages under /markdownBlog. This is almost certainly not what you want. Use the standard ```grails-app/conf/UrlMappings.groovy``` to map them. Here's an example:

```groovy
class UrlMappings {
    static mappings = {
        // ...
	      
        // Listing pages
        "/blog/" {
            controller = "markdownBlog"
            action = "index"
        }
        "/blog/archive" {
            controller = "markdownBlog"
            action = "archive"
        }
        "/blog/rss" {
            controller = "markdownBlog"
            action = "rss"
        }
        
        // Blog posts by permalink
        "/blog/$yyyy/$mm/$permalink/" {
            controller = "markdownBlog"
            action = "post"
        }
        
        // Ordinary editable pages by permalink
        "/pages/$permalink/" {
            controller = "markdownBlog"
            action = "page"
        }
        
        // The admin section
        "/top-secret-admin-section/blog/$action?/$id?"(controller: 'markdownBlogAdmin')
    }
}

```

In this example, we've put the blog posts under /blog and other pages under /pages. Of course, you can map them however you want using the powerful UrlMapping mechanism in Grails.

## Customizing Views
Once you get your controllers mapped and write a blog post, you will probably find the plugin's default post and page layouts to be a real disappointment. Fear not! You can install the views into your own Grails project and customize them.

```sh

$ grails markdown-blog-generate-views

```

This will install the views in ```grails-app/views/markdownBlog``` where you can then modify them to your heart's content.

This command also appends default settings to ```grails-app/conf/Config.groovy``` that you can override.

Blah
