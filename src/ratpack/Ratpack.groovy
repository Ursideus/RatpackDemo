import static ratpack.groovy.Groovy.ratpack
//import ratpack.config.ConfigData
//import static ratpack.groovy.Groovy.groovyMarkupTemplate
//import ratpack.groovy.template.MarkupTemplateModule
import ratpack.form.Form

//import ratpack.jackson.guice.JacksonModule
//import static ratpack.jackson.Jackson.json


/* ----------------------------------------------------------- */
// Handlers with Mime Type Negotiation.
/* ----------------------------------------------------------- */
ratpack {
  handlers {
    prefix("api") {
      handler {
        byMethod {
          get {
            String content = "This is my content"
            byContent {
              json {
                render "{\"content\": \"$content\"}"
              }
              xml {
                render "<content>$content</content>"
              }
              html {
                render "<!DOCTYPE html><html><body>$content</body></html>"
              }
              plainText {
                render content
              }
              noMatch {
                render "I have no clue what you wanted from me."
              }
            }
          }
          post {
            Form form = parse(Form)
            String data = form.data
            byContent {
              json {
                render "{\"message\": \"Received $data\"}"
              }
              xml {
                render "<message>Received $data</message>"
              }
              html {
                render "<!DOCTYPE html><html><body>Thank you for submitting $data</body></html>"
              }
              plainText {
                render data
              }
              noMatch {
                render "I have no clue what you wanted from me."
              }
            }
          }
        }
      }
    }
  }
}

/* ----------------------------------------------------------- */
// Handlers for specific prefix.
/* ----------------------------------------------------------- */
// ratpack {
//   handlers {
// 	   prefix("api") {
//       handler {
//         byMethod {
//           get {
//             render "Hello World!"
//           }
//           post {
//             Form form = parse(Form)
//             form.get
//             render "Hello ${form.data}"
//           }
//         }
//       }
//     }
//   }
// }

/* ----------------------------------------------------------- */
// Handling different HTTP verbs for the same route.
/* ----------------------------------------------------------- */
// ratpack {
//   handlers { chain ->
//     chain.handler { contx ->
//       contx.byMethod { spec ->
//         spec.get { ctx ->
//           ctx.render "this is root get handler!"
//         }
//         // post {
//         //   Form form = parse(Form)
//         //   render "Hello ${form.data}"
//         // }
//       }
//     }
//   }
// }

// -> Java equivalent:
// public class Main {
//   public static void main(String[] args) throws Exception {
//     RatpackServer.start(spec -> spec
//         .serverConfig(ServerConfig.noBaseDir())
//         .handlers(chain -> chain
//           .handler(c -> c
//           	.byMethod(spec -> spec
//           		.get(ctx -> ctx.render("Hello World!"))
//           		.post(ctx -> {
//           			Form form = ctx.parse(Form);
//           			ctx.render("Hello " + form.get("data"));
//           		})
//           	)
//           )
//         )
//     );
//   }
// }

/* ----------------------------------------------------------- */
//  Handlers with URI prefix
/* ----------------------------------------------------------- */
// ratpack {
//   handlers {
//     prefix("api") {
//       get {
//         render "Hello World! from get()"
//       }
//       post {
//         Form form = parse(Form)
//         render 'OK'
//       }
//     }
//   }
// }

/* ----------------------------------------------------------- */
//  Bind with model class
/* ----------------------------------------------------------- */
// ratpack {
//     bindings {
//         module JacksonModule
//     }
//     handlers {
//         get {
//             render 'Hello Visitor!'
//         }
//         get('person') {
//             render json(new Person(id: 1, name: 'John Doe', age: 33))
//         }
//     }
// }
//
// class Person {
//    Long id
//    String name
//    int age
// }
/* ----------------------------------------------------------- */
/*ratpack {
    handlers {
        get('users/:id') { //ex: /users/xyz123
            render context.pathTokens['id']
        }
        get('users') { //ex: /users/
            render 'no path token'
        }
        get('repeatString/:string/:times:[\\d]+') {
            //ex: /repeatString/X/10  would send back 10 Xs
            render context.pathTokens['string'] * context.pathTokens['times'].toInteger()
        }
    }
}*/

/*ratpack {
  handlers {
    get('getmapping') {
        render 'some data'
    }
    post('postmapping') {
        println request.body.text
        render 'some output'
    }
  }
}
*/
/*ratpack {
  handlers {
    handler {
        println 'some stuff done here'
        next()
    }
    handler {
      render 'some output'
    }
  }
}*/

/*ratpack {
    handlers {
        get("hello") {
            render "Hello, World!"
        }

        get("goodbye") {
            render "Goodbye, World!"
        }
    }
}*/

/*ratpack {
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
    //files { it.dir('public') }
  }
}
*/
