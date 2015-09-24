import ratpack.config.ConfigData
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    module MarkupTemplateModule
    // Define config file/Env settings path
    ConfigData configData = ConfigData.of()
            .yaml(ClassLoader.getSystemResource('application.yml'))
            .props(ClassLoader.getSystemResource('application.properties') as URL)
            .sysProps()
            .env()
            .build()
    bindInstance(ConfigData, configData) //this loads this instance into Guice
  }

  handlers {
    // Read config file/Env settings
    handler { ConfigData configData ->
      println configData.get("/property1", String)
      println configData.get("/property2/subproperty2", String)
      println configData.get("/javasysprop", String)
      println configData.get("/MYPROP", String)
      render "done"
    }
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { dir "public" }
  }
}
